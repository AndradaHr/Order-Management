package org.example.dao;
import org.example.connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {
    protected final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;


    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String createFindAllQuery() {
        String findAllQuery = "SELECT * from public.";
        findAllQuery += type.getSimpleName();

        return findAllQuery;
    }

    public ArrayList<T> findAll() {
        String query = createFindAllQuery();
        ArrayList<T> list = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(query);
            rs = findStatement.executeQuery();

            list = createObjects(rs);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:findAllClients " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }

        return list;
    }
    private ArrayList<T> createObjects(ResultSet resultSet) {
        ArrayList<T> list = new ArrayList<T>();

        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value;
                    if (field.getType().equals(int.class)) {
                        value = resultSet.getInt(field.getName());
                    } else {
                        value = resultSet.getObject(field.getName());
                    }
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException | InstantiationException e) {
            e.printStackTrace();
        }
        return list;
    }


    private boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }

    private String createFindQueryById(T object) {
        String findQuery = "SELECT * FROM public.";
        findQuery += object.getClass().getSimpleName();
        findQuery += " where id = ?";

        return findQuery;
    }

    public void findById(T object) {
        String query = createFindQueryById(object);
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(query);

            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    try {
                        findStatement.setInt(1, (int) field.get(object));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

            rs = findStatement.executeQuery();
            if (rs.next()) {
                for (Method method : object.getClass().getDeclaredMethods()) {
                    if (!method.getName().equals("setId") && method.getName().startsWith("set")) {
                        String columnName = method.getName().substring(3).toLowerCase(Locale.ROOT);
                        String value = rs.getString(columnName);
                        if (isNumeric(value)) {
                            method.invoke(object, Integer.parseInt(rs.getString(columnName)));
                        } else method.invoke(object, rs.getString(columnName));
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:findObjectById " + e.getMessage());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    private String createInsertQuery(T object, ArrayList<String> fieldsName) {
        String insertStatementString = "INSERT INTO public.";
        insertStatementString += object.getClass().getSimpleName();

        insertStatementString += " (";
        String valuesString = " VALUES(";
        for (String fieldName : fieldsName) {
            insertStatementString += fieldName + ", ";
            valuesString += "?, ";
        }
        insertStatementString = insertStatementString.substring(0, insertStatementString.length() - 2);
        insertStatementString += ")";

        valuesString = valuesString.substring(0, valuesString.length() - 2);
        valuesString += ")";

        insertStatementString += valuesString;

        return insertStatementString;
    }
    public int insert(T object) {
        ArrayList<String> fieldsName = new ArrayList<>();
        ArrayList<Object> fieldsValue = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.getName().equals("id")) {
                try {
                    fieldsName.add(field.getName());
                    fieldsValue.add(field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        String query = createInsertQuery(object, fieldsName);

        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int fieldIndex = 1;

            for (Object fieldValue : fieldsValue) {
                if (fieldValue.getClass().getSimpleName().equals("Integer"))
                    insertStatement.setInt(fieldIndex, (Integer) fieldValue);
                else if (fieldValue.getClass().getSimpleName().equals("Double"))
                    insertStatement.setDouble(fieldIndex, (Double) fieldValue);
                else if (fieldValue.getClass().getSimpleName().equals("LocalDate"))
                    insertStatement.setDate(fieldIndex, java.sql.Date.valueOf((LocalDate) fieldValue));
                else insertStatement.setString(fieldIndex, (String) fieldValue);
                fieldIndex++;
            }

            insertStatement.executeUpdate();
            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    private String createUpdateQuery(T object) {
        String updateQuery = "UPDATE public.";
        updateQuery += object.getClass().getSimpleName() + " set ";

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (!field.getName().equals("id"))
                updateQuery += field.getName() + " = ?, ";
        }

        updateQuery = updateQuery.substring(0, updateQuery.length() - 2);
        updateQuery += " where id = ?";

        return updateQuery;
    }

    public void update(T object) {
        String query = createUpdateQuery(object);

        Connection dbConnection = ConnectionFactory.getConnection();

        ArrayList<String> fieldsName = new ArrayList<>();
        ArrayList<Object> fieldsValue = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                fieldsName.add(field.getName());
                fieldsValue.add(field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(query, Statement.NO_GENERATED_KEYS);

            int fieldIndex = 1;
            for (int i = 0; i < fieldsValue.size(); i++) {
                if (fieldsName.get(i).equals("id")) {
                    insertStatement.setInt(fieldsValue.size(), (Integer) fieldsValue.get(i));
                } else {
                    if (fieldsValue.get(i).getClass().getSimpleName().equals("Integer"))
                        insertStatement.setInt(fieldIndex, (Integer) fieldsValue.get(i));
                    else insertStatement.setString(fieldIndex, (String) fieldsValue.get(i));
                    fieldIndex++;
                }
            }
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

    private String createDeleteQuery(T object) {
        String deleteQuery = "DELETE FROM public.";
        deleteQuery += object.getClass().getSimpleName();
        deleteQuery += " WHERE id = ?";

        return deleteQuery;
    }

    public void delete(T object) {
        String deleteStatementString = createDeleteQuery(object);
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        try {
            insertStatement = dbConnection.prepareStatement(deleteStatementString, Statement.NO_GENERATED_KEYS);


            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equals("id")) {
                    try {
                        insertStatement.setInt(1, (int) field.get(object));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }

            insertStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}

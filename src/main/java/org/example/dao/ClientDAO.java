package org.example.dao;

import org.example.connection.ConnectionFactory;
import org.example.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
public class ClientDAO {

    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO student (name,address,email,age)"
            + " VALUES (?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM student where id = ?";

    private static final String updateStatementString = "UPDATE students SET name=?, address=?, email=?, age=? WHERE id=?";
    private static final String deleteStatementString = "DELETE FROM students WHERE id=?";

    public static Client findById(int studentId) {
        Client toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, studentId);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            String address = rs.getString("address");
            String email = rs.getString("email");
            int age = rs.getInt("age");
            toReturn = new Client(studentId, name, address, email, age);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"StudentDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static int insert(Client student) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, student.getName());
            insertStatement.setString(2, student.getAddress());
            insertStatement.setString(3, student.getEmail());
            insertStatement.setInt(4, student.getAge());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "StudentDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    private boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }

    private String createFindQueryById(Client student) {
        String findQuery = "SELECT * FROM order_management.";
        findQuery += student.getName();
        findQuery += " where id = ?";

        return findQuery;
    }

    private String createInsertQuery(Client student, ArrayList<String> fieldsName) {
        String insertStatementString = "INSERT INTO order_management.";
        insertStatementString += student.getName();

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
*/
    public class ClientDAO extends AbstractDAO<Client> {
        public ClientDAO() {
            super();
        }
    }




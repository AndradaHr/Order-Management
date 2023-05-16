package org.example.dao;
import org.example.connection.ConnectionFactory;
import org.example.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class OrderDAO extends AbstractDAO<Order> {
    private final String getOrderDetailsStatementString = "select order.id as \"Order id\", client.name as \"Client\", product.name as \"Product\", order.quantity from order_management.order\n" +
            "join client on client.id = order.clientId\n" +
            "join product on product.id = order.productId\n" +
            "where order.id = ?";

    public OrderDAO() {
        super();
    }


    public Order getOrderDetails(Order order) {
        Order toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement getDetailsStatement = null;
        ResultSet rs = null;
        try {
            getDetailsStatement = dbConnection.prepareStatement(getOrderDetailsStatementString);
            getDetailsStatement.setLong(1, order.getId());
            rs = getDetailsStatement.executeQuery();
            rs.next();

            int orderId = rs.getInt("Order id");
            int clientName = rs.getInt("Client");
            int productName = rs.getInt("Product");
            int quantity = rs.getInt("quantity");
            toReturn = new Order(orderId, clientName, productName, quantity);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:getOrderDetails " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(getDetailsStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

}

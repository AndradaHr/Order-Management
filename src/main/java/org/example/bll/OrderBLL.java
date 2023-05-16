package org.example.bll;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.dao.BillDAO;
import org.example.dao.OrderDAO;
import org.example.model.Bill;
import org.example.model.Client;
import org.example.model.Order;
import org.example.model.Product;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.UUID;

public class OrderBLL {
    private final OrderDAO orderDAO = new OrderDAO();
    private final BillDAO billDAO=new BillDAO();

    public Order findOrderById(int orderId) {
        Order order = new Order(orderId);

        orderDAO.findById(order);

        if (order.getId() == 0) {
            throw new NoSuchElementException("The order with id =" + orderId + " was not found!");
        }

        return order;
    }
    public int insertOrder(int clientId, int productId, int quantity) {
        ProductBLL productBLL = new ProductBLL();
        Product product = productBLL.findProductById(productId);
        ClientBLL clientBLL = new ClientBLL();
        Client client = clientBLL.findClientById(clientId);

        int orderId = 0;
        if (product.getName() != null && product.getStock() >= quantity) {
            Order order = new Order(clientId, productId, quantity);
            orderId = orderDAO.insert(order);
            order.setId(orderId);

            product.setStock(product.getStock() - order.getQuantity());
            productBLL.updateProduct(productId, product.getName(), product.getPrice(), product.getStock());

            Order orderDetails = orderDAO.getOrderDetails(order);

            // Create a new Bill object for this order
            int billId = Math.abs(UUID.randomUUID().hashCode());
            int billTotal = product.getPrice() * order.getQuantity();
            LocalDate billDate = LocalDate.now();
            Bill bill = new Bill(order.getId(), order.getId(), billTotal, billDate);
            billDAO.insert(bill);

            Document document = new Document();


            try {
                PdfWriter.getInstance(document, new FileOutputStream(bill.id()+".pdf"));

                document.open();

                PdfPTable table = new PdfPTable(4); // 4 columns.

                PdfPCell cell1 = new PdfPCell(new Phrase("ID"));
                PdfPCell cell2 = new PdfPCell(new Phrase("Order ID"));
                PdfPCell cell3 = new PdfPCell(new Phrase("Total"));
                PdfPCell cell4 = new PdfPCell(new Phrase("Date"));

                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);


                table.addCell(String.valueOf(bill.id()));
                table.addCell(String.valueOf(bill.orderId()));
                table.addCell(String.valueOf(bill.total()));
                table.addCell(String.valueOf(bill.date()));

                document.add(table);

                document.close();

            } catch (FileNotFoundException | DocumentException e) {
                e.printStackTrace();
            }



        return orderId;
    } else return -1;}


    public int updateOrder(int id, int clientId, int productId, int quantity) {
        Order orderToUpdate = new Order();
        orderToUpdate.setId(id);
        orderDAO.findById(orderToUpdate);  // This will update orderToUpdate with the current data from the DB

        ProductBLL productBLL = new ProductBLL();
        Product product = productBLL.findProductById(productId);
        ClientBLL clientBLL = new ClientBLL();
        Client client = clientBLL.findClientById(clientId);
        // Add back the old quantity to the product stock before checking if there is enough stock for the new order
        product.setStock(product.getStock() + orderToUpdate.getQuantity());

        if (product.getName() != null && product.getStock() >= quantity) {
            orderToUpdate.setClientId(clientId);
            orderToUpdate.setProductId(productId);
            orderToUpdate.setQuantity(quantity);
            orderDAO.update(orderToUpdate);

            product.setStock(product.getStock()- orderToUpdate.getQuantity());
            productBLL.updateProduct(productId, product.getName(), product.getPrice(), product.getStock());

            Order orderDetails = orderDAO.getOrderDetails(orderToUpdate);

            // Create a new Bill object for this order
            int billId = Math.abs(UUID.randomUUID().hashCode());
            double billTotal = orderToUpdate.getQuantity();
            LocalDate billDate = LocalDate.now();
            return 1;
        }else return -1;
    }
    public void deleteOrder(int orderId) {
        orderDAO.delete(new Order(orderId));
    }

    public ArrayList<Order> getAllOrders() {
        return orderDAO.findAll();
    }}
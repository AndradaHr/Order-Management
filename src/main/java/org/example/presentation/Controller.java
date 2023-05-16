package org.example.presentation;
import org.example.bll.ClientBLL;
import org.example.bll.OrderBLL;
import org.example.bll.ProductBLL;
import org.example.model.Product;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Controller {
    private final MainWindow mainWindow;
    private final ClientBLL clientBLL = new ClientBLL();
    private final ProductBLL productBLL = new ProductBLL();
    private final OrderBLL orderBLL = new OrderBLL();

    public Controller(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        updateClientTableData();
        setupListeners();
    }

    private void setupListeners() {
        setupPanelChangeListeners();
        setupClientListeners();
        setupProductListeners();
        setupOrderListeners();
    }

    private void setupPanelChangeListeners() {
        mainWindow.getClientPanelButton().addActionListener(e -> changeToClientWindow());
        mainWindow.getProductPanelButton().addActionListener(e -> changeToProductWindow());
        mainWindow.getOrderPanelButton().addActionListener(e -> changeToOrderWindow());
    }

    private void setupClientListeners() {
        mainWindow.getClientPanel().getCreateButton().addActionListener(e -> addNewClient());
        mainWindow.getClientPanel().getRemoveButton().addActionListener(e -> deleteClient());
        mainWindow.getClientPanel().getModifyButton().addActionListener(e -> updateClient());
    }

    private void setupProductListeners() {
        mainWindow.getProductPanel().getCreateButton().addActionListener(e -> addNewProduct());
        mainWindow.getProductPanel().getModifyButton().addActionListener(e -> updateProduct());
        mainWindow.getProductPanel().getRemoveButton().addActionListener(e -> deleteProduct());
    }

    private void setupOrderListeners() {
        mainWindow.getOrderPanel().getCreateButton().addActionListener(e -> addNewOrder());
        mainWindow.getOrderPanel().getModifyButton().addActionListener(e -> updateOrder());
        mainWindow.getOrderPanel().getRemoveButton().addActionListener(e -> deleteOrder());
    }


    private void changeToClientWindow() {
        mainWindow.getCardLayout().show(mainWindow.getCardPanel(), "Client panel");
        mainWindow.getClientPanel().getClientsTableModel().setColumnCount(0);
        mainWindow.getClientPanel().getClientsTableModel().setRowCount(0);

        updateClientTableData();
    }

    private void changeToProductWindow() {
        mainWindow.getCardLayout().show(mainWindow.getCardPanel(), "Product panel");
        mainWindow.getProductPanel().getProductsTableModel().setColumnCount(0);
        mainWindow.getProductPanel().getProductsTableModel().setRowCount(0);

        updateProductTableData();
    }

    private void changeToOrderWindow() {
        mainWindow.getCardLayout().show(mainWindow.getCardPanel(), "Order panel");
        mainWindow.getOrderPanel().getTableModel().setColumnCount(0);
        mainWindow.getOrderPanel().getTableModel().setRowCount(0);

        updateOrderTableData();
    }

    private ArrayList<String> getFieldsName(Object object) {
        ArrayList<String> fieldsName = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            fieldsName.add(field.getName());
        }

        return fieldsName;
    }

    private void generateTableData(ArrayList<Object> objects, DefaultTableModel model) {
        model.setColumnCount(0);
        model.setRowCount(0);

        if (objects.size() > 0) {
            ArrayList<String> columnsName = getFieldsName(objects.get(0));

            /// Add columns to table
            for (String column : columnsName)
                model.addColumn(column);
            for (Object o : objects) {
                addObjectToTable(o, model);
            }
        }
    }

    private void addObjectToTable(Object object, DefaultTableModel model) {
        ArrayList<Object> row = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(object);
                row.add(value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        model.addRow(row.toArray());
    }
    private void addNewClient() {
        ClientPanel clientPanel = mainWindow.getClientPanel();
        String newClientName = clientPanel.getCreateNameField().getText();
        String newClientMail = clientPanel.getCreateEmailField().getText();
        String newClientAddress = clientPanel.getCreateAddressField().getText();
        String newClientAgeString = clientPanel.getCreateAgeField().getText();

        if (newClientName.isEmpty()) {
            mainWindow.showErrorMessage("Client name cannot be empty");
            return;
        }

        if (newClientMail.isEmpty() || !newClientMail.contains("@")) { // rudimentary email check, consider a more thorough validation
            mainWindow.showErrorMessage("Invalid email address");
            return;
        }

        if (newClientAddress.isEmpty()) {
            mainWindow.showErrorMessage("Client address cannot be empty");
            return;
        }

        int newClientAge;
        try {
            newClientAge = Integer.parseInt(newClientAgeString);
        } catch (NumberFormatException e) {
            mainWindow.showErrorMessage("Invalid age format");
            return;
        }

        if (newClientAge <= 0) {
            mainWindow.showErrorMessage("Age must be a positive number");
            return;
        }

        clientBLL.insertClient(newClientName, newClientMail, newClientAddress, newClientAge);
        updateClientTableData();

        clientPanel.getCreateNameField().setText("");
        clientPanel.getCreateEmailField().setText("");
        clientPanel.getCreateAddressField().setText("");
        clientPanel.getCreateAgeField().setText("");
    }


    private void updateClient() {
        ClientPanel clientPanel = mainWindow.getClientPanel();
        int id = Integer.parseInt(clientPanel.getModifyIdField().getText());
        String name = clientPanel.getModifyNameField().getText();
        String mail = clientPanel.getModifyEmailField().getText();
        String address = clientPanel.getModifyAddressField().getText();
        int age = Integer.parseInt(clientPanel.getModifyAgeField().getText());

        if (id >= 0 && age > 0 && !name.isEmpty() && !mail.isEmpty() && !address.isEmpty()) {
            clientBLL.updateClient(id, name, mail, address, age);
            updateClientTableData();

            clientPanel.getModifyIdField().setText("");
            clientPanel.getModifyNameField().setText("");
            clientPanel.getModifyEmailField().setText("");
            clientPanel.getModifyAddressField().setText("");
            clientPanel.getModifyAgeField().setText("");
        }
    }

    private void deleteClient() {
        ClientPanel clientPanel = mainWindow.getClientPanel();
        int clientId = Integer.parseInt(clientPanel. getRemoveIdField().getText());

        if (clientId >= 0) {
            clientBLL.deleteClient(clientId);

            updateClientTableData();

            clientPanel. getRemoveIdField().setText("");
        }
    }

    private void updateClientTableData() {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Object> objects = new ArrayList<>(clientBLL.getAllClients());
            generateTableData(objects, mainWindow.getClientPanel().getClientsTableModel());
        });
    }

    private void updateOrderTableData() {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Object> objects = new ArrayList<>(orderBLL.getAllOrders());
            generateTableData(objects, mainWindow.getOrderPanel().getTableModel());
        });
    }

    private void updateProductTableData() {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Object> objects = new ArrayList<>(productBLL.getAllProducts());
            generateTableData(objects, mainWindow.getProductPanel().getProductsTableModel());
        });
    }

    private void addNewProduct() {
        ProductPanel pp = mainWindow.getProductPanel();
        String name = pp.getCreateNameField().getText();
        int price = Integer.parseInt(pp.getCreatePriceField().getText());
        int stock = Integer.parseInt(pp.getCreateStockField().getText());

        if (!name.isEmpty() && price>=0 && stock >= 0) {
            productBLL.insertProduct(name, price, stock);
            updateProductTableData();
            pp.getCreateNameField().setText("");
            pp.getCreatePriceField().setText("");
            pp.getCreateStockField().setText("");
        }
    }
    private void updateProduct() {
        ProductPanel pp = mainWindow.getProductPanel();
        int productId = Integer.parseInt(pp.getModifyIdField().getText());
        String name = pp.getModifyNameField().getText();
        int price = Integer.parseInt(pp.getModifyPriceField().getText());
        int stock = Integer.parseInt(pp.getModifyStockField().getText());

        if (productId > 0 && !name.isEmpty() && price>=0) {
            productBLL.updateProduct(productId, name, price, stock);
            updateProductTableData();
            pp.getModifyIdField().setText("");
            pp.getModifyNameField().setText("");
            pp.getModifyPriceField().setText("");
            pp.getModifyStockField().setText("");
        }
    }
    private void deleteProduct() {
        int productId = Integer.parseInt(mainWindow.getProductPanel().getRemoveIdField().getText());

        if (productId > 0) {
            productBLL.deleteProduct(productId);
            updateProductTableData();
            mainWindow.getProductPanel().getRemoveIdField().setText("");
        }
    }

    private void addNewOrder() {
        OrderPanel op = mainWindow.getOrderPanel();
        int clientId = Integer.parseInt(op.getCreateClientIdField().getText());
        int productId = Integer.parseInt(op.getCreateProductIdField().getText());
        int quantity = Integer.parseInt(op.getCreateQuantityField().getText());

        if (clientId > 0 && productId > 0 && quantity > 0) {
            if (orderBLL.insertOrder(clientId, productId, quantity) == -1)
                mainWindow.showErrorMessage("Not enough stocks for the order!");
            else {
                updateOrderTableData();
                op.getCreateClientIdField().setText("");
                op.getCreateProductIdField().setText("");
                op.getCreateQuantityField().setText("");
            }
        }
    }
    private void updateOrder() {
        OrderPanel pp = mainWindow.getOrderPanel();
        int orderId = Integer.parseInt(pp.getModifyIdField().getText());
        int clientId = Integer.parseInt(pp.getModifyClientIdField().getText());
        int productId = Integer.parseInt(pp.getModifyProductIdField().getText());
        int quantity = Integer.parseInt(pp.getModifyQuantityField().getText());

        if (productId > 0 && clientId>0 && quantity>0) {
            if (orderBLL.updateOrder(orderId,clientId, productId, quantity) == -1)
                mainWindow.showErrorMessage("Not enough stocks for the order!");
            else{
                orderBLL.updateOrder(orderId,clientId,productId,quantity);
                updateOrderTableData();
                updateProductTableData();
                pp.getModifyIdField().setText("");
                pp.getModifyClientIdField().setText("");
                pp.getModifyProductIdField().setText("");
                pp.getModifyQuantityField().setText("");
            }}
    }
    private void deleteOrder() {
        int orderId = Integer.parseInt(mainWindow.getOrderPanel().getRemoveIdField().getText());
        if (orderId > 0) {
            Product product= productBLL.findProductById( orderBLL.findOrderById(orderId).getProductId());
            product.setStock(product.getStock()+orderBLL.findOrderById(orderId).getQuantity());
            productBLL.updateProduct(product.getId(), product.getName(), product.getPrice(), product.getStock());
            updateProductTableData();
            orderBLL.deleteOrder(orderId);
            updateOrderTableData();
            mainWindow.getOrderPanel().getRemoveIdField().setText("");
        }
    }
}

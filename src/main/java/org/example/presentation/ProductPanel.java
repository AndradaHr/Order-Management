package org.example.presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProductPanel {
    private JPanel mainPanel;
    private JLabel mainPanelLabel;
    private JTable productsTable;
    private JButton createButton, modifyButton, removeButton;
    private JTextField createNameField, createStockField, createPriceField;
    private JLabel createNameLabel, createStockLabel, createPriceLabel;
    private JTextField modifyIdField, modifyNameField, modifyPriceField, modifyStockField;
    private JLabel modifyIdLabel, modifyNameLabel, modifyPriceLabel, modifyStockLabel;
    private JLabel removeIdLabel;
    private JTextField removeIdField;
    private JScrollPane tableScrollPane;
    private DefaultTableModel productsTableModel;

    public ProductPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        mainPanelLabel = new JLabel("Products", SwingConstants.CENTER);
        mainPanelLabel.setBounds(0, 0, 700, 30);

        productsTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        productsTable = new JTable(productsTableModel);
        tableScrollPane = new JScrollPane(productsTable);
        tableScrollPane.setBounds(50, 50, 600, 200);

        createNameLabel = createAndAddLabel(mainPanel, "Product name", 25, 300);
        createNameField = createAndAddTextField(mainPanel, 25, 325);

        createPriceLabel = createAndAddLabel(mainPanel, "Price", 150, 300);
        createPriceField = createAndAddTextField(mainPanel, 150, 325);

        createStockLabel = createAndAddLabel(mainPanel, "Stock", 275, 300);
        createStockField = createAndAddTextField(mainPanel, 275, 325);

        createButton = createAndAddButton(mainPanel, "Add product", 400, 325);

        modifyIdLabel = createAndAddLabel(mainPanel, "Product id", 25, 375);
        modifyIdField = createAndAddTextField(mainPanel, 25, 400);

        modifyNameLabel = createAndAddLabel(mainPanel, "Name", 150, 375);
        modifyNameField = createAndAddTextField(mainPanel, 150, 400);

        modifyPriceLabel = createAndAddLabel(mainPanel, "Price", 275, 375);
        modifyPriceField = createAndAddTextField(mainPanel, 275, 400);

        modifyStockLabel = createAndAddLabel(mainPanel, "Stock", 400, 375);
        modifyStockField = createAndAddTextField(mainPanel, 400, 400);

        modifyButton = createAndAddButton(mainPanel, "Update product", 525, 400);

        removeIdLabel = createAndAddLabel(mainPanel, "Product id", 25, 450);
        removeIdField = createAndAddTextField(mainPanel, 25, 475);

        removeButton = createAndAddButton(mainPanel, "Delete product", 150, 475);

        mainPanel.add(mainPanelLabel);
        mainPanel.add(tableScrollPane);
    }

    public JTextField createAndAddTextField(JPanel panel, int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 100, 25);
        panel.add(textField);
        return textField;
    }

    public JLabel createAndAddLabel(JPanel panel, String labelText, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setBounds(x,y, 50, 25);
        panel.add(label);
        return label;
    }

    public JButton createAndAddButton(JPanel panel, String buttonText, int x, int y) {
        JButton button = new JButton(buttonText);
        button.setBounds(x, y, 150, 25);
        panel.add(button);
        return button;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JButton getModifyButton() {
        return modifyButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    public DefaultTableModel getProductsTableModel() {
        return productsTableModel;
    }

    public JTextField getCreateNameField() {
        return createNameField;
    }

    public JTextField getCreatePriceField() {
        return createPriceField;
    }

    public JTextField getCreateStockField() {
        return createStockField;
    }

    public JTextField getModifyIdField() {
        return modifyIdField;
    }

    public JTextField getModifyNameField() {
        return modifyNameField;
    }

    public JTextField getModifyPriceField() {
        return modifyPriceField;
    }

    public JTextField getModifyStockField() {
        return modifyStockField;
    }

    public JTextField getRemoveIdField() {
        return removeIdField;
    }
}


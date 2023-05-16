package org.example.presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OrderPanel {
    private JPanel mainPanel;
    private JLabel panelLabel;
    private JButton createButton, modifyButton, removeButton;
    private JTextField createClientIdField, createProductIdField, createQuantityField;
    private JLabel createClientIdLabel, createProductIdLabel, createQuantityLabel;
    private JTextField modifyClientIdField, modifyProductIdField, modifyQuantityField, modifyIdField;
    private JLabel modifyClientIdLabel, modifyProductIdLabel, modifyQuantityLabel, modifyIdLabel;
    private JLabel removeIdLabel;
    private JTextField removeIdField;
    private JScrollPane scrollPane;
    private DefaultTableModel transactionsTableModel;
    private JTable transactionsTable;

    public OrderPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        panelLabel = new JLabel("Orders", SwingConstants.CENTER);
        panelLabel.setBounds(0, 0, 700, 30);

        transactionsTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        transactionsTable = new JTable(transactionsTableModel);
        scrollPane = new JScrollPane(transactionsTable);
        scrollPane.setBounds(50, 50, 600, 200);

        mainPanel.add(panelLabel);
        mainPanel.add(scrollPane);

        createClientIdLabel = createAndAddLabel(mainPanel, "Client", 25, 300);
        createClientIdField = createAndAddTextField(mainPanel, 25, 325);

        createProductIdLabel = createAndAddLabel(mainPanel, "Product", 150, 300);
        createProductIdField = createAndAddTextField(mainPanel, 150, 325);

        createQuantityLabel = createAndAddLabel(mainPanel, "Quantity", 275, 300);
        createQuantityField = createAndAddTextField(mainPanel, 275, 325);

        createButton = createAndAddButton(mainPanel, "Add order", 400, 325);

        modifyIdLabel = createAndAddLabel(mainPanel, "Order id", 25, 375);
        modifyIdField = createAndAddTextField(mainPanel, 25, 400);

        modifyClientIdLabel = createAndAddLabel(mainPanel, "Client", 150, 375);
        modifyClientIdField = createAndAddTextField(mainPanel, 150, 400);

        modifyProductIdLabel = createAndAddLabel(mainPanel, "Product", 275, 375);
        modifyProductIdField = createAndAddTextField(mainPanel, 275, 400);

        modifyQuantityLabel = createAndAddLabel(mainPanel, "Quantity", 400, 375);
        modifyQuantityField = createAndAddTextField(mainPanel, 400, 400);

        modifyButton = createAndAddButton(mainPanel, "Update order", 525, 400);

        removeIdLabel = createAndAddLabel(mainPanel, "Order id", 25, 450);
        removeIdField = createAndAddTextField(mainPanel, 25, 475);

        removeButton = createAndAddButton(mainPanel, "Delete order", 180, 475);
    }
    public JTextField createAndAddTextField(JPanel panel, int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 100, 25);
        panel.add(textField);
        return textField;
    }

    public JLabel createAndAddLabel(JPanel panel, String labelText, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setBounds(x, y, 50, 25);
        panel.add(label);
        return label;
    }

    public JButton createAndAddButton(JPanel panel, String buttonText, int x, int y) {
        JButton button = new JButton(buttonText);
        button.setBounds(x, y, 150, 25);
        panel.add(button);
        return button;
    }
    public JPanel getPanel() {
        return mainPanel;
    }

    public DefaultTableModel getTableModel() {
        return transactionsTableModel;
    }

    public JTextField getCreateClientIdField() {
        return createClientIdField;
    }

    public JTextField getCreateProductIdField() {
        return createProductIdField;
    }

    public JTextField getCreateQuantityField() {
        return createQuantityField;
    }

    public JTextField getModifyClientIdField() {
        return modifyClientIdField;
    }

    public JTextField getModifyProductIdField() {
        return modifyProductIdField;
    }

    public JTextField getModifyQuantityField() {
        return modifyQuantityField;
    }

    public JTextField getModifyIdField() {
        return modifyIdField;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public JButton getModifyButton(){return modifyButton;}

    public JTextField getRemoveIdField() {
        return removeIdField;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }
}


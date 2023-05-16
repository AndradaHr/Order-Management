package org.example.presentation;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClientPanel {
    private JButton createButton, modifyButton, removeButton;
    private JTextField createNameField, createEmailField, createAddressField, createAgeField;
    private JLabel createNameLabel, createEmailLabel, createAddressLabel, createAgeLabel;
    private JTextField modifyIdField, modifyNameField, modifyEmailField, modifyAddressField, modifyAgeField;
    private JLabel modifyIdLabel, modifyNameLabel, modifyEmailLabel, modifyAddressLabel, modifyAgeLabel;
    private JLabel removeIdLabel;
    private JTextField removeIdField;
    private JPanel mainPanel;
    private JLabel mainPanelLabel;
    private JScrollPane tableScrollPane;
    private DefaultTableModel clientsTableModel;
    private JTable clientsDataTable;

    public ClientPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        mainPanelLabel = new JLabel("Clients", SwingConstants.CENTER);
        mainPanelLabel.setBounds(0, 0, 700, 30);

        clientsTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        clientsDataTable = new JTable(clientsTableModel);
        tableScrollPane = new JScrollPane(clientsDataTable);
        tableScrollPane.setBounds(50, 50, 600, 200);

        createNameLabel = createAndAddLabel(mainPanel, "Name", 25, 275);
        createNameField = createAndAddTextField(mainPanel, 25, 300);

        createEmailLabel = createAndAddLabel(mainPanel, "Mail", 150, 275);
        createEmailField = createAndAddTextField(mainPanel, 150, 300);

        createAddressLabel = createAndAddLabel(mainPanel, "Address", 275, 275);
        createAddressField = createAndAddTextField(mainPanel, 275, 300);

        createAgeLabel = createAndAddLabel(mainPanel, "Age", 400, 275);
        createAgeField = createAndAddTextField(mainPanel, 400, 300);

        createButton = createAndAddButton(mainPanel, "Add a new client", 525, 300);

        modifyIdLabel = createAndAddLabel(mainPanel, "Client id", 25, 350);
        modifyIdField = createAndAddTextField(mainPanel, 25, 375);

        modifyNameLabel = createAndAddLabel(mainPanel, "Name", 150, 350);
        modifyNameField = createAndAddTextField(mainPanel, 150, 375);

        modifyEmailLabel = createAndAddLabel(mainPanel, "Email", 275, 350);
        modifyEmailField = createAndAddTextField(mainPanel, 275, 375);

        modifyAddressLabel = createAndAddLabel(mainPanel, "Address", 400, 350);
        modifyAddressField = createAndAddTextField(mainPanel, 400, 375);

        modifyAgeLabel = createAndAddLabel(mainPanel, "Age", 525, 350);
        modifyAgeField = createAndAddTextField(mainPanel, 525, 375);

        modifyButton = createAndAddButton(mainPanel, "Update client", 475, 425);

        removeIdLabel = createAndAddLabel(mainPanel, "Client id", 25, 450);
        removeIdField = createAndAddTextField(mainPanel, 25, 475);

        removeButton = createAndAddButton(mainPanel, "Delete client", 180, 475);

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

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public DefaultTableModel getClientsTableModel() {
        return clientsTableModel;
    }

    public JTextField getCreateNameField() {
        return createNameField;
    }

    public JTextField getCreateEmailField() {
        return createEmailField;
    }

    public JTextField getCreateAddressField() {
        return createAddressField;
    }

    public JTextField getCreateAgeField() {
        return createAgeField;
    }

    public JTextField getModifyIdField() {
        return modifyIdField;
    }

    public JTextField getModifyNameField() {
        return modifyNameField;
    }

    public JTextField getModifyEmailField() {
        return modifyEmailField;
    }

    public JTextField getModifyAddressField() {
        return modifyAddressField;
    }

    public JTextField getModifyAgeField() {
        return modifyAgeField;
    }

    public JTextField getRemoveIdField() {
        return removeIdField;
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
}


package org.example.presentation;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private static final String FRAME_TITLE = "Order management";
    private static final String CLIENT_PANEL_NAME = "Client panel";
    private static final String PRODUCT_PANEL_NAME = "Product panel";
    private static final String ORDER_PANEL_NAME = "Order panel";

    private JFrame frame;
    private JPanel listPanel;
    private JPanel buttonsPanel;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    private JButton clientPanelButton;
    private JButton productPanelButton;
    private JButton orderPanelButton;

    private ClientPanel clientPanel;
    private ProductPanel productPanel;
    private OrderPanel orderPanel;

    public MainWindow() {
        setupFrame();
        setupPanel();
        setupCardPanel();
        setupButtons();

        frame.add(listPanel);
        frame.setSize(700, 600);
        frame.setVisible(true);
    }

    private void setupFrame() {
        frame = new JFrame(FRAME_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupPanel() {
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
    }

    private void setupCardPanel() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        clientPanel = new ClientPanel();
        productPanel = new ProductPanel();
        orderPanel = new OrderPanel();

        cardPanel.add(clientPanel.getMainPanel(), CLIENT_PANEL_NAME);
        cardPanel.add(productPanel.getMainPanel(), PRODUCT_PANEL_NAME);
        cardPanel.add(orderPanel.getPanel(), ORDER_PANEL_NAME);

        listPanel.add(cardPanel);
    }

    private void setupButtons() {
        buttonsPanel = new JPanel(new FlowLayout());

        clientPanelButton = new JButton(CLIENT_PANEL_NAME);
        productPanelButton = new JButton(PRODUCT_PANEL_NAME);
        orderPanelButton = new JButton(ORDER_PANEL_NAME);

        buttonsPanel.add(clientPanelButton);
        buttonsPanel.add(productPanelButton);
        buttonsPanel.add(orderPanelButton);

        listPanel.add(buttonsPanel);
    }

    public JButton getClientPanelButton() {
        return clientPanelButton;
    }

    public JButton getProductPanelButton() {
        return productPanelButton;
    }

    public JButton getOrderPanelButton() {
        return orderPanelButton;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public ClientPanel getClientPanel() {
        return clientPanel;
    }

    public ProductPanel getProductPanel() {
        return productPanel;
    }

    public OrderPanel getOrderPanel() {
        return orderPanel;
    }
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

package ui;

import model.Order;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OrderAppGui extends JFrame implements ActionListener {

    static final String JSON_STORE = "./data/currentOrder.json";
    JsonReader jsonReader;
    Order order;
    JPanel starterPanel;
    JPanel menuPanel;
    JButton loadOrder;
    JButton newOrder;

    //MODIFIES: this
    //EFFECTS: Constructs a frame to house the panels
    public OrderAppGui() {
        jsonReader = new JsonReader(JSON_STORE);
        order = new Order(true);
        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        starterPanel = new JPanel(new GridLayout());
        starterPanel.setPreferredSize(new Dimension(Order.WIDTH, Order.HEIGHT));

        loadOrder = new JButton("LOAD ORDER");
        newOrder = new JButton("NEW ORDER");
        loadOrder.setFont(new Font("sans serif", Font.PLAIN, 100));
        newOrder.setFont(new Font("sans serif", Font.PLAIN, 100));
        starterPanel.add(loadOrder);
        loadOrder.addActionListener(this);
        starterPanel.add(newOrder);
        newOrder.addActionListener(this);
        add(starterPanel);
    }

    // MODIFIES: this, e
    // EFFECTS: Performs actions on buttons in frame
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == loadOrder) {
            try {
                order = jsonReader.read();
            } catch (IOException ex) {
                loadOrder.setText("Unable to load order...");
            }

        }
        starterPanel.setVisible(false);
        menuPanel = new MenuPanel(order, this);
        add(menuPanel);
        menuPanel.setVisible(true);
    }
}
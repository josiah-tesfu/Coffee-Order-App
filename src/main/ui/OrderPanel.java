package ui;

import model.Drink;
import model.Order;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Arrays;


public class OrderPanel extends JPanel implements ActionListener {

    Order order;
    OrderAppGui orderAppGui;
    MenuPanel menuPanel;
    JPanel drinkList;
    JPanel orderSummary;
    JPanel[] drinkSummary;
    JScrollPane drinkListPane;
    JButton backToMenu;
    JButton saveOrder;
    JButton completeOrder;
    JButton[] removeBtn;
    static final String JSON_STORE = "./data/currentOrder.json";
    JsonWriter jsonWriter;
    JLabel total;
    JLabel tax;
    JLabel subtotal;

    // MODIFIES: this
    // EFFECTS: Constructs a panel to display the order
    public OrderPanel(Order order, OrderAppGui orderAppGui, MenuPanel menuPanel) {
        jsonWriter = new JsonWriter(JSON_STORE);
        this.orderAppGui = orderAppGui;
        this.menuPanel = menuPanel;
        this.order = order;
        removeBtn = new JButton[order.size()];
        setPreferredSize(new Dimension(Order.WIDTH, Order.HEIGHT));
        setLayout(new GridLayout(1, 2));

        drinkList = new JPanel();
        drinkListPane = new JScrollPane(drinkList);
        orderSummary = new JPanel();
        drinkSummary = new JPanel[order.size()];
        drinkList.setLayout(new BoxLayout(drinkList, BoxLayout.PAGE_AXIS));
        orderSummary.setLayout(new BoxLayout(orderSummary, BoxLayout.PAGE_AXIS));

        setDrinkList();
        setOrderSummary();
        this.add(drinkListPane);
        this.add(orderSummary);
    }

    // MODIFIES: this, panelTitle
    // EFFECTS: Adds a list of the drink items in the order to a panel
    public void setDrinkList() {
        JLabel panelTitle = new JLabel("Order Summary");
        panelTitle.setFont(new Font("sans serif", Font.PLAIN, 80));
        panelTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelTitle.setBorder(BorderFactory.createEmptyBorder(30, 5, 30, 5));
        drinkList.add(panelTitle);

        int index = 0;
        for (Drink drink : order.getDrinks()) {
            index = setDrinkSummary(index, drink);
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a list of components to a drink summary panel
    private int setDrinkSummary(int index, Drink drink) {
        drinkSummary[index] = new JPanel();
        drinkSummary[index].setLayout(new BoxLayout(drinkSummary[index], BoxLayout.PAGE_AXIS));
        addDrinkLabel(drink.getDrinkName(), index);
        addDrinkLabel("Size: " + drink.getDrinkSize(), index);
        for (int i = 0; i < 4; i++) {
            String addOnName = drink.getAddOn(i).getAddOnName();
            int addOnNum = drink.getAddOn(i).getAddOnNum();
            if (addOnNum != 0) {
                addDrinkLabel(addOnName + " x" + addOnNum, index);
            }
        }
        addDrinkLabel("Price: $" + drink.getDrinkPrice(), index);

        removeBtn[index] = new JButton("remove item ");
        removeBtn[index].setFont(new Font("sans serif", Font.PLAIN, 50));
        drinkSummary[index].add(removeBtn[index]);
        removeBtn[index].addActionListener(this);

        drinkSummary[index].setAlignmentX(Component.CENTER_ALIGNMENT);
        drinkSummary[index].setBorder(BorderFactory.createEmptyBorder(30, 5, 30, 5));
        drinkList.add(drinkSummary[index]);
        index++;
        return index;
    }

    // MODIFIES: this
    // EFFECTS: Adds components to the order summary panel
    public void setOrderSummary() {
        backToMenu = addSummaryButton("Back to Menu");
        Component space = Box.createVerticalStrut(600);
        orderSummary.add(space);
        total = addSummaryLabel("Total: $" + order.getTotal());
        tax = addSummaryLabel("Tax: $" + order.getTax());
        subtotal = addSummaryLabel("Subtotal: $" + order.getSubtotal());

        saveOrder = addSummaryButton("Save Order");
        completeOrder = addSummaryButton("Complete Order");
    }

    // MODIFIES: label
    // EFFECTS: Constructs a label and adds it to the panel for a specific drink item
    public void addDrinkLabel(String drinkLabel, int index) {
        JLabel label = new JLabel(drinkLabel);
        label.setFont(new Font("sans serif", Font.PLAIN, 50));
        drinkSummary[index].add(label);
    }

    // MODIFIES: label
    // EFFECTS: Constructs a label and adds it to the order summary panel
    public JLabel addSummaryLabel(String labelText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("sans serif", Font.PLAIN, 50));
        orderSummary.add(label);
        return label;
    }

    // MODIFIES: this, button
    // EFFECTS: Adds a button to the order summary panel and returns it
    public JButton addSummaryButton(String buttonText) {
        JButton button;
        button = new JButton(buttonText);
        button.addActionListener(this);
        button.setFont(new Font("sans serif", Font.PLAIN, 50));
        orderSummary.add(button);
        return button;
    }

    // MODIFIES: this, e
    // EFFECTS: Performs actions on buttons in the order panel
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        int orderSize = order.size();

        if (obj instanceof JButton) {
            if (Arrays.asList(removeBtn).contains(obj)) {
                removeBtnAction(obj, orderSize);

            } else if (obj == saveOrder) {
                saveOrderAction();

            } else if (obj == backToMenu) {
                backToMenuAction();
            } else if (obj == completeOrder) {
                completeOrderAction(obj);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Closes this and opens the menu panel
    private void backToMenuAction() {
        this.setVisible(false);
        orderAppGui.add(menuPanel);
        menuPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Saves the order to a file
    private void saveOrderAction() {
        try {
            jsonWriter.open();
            jsonWriter.write(order);
            jsonWriter.close();
            saveOrder.setText("Saved order to file " + JSON_STORE);
        } catch (FileNotFoundException ex) {
            saveOrder.setText("Unable to save file...");
        }
    }

    // MODIFIES: this
    // EFFECTS: Removes a specified drink from the order
    private void removeBtnAction(Object obj, int orderSize) {
        for (int i = 0; i < orderSize; i++) {
            if (obj == removeBtn[i]) {
                order.removeFromOrder(i);
                drinkSummary[i].setVisible(false);
                total.setText("Total: $" + order.getTotal());
                tax.setText("Tax: $" + order.getTax());
                subtotal.setText("Subtotal: $" + order.getSubtotal());
            }
        }
    }

    private void completeOrderAction(Object obj) {
        orderAppGui.dispose();
    }
}

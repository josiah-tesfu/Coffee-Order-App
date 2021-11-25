package ui;

import model.Drink;
import model.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

// Represents a panel which displays drink which can be customized and added to the order
public class DrinkPanel extends JPanel implements ActionListener {

    private final Order order;
    private final Drink drink;
    private final MenuPanel menuPanel;
    private final OrderAppGui orderAppGui;
    private final GridBagConstraints constraints;
    private JButton backToMenuBtn;
    private JButton viewOrderBtn;
    private JButton sizeBtn;
    private JButton addToOrderBtn;
    private final String drinkName;
    private final JButton[] addOnBtn;
    private final int[] addOnCount;

    // MODIFIES: this
    // EFFECTS: Constructs a panel fora specific drink in the order
    public DrinkPanel(Order order, OrderAppGui orderAppGui, MenuPanel menuPanel, String drinkName) {
        this.drinkName = drinkName;
        this.orderAppGui = orderAppGui;
        this.menuPanel = menuPanel;
        this.order = order;
        addOnCount = new int[4];
        addOnBtn = new JButton[4];
        drink = new Drink(drinkName.toLowerCase(), order.isToGo());
        order.addToOrder(drink);
        setPreferredSize(new Dimension(Order.WIDTH, Order.HEIGHT));
        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        addComponents(constraints);
    }

    // MODIFIES: this
    // EFFECTS: Adds the components to the drink panel
    public void addComponents(GridBagConstraints constraints) {

        backToMenuBtn = addButton("back to menu", 0, 0, 100, 30);
        viewOrderBtn = addButton("view order", 3, 0, 100, 30);
        sizeBtn = addButton("small", 0, 2, 200, 30);
        addOnBtn[0] = addOnButton("milk", 0, 3, 80, 30);
        addOnBtn[1] = addOnButton("sugar", 1, 3, 80, 30);
        addOnBtn[2] = addOnButton("cream", 0, 4, 80, 30);
        addOnBtn[3] = addOnButton("esp. shot", 1, 4, 60, 30);
        addToOrderBtn = addOnButton("add to order", 0, 5, 100, 30);

        JLabel drinkPanelLbl = new JLabel(drinkName);
        specifyConstraints(0, 1, 200, 200);
        this.add(drinkPanelLbl, constraints);
        drinkPanelLbl.setFont(new Font("sans serif", Font.PLAIN, 50));

    }

    // MODIFIES: name
    // EFFECTS: Adds a JButton to the drink panel and returns it
    public JButton addButton(String name, int gridX, int gridY, int ipadX, int ipadY) {
        JButton button = new JButton(name);
        specifyConstraints(gridX, gridY, ipadX, ipadY);
        button.setFont(new Font("sans serif", Font.PLAIN, 30));
        this.add(button, constraints);
        button.addActionListener(this);
        return button;
    }

    // MODIFIES: button
    // EFFECTS: Adds an add-on button to the drink panel and returns it
    public JButton addOnButton(String name, int gridX, int gridY, int ipadX, int ipadY) {
        JButton button = new JButton(name + " + ");
        specifyConstraints(gridX, gridY, ipadX, ipadY);
        button.setFont(new Font("sans serif", Font.PLAIN, 30));
        this.add(button, constraints);
        button.addActionListener(this);
        return button;
    }

    // EFFECTS: Specifies GridBagConstraints for the drink panel
    private void specifyConstraints(int gridX, int gridY, int ipadX, int ipadY) {
        constraints.ipadx  = ipadX;
        constraints.ipady = ipadY;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
    }

    // MODIFIES: e
    // EFFECTS: Performs actions on buttons in the drink panel
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj instanceof JButton) {

            if (obj == backToMenuBtn) {
                backToMenuBtnAction();
            } else if (obj == sizeBtn) {
                sizeBtnAction();
            } else if (Arrays.asList(addOnBtn).contains(obj)) {
                addOnBtnAction(obj);
            } else if (obj == addToOrderBtn) {
                addToOrderBtnAction();
            } else if (obj == viewOrderBtn) {
                viewOrderBtnAction();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Closes this and opens the order panel
    private void viewOrderBtnAction() {
        OrderPanel orderPanel;
        this.setVisible(false);
        orderPanel = new OrderPanel(order, orderAppGui, menuPanel);
        orderAppGui.add(orderPanel);
        orderPanel.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Adds the drink to the order
    private void addToOrderBtnAction() {
        sizeBtn.setVisible(false);
        for (JButton button : addOnBtn) {
            button.setVisible(false);
        }
        order.addPriceToDrink(drink);
        addToOrderBtn.setText(drinkName + " added to order");
        order.setEvent(drink.getDrinkName() + " has been added to the order");
    }

    // MODIFIES: this
    // EFFECTS: Modifies the drink size
    private void sizeBtnAction() {
        if (sizeBtn.getText().equals("small")) {
            sizeBtn.setText("medium");
        } else if (sizeBtn.getText().equals("medium")) {
            sizeBtn.setText("large");
        } else if (sizeBtn.getText().equals("large")) {
            sizeBtn.setText("small");
        }
        order.getDrink(drinkName).setDrinkSize(sizeBtn.getText());
    }

    // MODIFIES: this
    // EFFECTS: Closes this and opens the menu panel
    private void backToMenuBtnAction() {
        this.setVisible(false);
        orderAppGui.add(menuPanel);
        menuPanel.setVisible(true);
    }

    // EFFECTS: Performs actions on add-on buttons
    private void addOnBtnAction(Object obj) {
        addOnAction(obj, "milk", "milk", 0);
        addOnAction(obj, "sugar", "sugar", 1);
        addOnAction(obj, "cream", "cream",  2);
        addOnAction(obj, "esp. shot", "espresso shot", 3);
    }

    // MODIFIES: this, addOnCount
    // EFFECTS: Adds add-on to the drink
    public void addOnAction(Object obj, String name, String name2, int i) {
        if (obj == addOnBtn[i]) {
            addOnCount[i]++;
            addOnBtn[i].setText(name + " + " + addOnCount[i]);
            drink.addOn(name2);
        }
    }
}
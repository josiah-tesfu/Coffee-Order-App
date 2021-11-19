package ui;

import model.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {
    DrinkPanel drinkPanel;
    OrderPanel orderPanel;
    OrderAppGui orderAppGui;
    Order order;
    JButton[] drinkButtons;
    JButton orderButton;
    JLabel label;
    GridBagConstraints constraints;

    //MODIFIES: this
    //EFFECTS: Constructs a panel that displays the menu
    public MenuPanel(Order order, OrderAppGui orderAppGui) {
        this.orderAppGui = orderAppGui;
        this.order = order;
        drinkButtons = new JButton[6];
        setPreferredSize(new Dimension(Order.WIDTH, Order.HEIGHT));

        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        addTopComponents(constraints);
        addDrinkComponents(constraints);
    }

    // MODIFIES: this
    // EFFECTS: Sets the constraints for the menu panel layout
    private void specifyConstraints(double weightX, double weightY, int gridX, int gridY) {
        constraints.ipadx = 100;
        constraints.ipady = 100;
        constraints.weightx = weightX;
        constraints.weighty = weightY;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
    }

    // EFFECTS: Returns a JButton for the menu panel
    public JButton drinkButton(String name) {
        JButton button;
        Icon icon = new ImageIcon("./data/coffee_cup.png");
        button = new JButton(name, icon);

        button.setVerticalTextPosition(AbstractButton.TOP);
        button.setHorizontalTextPosition(AbstractButton.CENTER);
        button.setFont(new Font("sans serif", Font.PLAIN, 30));
        button.addActionListener(this);
        return button;
    }

    // MODIFIES: this
    // EFFECTS: Adds the drink buttons to the menu panel
    private void addDrinkComponents(GridBagConstraints c) {
        drinkButtons[0] = drinkButton("Drip Coffee");
        specifyConstraints(0.5, 0.5, 0, 1);
        this.add(drinkButtons[0], c);

        drinkButtons[1] = drinkButton("Americano");
        specifyConstraints(0.5, 0.5, 1, 1);
        this.add(drinkButtons[1], c);

        drinkButtons[2] = drinkButton("Latte");
        specifyConstraints(0.5, 0.5, 2, 1);
        this.add(drinkButtons[2], c);

        drinkButtons[3] = drinkButton("Cappuccino");
        specifyConstraints(0.5, 0.5, 0, 2);
        this.add(drinkButtons[3], c);

        drinkButtons[4] = drinkButton("Espresso");
        specifyConstraints(0.5, 0.5, 1, 2);
        this.add(drinkButtons[4], c);

        drinkButtons[5] = drinkButton("Macchiato");
        specifyConstraints(0.5, 0.5, 2, 2);
        this.add(drinkButtons[5], c);
    }

    // MODIFIES: this
    // EFFECTS: Adds the components at the top of the panel
    private void addTopComponents(GridBagConstraints c) {
        label = new JLabel("Select drink");
        label.setFont(new Font("sans serif", Font.PLAIN, 80));
        specifyConstraints(1, 1, 0, 0);
        this.add(label, c);

        orderButton = new JButton("VIEW ORDER");
        orderButton.setFont(new Font("sans serif", Font.PLAIN, 30));
        specifyConstraints(1, 1, 3, 0);
        constraints.ipadx = 30;
        constraints.ipady = 30;
        this.add(orderButton, c);
        orderButton.addActionListener(this);
    }

    // MODIFIES: this, e
    // EFFECTS: Performs actions on buttons in the menu panel
    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if (obj == orderButton) {
            this.setVisible(false);
            orderPanel = new OrderPanel(order, orderAppGui, this);
            orderAppGui.add(orderPanel);
            orderPanel.setVisible(true);

        } else {
            for (JButton drinkButton : drinkButtons) {
                if (obj == drinkButton) {
                    drinkPanel = new DrinkPanel(order, orderAppGui, this, drinkButton.getText());
                    this.setVisible(false);
                    orderAppGui.add(drinkPanel);
                    drinkPanel.setVisible(true);
                }
            }
        }

    }
}
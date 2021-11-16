package ui;

import model.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*

In this class:

 - create JPanel for drinks menu

 JPanel contains components:
  - label for panel
  - buttons for each of the drinks
    - each button opens DrinkPanel for that drink
  - button for view order
    - opens OrderPanel

- components layout: GridBagLayout


 */

public class MenuPanel extends JPanel implements ActionListener {

    Order order;
    JButton drinkButton;
    JButton orderButton;
    JLabel label;
    GridBagConstraints constraints;

    public MenuPanel(Order order) {
        this.order = order;
        setPreferredSize(new Dimension(Order.WIDTH, Order.HEIGHT));

        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        addTopComponents(constraints);
        addDrinkComponents(constraints);
    }

    private void specifyConstraints(double weightX, double weightY, int gridX, int gridY) {
        constraints.ipadx = 100;
        constraints.ipady = 100;
        constraints.weightx = weightX;
        constraints.weighty = weightY;
        constraints.gridx = gridX;
        constraints.gridy = gridY;
    }

    public void drinkButton(String name) {
        Icon icon = new ImageIcon("./data/coffee_cup.png");
        drinkButton = new JButton(name, icon);

        drinkButton.setVerticalTextPosition(AbstractButton.TOP);
        drinkButton.setHorizontalTextPosition(AbstractButton.CENTER);
        drinkButton.setFont(new Font("sans serif", Font.PLAIN, 30));
        drinkButton.addActionListener(this);

    }

    private void addDrinkComponents(GridBagConstraints c) {
        drinkButton("Drip Coffee");
        specifyConstraints(0.5, 0.5, 0, 1);
        this.add(drinkButton, c);

        drinkButton("Americano");
        specifyConstraints(0.5, 0.5, 1, 1);
        this.add(drinkButton, c);

        drinkButton("Latte");
        specifyConstraints(0.5, 0.5, 2, 1);
        this.add(drinkButton, c);

        drinkButton("Cappuccino");
        specifyConstraints(0.5, 0.5, 0, 2);
        this.add(drinkButton, c);

        drinkButton("Espresso");
        specifyConstraints(0.5, 0.5, 1, 2);
        this.add(drinkButton, c);

        drinkButton("Macchiato");
        specifyConstraints(0.5, 0.5, 2, 2);
        this.add(drinkButton, c);
    }

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


    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        this.setVisible(obj == orderButton);

    }
}

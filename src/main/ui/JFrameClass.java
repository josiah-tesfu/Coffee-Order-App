package ui;

import model.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameClass extends JFrame {

    Order order;
    JPanel starterPanel;
    JPanel menuPanel;
    JButton loadOrder;
    JButton newOrder;

    public JFrameClass() {
        setVisible(true);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuPanel = new MenuPanel(order);
        add(menuPanel);

        starterPanel = new JPanel(new GridLayout());
        starterPanel.setPreferredSize(new Dimension(Order.WIDTH, Order.HEIGHT));
        JButton loadOrder = new JButton("LOAD ORDER");
        JButton newOrder = new JButton("NEW ORDER");
        loadOrder.setFont(new Font("sans serif", Font.PLAIN, 100));
        newOrder.setFont(new Font("sans serif", Font.PLAIN, 100));
        starterPanel.add(loadOrder);
        starterPanel.add(newOrder);
        add(starterPanel);
        loadOrder.addActionListener(e -> starterPanel.setVisible(false));

        newOrder.addActionListener(e -> {
            starterPanel.setVisible(false);
            add(menuPanel);
            menuPanel.setVisible(true);
        });

    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object obj = e.getSource();
//        if (obj == loadOrder) {
//            starterPanel.setVisible(false);
//        } else if (obj == newOrder) {
//            starterPanel.setVisible(false);
//            add(menuPanel);
//            menuPanel.setVisible(true);
//        } else {
//            starterPanel.setVisible(false);
//            add(menuPanel);
//            menuPanel.setVisible(true);
//        }
//    }

    /*

    add MenuPanel to the JFrame
    add DrinkPanel to the JFrame
    add OrderPanel to the JFrame

    in MenuPanel:
     - buttons to DrinkPanel
     - button to OrderPanel

    in DrinkPanel:
     - call to OrderPanel
     - select drink size (button)
     - add add-ons (button)
     - add to order (button)


     */

}

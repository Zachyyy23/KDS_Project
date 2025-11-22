package SimplePOS.gui;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

import SimplePOS.model.Order;
import SimplePOS.services.OrderCounter;


public class POSFrame extends JFrame {

    private OrderCounter orderCounter;
    private JTextArea orderDisplay;

    public POSFrame(OrderCounter orderCounter) {
        this.orderCounter = orderCounter;

        setTitle("POS");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        orderDisplay = new JTextArea();
        orderDisplay.setEditable(false);
        add(new JScrollPane(orderDisplay), BorderLayout.CENTER);


        JPanel buttons  = new JPanel();
        buttons.setLayout(new GridLayout(3,3));

        addItemButton(buttons, "Hell's Burger", 715.99);
        addItemButton(buttons, "Salmon Tartare", 320.99);
        addItemButton(buttons, "Steak Frites", 120.50);
        addItemButton(buttons, "Texas Style Chicken", 75);
        addItemButton(buttons, "Grilled Salmon", 350.23);
        addItemButton(buttons, "Braised Beef", 300.50);
        addItemButton(buttons, "Spring Rolls", 170.90);
        addItemButton(buttons, "White Rice", 180.56);
        addItemButton(buttons, "Yang Chao Rice", 210.99);

        add(buttons, BorderLayout.WEST);

        setVisible(true);
    }

    private void addItemButton(JPanel panel, String name, double price) {

        JButton btn = new JButton(name);

        btn.addActionListener(e -> {
            Order updated = orderCounter.addIncrement(name, price);
            refreshDisplay();
        });

        panel.add(btn);
    }

    private void refreshDisplay() {
        StringBuilder sb = new StringBuilder();
        for (Order a: orderCounter.getOrders()) {
            sb.append(a).append("\n");
        }
        orderDisplay.setText(sb.toString());
    }
}

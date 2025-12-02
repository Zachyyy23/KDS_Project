package KDS.gui;

import KDS.services.KDSService;
import SimplePOS.model.Order;

import javax.swing.*;
import java.awt.*;

public class KDSFrame extends JFrame {

    private JPanel listPanel = new JPanel();

    public KDSFrame() {
        setTitle("Kitchen Display System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scroll, BorderLayout.CENTER);

        new Timer(500, e -> refresh()).start();

        setVisible(true);
    }

    private void refresh() {
        listPanel.removeAll();

        for (Order a : KDSService.getOrders()) {
            listPanel.add(new OrderRowPanel(a));
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    private class OrderRowPanel extends JPanel {

        private Order order;
        private JLabel label;

        public OrderRowPanel(Order order) {
            this.order = order;

            setLayout(new BorderLayout());
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            label = new JLabel(order.toString());
            add(label, BorderLayout.CENTER);

            JPanel buttons = new JPanel();
            JButton prep = new JButton("Preparing");
            JButton done = new JButton("Done");

            prep.addActionListener(e -> {
                KDSService.markPreparing(order);
                label.setText(order.toString());
            });

            done.addActionListener(e -> {
                KDSService.markDone(order);
                label.setText(order.toString());
            });

            buttons.add(prep);
            buttons.add(done);

            add(buttons, BorderLayout.EAST);
        }
    }
}

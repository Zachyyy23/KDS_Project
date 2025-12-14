package KDS.program;

import KDS.services.KDSService;
import SimplePOS.model.Order;

import javax.swing.*;
import java.awt.*;

public class KDSFrame extends JFrame {

    private JPanel listPanel = new JPanel();

    public KDSFrame() {
        setTitle("Kitchen Display System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10,10));

        JScrollPane scroll = new JScrollPane(listPanel);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scroll, BorderLayout.CENTER);

        new Timer(700, e -> refresh()).start();

        setVisible(true);
    }

    private void refresh() {
        listPanel.removeAll();

        for (Order a : KDSService.getOrders()) {
            if (a.getStatus().equals("Done")) {
                continue;
            }
            listPanel.add(new OrderTicket(a));
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    private class OrderTicket extends JPanel {

        private Order order;

        public OrderTicket(Order order) {
            this.order = order;

            setPreferredSize(new Dimension(250,230));
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            updateColorByTime();

            JPanel body = new JPanel();
            body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
            body.setBackground(getBackground());
            body.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel item = new JLabel(order.getName());
            item.setFont(new Font("SansSerif", Font.BOLD, 16));

            JLabel qty = new JLabel("Qty: " + order.getQuantity());
            JLabel status = new JLabel("Status: " + order.getStatus());

            long elapsedMinutes = (System.currentTimeMillis() - order.getTimeStart()) / 60000;
            JLabel time = new JLabel("Time: " + elapsedMinutes + " min");

            body.add(item);
            body.add(Box.createVerticalStrut(5));
            body.add(qty);
            body.add(status);

            add(body, BorderLayout.CENTER);

            JButton actionBtn = new JButton();
            updateButtonText(actionBtn);

            actionBtn.addActionListener(e -> {
                if (order.getStatus().equals("Pending")) {
                    KDSService.markPreparing(order);
                } else {
                    KDSService.markDone(order);
                }
                updateButtonText(actionBtn);
            });

            JPanel footer = new JPanel();
            footer.add(actionBtn);
            add(footer, BorderLayout.SOUTH);
        }

        private void updateColorByTime() {
            long elapsedMillis = System.currentTimeMillis() - order.getTimeStart();
            long elapsedMinutes = elapsedMillis / 60000;

            if (elapsedMinutes >= 30) {
                setBackground(new Color(244, 67, 54));   // Red
            } else if (elapsedMinutes >= 20) {
                setBackground(new Color(255, 152, 0));   // Orange
            } else if (elapsedMinutes >= 5) {
                setBackground(new Color(255, 235, 59));  // Yellow
            } else {
                setBackground(Color.WHITE);
            }
        }

        private void updateButtonText(JButton btn) {
            if (order.getStatus().equals("Pending")) {
                btn.setText("Prepare");
            } else if (order.getStatus().equals("Preparing")) {
                btn.setText("Done");
            }
        }
    }
}

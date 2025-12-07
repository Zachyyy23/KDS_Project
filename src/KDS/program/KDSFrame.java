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
            listPanel.add(new OrderTicket(a));
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    private class OrderTicket extends JPanel {

        private Order order;
        private JLabel label;

        public OrderTicket(Order order) {
            this.order = order;

            setPreferredSize(new Dimension(250, 230));
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            setBackground(Color.WHITE);


            JPanel header = new JPanel(new BorderLayout());
            header.setPreferredSize(new Dimension(250, 40));
            header.setBackground(new Color(66, 133, 244));
/*

            JLabel title = new JLabel(order.getCustomerName());
            title.setForeground(Color.WHITE);
            title.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 5));


            header.add(title, BorderLayout.CENTER);
            add(header, BorderLayout.NORTH);
*/

            JPanel body = new JPanel();
            body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
            body.setBackground(Color.WHITE);
            body.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

/*
            for (String item : order.getItems()) {
                JLabel lbl = new JLabel("• " + item);
                lbl.setFont(new Font("SansSerif", Font.BOLD, 13));
                body.add(lbl);
            }
*/

            add(body, BorderLayout.CENTER);


            JButton done = new JButton("Done");
            done.addActionListener(e -> {
                KDSService.markDone(order);
            });


            JPanel footer = new JPanel();
            footer.add(done);
            add(footer, BorderLayout.SOUTH);
        }
    }
}

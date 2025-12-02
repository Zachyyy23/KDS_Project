package Launcher;

import SimplePOS.gui.POSFrame;
import SimplePOS.model.Order;
import SimplePOS.services.OrderCounter;
import KDS.gui.KDSFrame;

import java.util.ArrayList;

public class MainApp {
    public static void main(String[] args) {

        OrderCounter counter = new OrderCounter(new ArrayList<Order>());

        javax.swing.SwingUtilities.invokeLater(() -> {
            new POSFrame(counter);
            new KDSFrame();
        });
    }
}

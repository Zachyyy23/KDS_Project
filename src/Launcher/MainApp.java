package Launcher;

import SimplePOS.model.Order;
import SimplePOS.program.POSFrame;
import SimplePOS.services.OrderCounter;
import KDS.program.KDSFrame;

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

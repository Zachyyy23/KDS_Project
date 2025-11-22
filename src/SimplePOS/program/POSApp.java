package SimplePOS.program;

import java.util.ArrayList;

import SimplePOS.gui.POSFrame;
import SimplePOS.model.Order;
import SimplePOS.services.OrderCounter;


public class POSApp {

    public static ArrayList<Order> orders = new ArrayList<>();


    public static void main (String[] args) {

        OrderCounter counter = new OrderCounter(orders);

        new POSFrame(counter);

    }
}

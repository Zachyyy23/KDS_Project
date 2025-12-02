package KDS.services;

import SimplePOS.model.Order;
import shared.OrderRepo;
import java.util.List;

public class KDSService {

    public static List<Order> getOrders() {
        return OrderRepo.getAll();
    }

    public static void markPreparing(Order a) {
        a.setStatus("Preparing");
    }

    public static void markDone(Order a) {
        a.setStatus("Done");
    }
}

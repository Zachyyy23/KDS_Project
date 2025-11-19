package SimplePOS.program;
import java.util.Scanner;
import SimplePOS.model.Order;
import java.util.ArrayList;

public class POSApp {

    public static ArrayList<Order> orders = new ArrayList<>();


    public static void main (String[] args) {

        Scanner inputOrder = new Scanner(System.in);
        int nextID = 001;

        while (true) {
            System.out.println("Add item name (or type exit): ");
            String name = inputOrder.nextLine();
            if (name.equalsIgnoreCase("exit")) break;

            System.out.println("Quantity: ");
            int quantity = inputOrder.nextInt();
            inputOrder.nextLine();

            System.out.println("Enter Cost: ");
            double cost = inputOrder.nextDouble();
            inputOrder.nextLine();

            Order a = new Order(nextID, name, quantity, cost, "Pending");
            orders.add(a);
            nextID++;

            System.out.println("Added new Order: " + a);



        }

    }
}

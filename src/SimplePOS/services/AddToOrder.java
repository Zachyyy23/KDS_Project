package SimplePOS.services;

import SimplePOS.model.Order;
import shared.OrderRepo;

import java.util.Scanner;

public class AddToOrder {

    private Scanner inputOrder = new Scanner(System.in);
    private int nextID = 1;

    public void takeOrder() {
        System.out.print("Add item name (or type exit): ");
        String name = inputOrder.nextLine();

        if(name.equalsIgnoreCase("exit")) {
            System.out.println("Program closing...");
            System.exit(0);
        }

        System.out.print("Quantity: ");
        int quantity = inputOrder.nextInt();
        inputOrder.nextLine();

        System.out.print("Enter Cost: ");
        double cost = inputOrder.nextDouble();
        inputOrder.nextLine();

        Order a = new Order(nextID, name, quantity, cost, "Pending");
        OrderRepo.add(a);
        nextID++;

        System.out.println("Added new Order: " + a);

    }


}
package scmx.model;

import java.util.ArrayList;
import java.util.List;

public class Supplier implements SupplyChainNode {
    private String name;
    private Inventory rawMaterialInventory;
    private List<Order> incomingOrders = new ArrayList<>();
    private List<Order> outgoingOrders = new ArrayList<>();

    public Supplier(String name, Product product, int initialStock) {
        this.name = name;
        this.rawMaterialInventory = new Inventory(product, initialStock);
    }

    @Override
    public void receiveOrder(Order order) {
        incomingOrders.add(order);
    }

    @Override
    public void shipOrder(Order order) {
        if (rawMaterialInventory.getQuantity() >= order.getQuantity()) {
            rawMaterialInventory.removeStock(order.getQuantity());
            Order shipment = new Order(
                order.getProduct(),
                order.getQuantity(),
                this,
                order.getReceiver(),
                1 // assume 1-day delivery
            );
            outgoingOrders.add(shipment);
            System.out.println(name + " shipped " + order.getQuantity() + " units to " + order.getReceiver().getName());
        } else {
            System.out.println(name + " cannot fulfill order due to insufficient raw materials.");
        }
    }

    @Override
    public void processDay() {
        // Step 1: Ship orders if possible
        List<Order> delivered = new ArrayList<>();
        for (Order order : outgoingOrders) {
            order.decrementDaysToDelivery();
            if (order.getDaysToDelivery() == 0) {
                order.getReceiver().receiveOrder(order);
                delivered.add(order);
                System.out.println(name + " delivered order to " + order.getReceiver().getName());
            }
        }
        // Remove delivered orders after iteration to avoid ConcurrentModificationException
        outgoingOrders.removeAll(delivered);

        // Step 2: Process incoming orders by shipping them if possible
        List<Order> fulfilled = new ArrayList<>();
        for (Order order : incomingOrders) {
            shipOrder(order);
            fulfilled.add(order);
        }
        incomingOrders.removeAll(fulfilled);
    }

    @Override
    public String getName() {
        return name;
    }

    public int getStock() {
        return rawMaterialInventory.getQuantity();
    }
}

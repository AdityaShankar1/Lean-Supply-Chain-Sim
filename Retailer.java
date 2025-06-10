// Retailer.java
package scmx.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Retailer implements SupplyChainNode {
    private String name;
    private Inventory finishedGoodsInventory = new Inventory(null, 0);
    private Manufacturer manufacturer;
    private List<Order> incomingOrders = new ArrayList<>();
    private static final int MIN_DEMAND = 5;
    private static final int MAX_DEMAND = 15;

    public Retailer(String name, Manufacturer manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.finishedGoodsInventory = new Inventory(manufacturer.getProduct(), 0);
    }

    @Override
    public void receiveOrder(Order order) {
        finishedGoodsInventory.addStock(order.getQuantity());
    }

    @Override
    public void shipOrder(Order order) {
        // Not used
    }

    @Override
    public void processDay() {
        int demand = new Random().nextInt(MAX_DEMAND - MIN_DEMAND + 1) + MIN_DEMAND;

        if (finishedGoodsInventory.removeStock(demand)) {
            System.out.println(name + " fulfilled customer demand of " + demand + " units.");
        } else {
            System.out.println(name + " could not fulfill demand of " + demand + " units (insufficient stock).");
        }

        if (finishedGoodsInventory.getQuantity() < 10) {
            int orderQty = 20;
            Order order = new Order(
                manufacturer.getProduct(),
                orderQty,
                this,
                manufacturer,
                2
            );
            manufacturer.receiveOrder(order);
            System.out.println(name + " ordered " + orderQty + " units from Manufacturer");
        }
    }

    @Override
    public String getName() {
        return name;
    }
}

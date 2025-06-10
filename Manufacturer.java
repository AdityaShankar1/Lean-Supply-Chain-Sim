// Manufacturer.java
package scmx.model;

import java.util.ArrayList;
import java.util.List;

public class Manufacturer implements SupplyChainNode {
    private String name;
    private Inventory finishedGoodsInventory;
    private int rawMaterialStock;
    private List<Order> incomingOrders = new ArrayList<>();
    private List<Order> outgoingOrders = new ArrayList<>();
    private SupplyChainNode supplier;

    private final int LEAD_TIME_FROM_SUPPLIER = 2;
    private final int PRODUCTION_CAPACITY = 10;

    public Manufacturer(String name, Product product, SupplyChainNode supplier) {
        this.name = name;
        this.finishedGoodsInventory = new Inventory(product, 0);
        this.supplier = supplier;
        this.rawMaterialStock = 0;
    }

    @Override
    public void receiveOrder(Order order) {
        incomingOrders.add(order);
    }

    @Override
    public void shipOrder(Order order) {
        if (finishedGoodsInventory.getQuantity() >= order.getQuantity()) {
            finishedGoodsInventory.removeStock(order.getQuantity());
            Order shipment = new Order(
                order.getProduct(),
                order.getQuantity(),
                this,
                order.getReceiver(),
                1
            );
            outgoingOrders.add(shipment);
            System.out.println(name + " shipped " + order.getQuantity() + " units to " + order.getReceiver().getName());
        } else {
            System.out.println(name + " cannot fulfill order due to insufficient finished goods.");
        }
    }

    private void produce() {
        int unitsToProduce = Math.min(rawMaterialStock, PRODUCTION_CAPACITY);
        if (unitsToProduce > 0) {
            rawMaterialStock -= unitsToProduce;
            finishedGoodsInventory.addStock(unitsToProduce);
            System.out.println(name + " produced " + unitsToProduce + " units of finished goods.");
        } else {
            System.out.println(name + " could not produce due to insufficient raw materials.");
        }
    }

    private void orderRawMaterials() {
        Order rawMaterialOrder = new Order(
            finishedGoodsInventory.getProduct(),
            20,
            this,
            supplier,
            LEAD_TIME_FROM_SUPPLIER
        );
        supplier.receiveOrder(rawMaterialOrder);
        System.out.println(name + " ordered 20 units of raw material from " + supplier.getName());
    }

    @Override
    public void processDay() {
        List<Order> delivered = new ArrayList<>();
        for (Order o : outgoingOrders) {
            o.decrementDaysToDelivery();
            if (o.getDaysToDelivery() == 0) {
                o.getReceiver().receiveOrder(o);
                delivered.add(o);
                System.out.println(name + " delivered order to " + o.getReceiver().getName());
            }
        }
        outgoingOrders.removeAll(delivered);

        produce();

        List<Order> fulfilled = new ArrayList<>();
        for (Order o : incomingOrders) {
            shipOrder(o);
            fulfilled.add(o);
        }
        incomingOrders.removeAll(fulfilled);

        if (rawMaterialStock < 10) {
            orderRawMaterials();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public Product getProduct() {
        return finishedGoodsInventory.getProduct();
    }

    public void receiveOrderRawMaterials(int quantity) {
        rawMaterialStock += quantity;
        System.out.println(name + " received " + quantity + " units of raw materials.");
    }
}

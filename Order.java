// Order.java
package scmx.model;

public class Order {
    private Product product;
    private int quantity;
    private SupplyChainNode sender;
    private SupplyChainNode receiver;
    private int daysToDelivery;

    public Order(Product product, int quantity, SupplyChainNode sender, SupplyChainNode receiver, int daysToDelivery) {
        this.product = product;
        this.quantity = quantity;
        this.sender = sender;
        this.receiver = receiver;
        this.daysToDelivery = daysToDelivery;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public SupplyChainNode getSender() {
        return sender;
    }

    public SupplyChainNode getReceiver() {
        return receiver;
    }

    public int getDaysToDelivery() {
        return daysToDelivery;
    }

    public void decrementDaysToDelivery() {
        if (daysToDelivery > 0) daysToDelivery--;
    }
}

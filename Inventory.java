// Inventory.java
package scmx.model;

public class Inventory {
    private Product product;
    private int quantity;

    public Inventory(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addStock(int amount) {
        quantity += amount;
    }

    public boolean removeStock(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
            return true;
        }
        return false;
    }

    public Product getProduct() {
        return product;
    }
}

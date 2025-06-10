// SupplyChainNode.java
package scmx.model;

public interface SupplyChainNode {
    void receiveOrder(Order order);
    void shipOrder(Order order);
    void processDay();
    String getName();
}

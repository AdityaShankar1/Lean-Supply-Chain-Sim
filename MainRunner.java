// MainRunner.java
package scmx.main;

import scmx.model.*;

public class MainRunner {
    public static void main(String[] args) {
        System.out.println("SCMX simulation begins...\n");

        Product widget = new Product("Widget");
        Supplier supplier = new Supplier("Supplier A", widget, 50);

        Manufacturer manufacturer = new Manufacturer("Manufacturer B", widget, supplier);
        Retailer retailer = new Retailer("Retailer C", manufacturer);

        for (int day = 1; day <= 5; day++) {
            System.out.println("--- Day " + day + " ---");

            System.out.println("--- Processing day for Manufacturer: " + manufacturer.getName() + " ---");
            manufacturer.processDay();

            System.out.println("--- Processing day for Retailer: " + retailer.getName() + " ---");
            retailer.processDay();

            System.out.println("--- Processing day for Supplier: " + supplier.getName() + " ---");
            supplier.processDay();

            System.out.println();
        }
    }
}

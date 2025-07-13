This project simulates a lightweight *Supply Chain Management System* focused on lean inventory and delivery pipeline modeling.

## 📦 Modules

### 1. Inventory Simulation (SCMX)
- Models Supplier, Manufacturer, and Retailer nodes
- Supports Lean, Agile, and JIT strategies
- Randomized demand and lead times
- CLI-based simulation output

### 2. Delivery ETL Microservice (New!)
- Uses real-world-style Amazon Logistics data (LMD)
- Performs ETL (Extract, Transform, Load) pipeline from CSV
- Outputs cleaned delivery logs and summarized vehicle performance
- Helps evaluate delivery efficiency (e.g., scooter vs motorcycle)
- Fully modular — can be plugged in or removed without breaking SCM logic

## 📈 Sample Outputs

- /data/etl_output.csv: Cleaned delivery records
- /data/summary_by_vehicle.csv: Grouped delivery statistics (avg time, count)

## ✅ Getting Started

### Prerequisites
- Java 17 or above (Java 21+ tested)
- Eclipse or IntelliJ (recommended)

### Run Inventory Simulation
```bash
Run MainRunner.java

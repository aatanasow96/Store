package Entities;

import java.time.LocalDate;

public class Appliance extends Product {
    String model;
    LocalDate productionDate;
    Double weight;

    public Appliance(String name, String brand, double price, String model, LocalDate productionDate, Double weight) {
        super(name, brand, price);
        this.model = model;
        this.productionDate = productionDate;
        this.weight = weight;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("%s %s", super.toString(), this.model);
    }
}

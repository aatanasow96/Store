package Entities;

import java.time.LocalDate;

public abstract class PerishableProduct extends Product {
    private LocalDate expirationDate;

    protected PerishableProduct(String name, String brand, double price, LocalDate expirationDate) {
        super(name, brand, price);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}

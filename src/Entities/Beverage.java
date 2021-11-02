package Entities;

import java.time.LocalDate;

public class Beverage extends PerishableProduct {
    public Beverage(String name, String brand, double price, LocalDate expirationDate) {
        super(name, brand, price, expirationDate);
    }
}

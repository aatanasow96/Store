package Entities;

import java.time.LocalDate;

public class Food extends PerishableProduct {
    public Food(String name, String brand, double price, LocalDate expirationDate) {
        super(name, brand, price, expirationDate);
    }
}

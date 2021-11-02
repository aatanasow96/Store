import Entities.*;
import Entities.Enums.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Cart cart = fillCart();
        LocalDateTime timeOfPurchase = LocalDateTime.of(2021, 6, 14, 12, 34, 56);
        CashierImpl cashier = new CashierImpl();
        cashier.printReceipt(cart, timeOfPurchase);
    }

    private static Cart fillCart() {
        Food apples =
                new Food("apples", "BrandA", 1.50, LocalDate.of(2021, 6, 14));

        Beverage milk =
                new Beverage("milk", "BrandM", 0.99, LocalDate.of(2022, 2, 2));

        Clothes shirt =
                new Clothes("T-Shirt", "BrandT", 15.99, Size.M, "violet");

        Appliance laptop =
                new Appliance("laptop", "BrandL", 2345, "ModelL",
                        LocalDate.of(2021, 3, 3), 1.125);

        Cart cart = new Cart();

        Map<Product, Double> productQuantityMap = cart.getProductQuantityMap();
        productQuantityMap.put(apples, 2.45);
        productQuantityMap.put(milk, (double) 3);
        productQuantityMap.put(shirt, (double) 2);
        productQuantityMap.put(laptop, (double) 1);

        return cart;
    }
}

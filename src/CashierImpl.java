import Entities.Product;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CashierImpl implements Cashier {

    @Override
    public void printReceipt(Cart cart, LocalDateTime purchaseDate) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Date: %s", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(purchaseDate)))
                .append(System.lineSeparator())
                .append("---Products---").append(System.lineSeparator());

        DecimalFormat df = new DecimalFormat("#####.##");
        for (Map.Entry<Product, Double> productQuantityMap : cart.getProductQuantityMap().entrySet()) {
            sb.append(System.lineSeparator());
            double price = productQuantityMap.getKey().getPrice();
            double quantity = productQuantityMap.getValue();

            sb.append(productQuantityMap.getKey())
                    .append(System.lineSeparator())
                    .append(String.format("%s x $%.2f = $%.2f",
                            df.format(quantity), price, quantity * price))
                    .append(System.lineSeparator());

        }
        System.out.println(sb);
    }
}


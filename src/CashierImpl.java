import Entities.PerishableProduct;
import Entities.Product;

import java.text.DecimalFormat;
import java.time.LocalDate;
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
            Product product = productQuantityMap.getKey();
            double quantity = productQuantityMap.getValue();

            sb.append(product)
                    .append(System.lineSeparator())
                    .append(String.format("%s x $%.2f = $%.2f",
                            df.format(quantity), product.getPrice(), quantity * product.getPrice()))
                    .append(System.lineSeparator());

            addDiscountToReceipt(product, quantity, purchaseDate, sb);
        }
        System.out.println(sb);
    }

    private void addDiscountToReceipt(Object product, double quantity, LocalDateTime purchaseDate, StringBuilder sb) {

        if (product instanceof PerishableProduct) {
            LocalDate expirationDate = ((PerishableProduct) product).getExpirationDate();

            if (expirationDate.isEqual(purchaseDate.toLocalDate())) {
                double discountedPrice = ((PerishableProduct) product).getPrice() / 2;

                sb.append("#discount 50% -$")
                        .append(String.format("%.2f", discountedPrice * quantity))
                        .append(System.lineSeparator());

            } else if (purchaseDate.toLocalDate().plusDays(6).isAfter(expirationDate)) {
                double discountedPrice = ((PerishableProduct) product).getPrice() * 0.1;

                sb.append("#discount 10% -$")
                        .append(String.format("%.2f", discountedPrice * quantity))
                        .append(System.lineSeparator());
            }
        }
    }
}


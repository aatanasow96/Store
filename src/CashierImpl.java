import Entities.Appliance;
import Entities.Clothes;
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

            addDiscountToReceipt(product.getPrice(), quantity, getDiscountPercent(product, purchaseDate), sb);
        }
        System.out.println(sb);
    }

    private void addDiscountToReceipt(double price, double quantity, int discountPercent, StringBuilder sb) {
        double discountedPrice;

        switch (discountPercent) {
            case 5:
                sb.append("#discount 5% -$");
                discountedPrice = price * 0.05;
                break;
            case 10:
                sb.append("#discount 10% -$");
                discountedPrice = price * 0.1;
                break;
            case 50:
                sb.append("#discount 50% -$");
                discountedPrice = price / 2;
                break;
            default:
                return;
        }

        sb.append(String.format("%.2f", discountedPrice * quantity))
                .append(System.lineSeparator());
    }

    private int getDiscountPercent(Object product, LocalDateTime purchaseDate) {
        int discount = 0;

        if (product instanceof PerishableProduct) {

            LocalDate expirationDate = ((PerishableProduct) product).getExpirationDate();

            if (expirationDate.isEqual(purchaseDate.toLocalDate())) {
                discount = 50;
            } else if (purchaseDate.toLocalDate().plusDays(6).isAfter(expirationDate)) {
                discount = 10;
            }

        } else {

            if (!isWeekend(purchaseDate) && product instanceof Clothes) {
                discount = 10;

            } else if (isWeekend(purchaseDate) && product instanceof Appliance) {
                discount = 5;
            }
        }

        return discount;
    }

    private boolean isWeekend(LocalDateTime purchaseDate) {
        return purchaseDate.getDayOfWeek().getValue() == 6 || purchaseDate.getDayOfWeek().getValue() == 7;
    }
}


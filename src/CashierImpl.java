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

    private double subtotal;
    private double totalDiscount;

    public CashierImpl() {
        this.subtotal = 0;
        this.totalDiscount = 0;
    }

    @Override
    public void printReceipt(Cart cart, LocalDateTime purchaseDate) {
        StringBuilder sb = new StringBuilder();
        addReceiptHeader(purchaseDate, sb);

        DecimalFormat df = new DecimalFormat("#####.##");

        for (Map.Entry<Product, Double> productQuantityMap : cart.getProductQuantityMap().entrySet()) {
            sb.append(System.lineSeparator());

            Product product = productQuantityMap.getKey();
            double quantity = productQuantityMap.getValue();
            double totalPrice = product.getPrice() * quantity;

            addProductToReceipt(sb, df, product, quantity, totalPrice);

            addDiscountToReceipt(product.getPrice(), quantity, getDiscountPercent(product, purchaseDate), sb);

            this.subtotal += totalPrice;
        }

        addReceiptFooter(sb);
        System.out.println(sb);
    }

    private void addProductToReceipt(StringBuilder sb, DecimalFormat df,
                                     Product product, double quantity, double totalPrice) {

        sb.append(product)
                .append(System.lineSeparator())
                .append(String.format("%s x $%.2f = $%s",
                        df.format(quantity), product.getPrice(), df.format(totalPrice)))
                .append(System.lineSeparator());
    }

    private void addDiscountToReceipt(double price, double quantity, int discountPercent, StringBuilder sb) {

        double discount;

        switch (discountPercent) {
            case 5 -> {
                sb.append("#discount 5% -$");
                discount = price * 0.05;
            }
            case 10 -> {
                sb.append("#discount 10% -$");
                discount = price * 0.1;
            }
            case 50 -> {
                sb.append("#discount 50% -$");
                discount = price / 2;
            }
            default -> {
                return;
            }
        }

        double discountPrice = discount * quantity;

        sb.append(String.format("%.2f", discountPrice))
                .append(System.lineSeparator());

        this.totalDiscount += discountPrice;
    }

    private int getDiscountPercent(Object product, LocalDateTime purchaseDate) {
        int discountPercent = 0;

        if (product instanceof PerishableProduct) {

            LocalDate expirationDate = ((PerishableProduct) product).getExpirationDate();

            if (expirationDate.isEqual(purchaseDate.toLocalDate())) {
                discountPercent = 50;
            } else if (purchaseDate.toLocalDate().plusDays(6).isAfter(expirationDate)) {
                discountPercent = 10;
            }

        } else {

            if (!isWeekend(purchaseDate) && product instanceof Clothes) {
                discountPercent = 10;
            } else if (isWeekend(purchaseDate) && product instanceof Appliance) {
                discountPercent = 5;
            }
        }

        return discountPercent;
    }

    private boolean isWeekend(LocalDateTime purchaseDate) {
        return purchaseDate.getDayOfWeek().getValue() == 6 || purchaseDate.getDayOfWeek().getValue() == 7;
    }

    private void addReceiptHeader(LocalDateTime purchaseDate, StringBuilder sb) {
        sb.append(String.format("Date: %s", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(purchaseDate)))
                .append(System.lineSeparator())
                .append("---Products---")
                .append(System.lineSeparator());
    }

    private void addReceiptFooter(StringBuilder sb) {
        sb.append("-----------------------------------------------------------------------------------")
                .append(System.lineSeparator())
                .append(String.format("SUBTOTAL: $%.2f", subtotal))
                .append(System.lineSeparator())
                .append(String.format("DISCOUNT: -$%.2f", totalDiscount))
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(String.format("TOTAL: $%.2f", subtotal - totalDiscount));
    }
}


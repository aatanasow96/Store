import java.time.LocalDateTime;

public interface Cashier {
    void printReceipt(Cart cart, LocalDateTime purchaseDate);
}

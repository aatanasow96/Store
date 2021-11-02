import Entities.Product;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Double> productQuantityMap;

    public Cart() {
        this.productQuantityMap = new LinkedHashMap<>();
    }

    public Map<Product, Double> getProductQuantityMap() {
        return productQuantityMap;
    }

    public void setProductQuantityMap(Map<Product, Double> productQuantityMap) {
        this.productQuantityMap = productQuantityMap;
    }
}

package product;

import java.time.LocalDate;

public class NonShippableProduct extends Product{
    public NonShippableProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    public NonShippableProduct(String name, double price, int quantity, LocalDate expiry) {
        super(name, price, quantity,expiry);
    }
}
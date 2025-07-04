package product;

import java.time.LocalDate;

interface shippable{
    public String getName();
    public double getWeight();
}

public class ShippableProduct extends Product implements shippable{
    private double weight;

    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    public ShippableProduct(String name, double price, int quantity, double weight, LocalDate expiry) {
        super(name, price, quantity,expiry);
        this.weight = weight;
    }

    @Override
    public double getWeight(){
        return this.weight;
    }
}
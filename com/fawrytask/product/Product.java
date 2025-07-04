package product;

import java.time.LocalDate;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private LocalDate expiry;


    public Product(String name, double price, int quantity, LocalDate expiry) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiry = expiry;
    }

    public Product(String name, double price, int quantity) {
        this(name,price,quantity,null);
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public LocalDate getExpiry(){
        return this.expiry;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void reduceQuantity(int q){
        this.quantity-=q;
    }

    public void setExpiry(LocalDate expiry){
        this.expiry = expiry;
    }

    public boolean isExpired(){
        return expiry != null && expiry.isBefore(LocalDate.now());
    }
}
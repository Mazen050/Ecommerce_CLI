import carte.Cart;
import customer.Customer;
import java.time.LocalDate;
import product.*;
import shipping.*;


public class Main {
    public static void checkout(Customer customer ,Cart cart)throws Exception{
        if(cart.isEmpty()){
            throw new Exception("The Cart Is Empty");
        }
        else{
            double subTotal = cart.getTotalPrice();
            double shippingFee = new Shipping(cart.getShippableItems()).getShippingFees();
            double paidAmount = subTotal+shippingFee;

            if(customer.getBalance()<paidAmount){
                throw new Exception("Customer's balance is insufficient.");
            }

            customer.setBalance(customer.getBalance() - paidAmount);
            double currentBalance = customer.getBalance();

            System.out.println();
            System.out.println("** Checkout receipt **");
            
            cart.listItems();

            System.out.println("-----------------------");
            System.out.println("Subtotal\t"+subTotal);
            System.out.println("Shipping\t"+shippingFee);
            System.out.println("Amount\t"+paidAmount);

            System.out.println();

            System.out.println("Customer Current Balance:"+currentBalance);

            cart.checkout();
        }
        

    }
    public static void main(String[] args) {
        Customer richCustomer = new Customer("Mazen","0101",10000);
        Customer poorCustomer = new Customer("Poor", "0002", 10.0);

        LocalDate expiredDate =  LocalDate.MIN;
        LocalDate UnexpiredDate =  LocalDate.MAX;


        ShippableProduct cheese = new ShippableProduct("Cheese",100,3,400,UnexpiredDate);
        ShippableProduct biscet = new ShippableProduct("Cheese",100,3,300,UnexpiredDate);
        ShippableProduct tv = new ShippableProduct("TV",2000,3,1000,UnexpiredDate);
        NonShippableProduct scratch = new NonShippableProduct("scratchCard",100,3,UnexpiredDate);

        Cart c = new Cart();

        try {
            c.add(cheese, 2); 
            c.add(tv, 3); 
            c.add(scratch, 1); 
            checkout(richCustomer, c); 
            // c.add(p,2);
            
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }


        // other test cases

        ShippableProduct cheeseFresh = new ShippableProduct("Cheese", 100.0, 5, 200.0, UnexpiredDate);
        ShippableProduct cheeseOld   = new ShippableProduct("Cheese (old)",  50.0, 5, 200.0, expiredDate);
        NonShippableProduct scratchCard = new NonShippableProduct("ScratchCard", 20.0, 10, UnexpiredDate);

        // 1. Normal checkout
        System.out.println("\n=== 1. Normal checkout ===");
        try {
            Cart cart1 = new Cart();
            cart1.add(cheeseFresh, 2);
            cart1.add(scratchCard, 1);
            checkout(richCustomer, cart1);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 2. Empty cart
        System.out.println("\n=== 2. Empty cart ===");
        try {
            Cart cart2 = new Cart();
            checkout(richCustomer, cart2);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 3. Adding more than available stock
        System.out.println("\n=== 3. Add more than stock ===");
        try {
            Cart cart3 = new Cart();
            cart3.add(cheeseFresh, 999);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 4. Adding an expired product
        System.out.println("\n=== 4. Add expired product ===");
        try {
            Cart cart4 = new Cart();
            cart4.add(cheeseOld, 1);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 5. Insufficient funds at checkout
        System.out.println("\n=== 5. Insufficient balance ===");
        try {
            Cart cart5 = new Cart();
            cart5.add(cheeseFresh, 1);
            checkout(poorCustomer, cart5);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


        // 6. Negative quantity
        System.out.println("\n=== 7. Negative quantity ===");
        try {
            Cart cart7 = new Cart();
            cart7.add(cheeseFresh, -3);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 7. Stock reduction after successful checkout
        System.out.println("\n=== 8. Stock decremented after checkout ===");
        try {
            cheeseFresh.setQuantity(5);
            System.out.println("Current Stock: "+cheeseFresh.getQuantity());
            Cart cart8 = new Cart();
            cart8.add(cheeseFresh, 2);
            checkout(richCustomer, cart8);
            System.out.println("Remaining stock: " + cheeseFresh.getQuantity());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
}
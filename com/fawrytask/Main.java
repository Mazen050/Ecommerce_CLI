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
        Customer customer = new Customer("Mazen","0101",10000);

        LocalDate expiredDate =  LocalDate.MIN;
        LocalDate UnexpiredDate =  LocalDate.MAX;
        ShippableProduct cheese = new ShippableProduct("Cheese",100,3,400,UnexpiredDate);
        ShippableProduct biscet = new ShippableProduct("Cheese",100,3,300,UnexpiredDate);
        ShippableProduct tv = new ShippableProduct("TV",2000,3,1000,UnexpiredDate);
        NonShippableProduct scratchCard = new NonShippableProduct("scratchCard",100,3,UnexpiredDate);

        Cart c = new Cart();

        try {
            c.add(cheese, 2); 
            c.add(tv, 3); 
            c.add(scratchCard, 1); 
            checkout(customer, c); 
            // c.add(p,2);
            
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}
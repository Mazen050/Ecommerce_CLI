package customer;

public class Customer{
    private double balance;
    
    public Customer(String name, String num,double balance){
        this.balance = balance;
    }
    public double getBalance(){
        return this.balance;
    }
    public void setBalance(double b){
        this.balance = b;
    }
}
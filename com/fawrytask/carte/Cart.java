package carte;

import java.util.HashMap;
import product.*;

public class Cart {
    HashMap<Product, Integer> item = new HashMap<>();
    
    public void add(Product p,int quantity)throws Exception{
        int availableQuantity = p.getQuantity();
        if(p.isExpired()){
            throw new Exception("Item Expired: "+p.getName());
        }
        else if(availableQuantity==0){
                throw new Exception("Item Out of Stock");
        }
        else if(availableQuantity>=quantity){
            if(quantity<0){
                throw new Exception("Enter real quantity");
            }
            
            item.put(p, quantity);
        }
        else{
            throw new Exception("Quantity not available for item:"+p.getName()+" \nMaximum Available Quantity: "+availableQuantity);
        }
    }

    public void remove(Product p){
        item.remove(p);
    }

    public HashMap<ShippableProduct,Integer> getShippableItems(){
        HashMap<ShippableProduct,Integer> shippable = new HashMap<>();
        for(Product p : item.keySet()){
            if(p instanceof ShippableProduct){
                shippable.put((ShippableProduct) p,item.get(p));
            }
        }
        return shippable;
    }

    public double getTotalPrice(){
        double sum = 0;
        for(Product p: item.keySet()){
            sum+=(p.getPrice()*item.get(p));
        }
        return sum;
    }

    public boolean isEmpty(){
        return item.isEmpty();
    }

    public void listItems(){
        item.forEach((p,value)->{
            System.out.println(value+"x "+p.getName()+"\t"+p.getPrice());
        });
    }
    public void checkout(){
        item.forEach((p,value)->{
            p.reduceQuantity(value);
        });
    }
}
package shipping;
import java.util.HashMap;
import product.ShippableProduct;

public class Shipping {
    private double totalWeight;
    private HashMap<ShippableProduct,Integer> item;
    public Shipping(HashMap<ShippableProduct,Integer> list){
        this.item = list;
        System.out.println("** Shipment notice **");
        for(ShippableProduct p:list.keySet()){
            System.out.println(list.get(p)+"x "+p.getName()+"\t"+p.getWeight());
            totalWeight+=p.getWeight();
        }
        String unite= "lb";


        if(totalWeight>1000){
            totalWeight = totalWeight/1000;
            unite = "kg";
        }
        System.out.println("Total package weight "+totalWeight+""+unite);
    }
    public double getShippingFees(){
        double fee=0;
        for(ShippableProduct p:item.keySet()){
            fee+=(p.getWeight()/1000 * 10)*item.get(p);
        }
        return fee;
    }
}
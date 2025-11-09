package models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Orderables> order;
    public Order(){
        this.order = new ArrayList<>();
    }
    // a public access grant to add into the encapsulated array list
    public void addOrder(Orderables item){
        order.add(item);
    }
    // a method to remove an order from the encapsulated arrayList
    public void removeItem(Orderables item){
        order.remove(item);
    }
    /* this method goes through every ordered items in the order arraylist and gives
    the total
     */
    public double calculateTotalPrice(){
        double total = 0;
        for (Orderables order : order){
            total += order.getPrice();
        }
        return total;
    }
    /* takes all the summaries from the ordered items and shows it to the user to have
    an idea of the orders they have
    */
    public void orderSummary(){
        System.out.println("----- Order Details -----");
        for (Orderables order : order){
            System.out.println("- " + order.getSummary() + " : $" + String.format("%.2f", order.getPrice()));
        }
        System.out.println("___________________");
        System.out.println("Total: $" + String.format("%.2f", calculateTotalPrice()));
    }

}

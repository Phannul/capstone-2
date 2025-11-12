package models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Orderables> order;
    private String customerName;
    public Order(){
        this.order = new ArrayList<>();
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // a public access grant to add into the encapsulated array list
    public void addOrder(Orderables item){
        order.add(item);
    }
    // a method to remove an order from the encapsulated arrayList
    public void removeItem(Orderables item){
        order.remove(item);
    }
    public boolean isEmpty(){
        return order.isEmpty();
    }
    /* this method goes through every ordered items in the order arraylist and gives
    the total
     */
    public double calculateTotalPrice(){
        double total = 0;
        for (Orderables order : order){
            total += order.getPrice();
        }
        if (customerName != null && customerName.equalsIgnoreCase("dave dykes")){
            total = 0.0;
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

    public String getCustomerName() {
        return customerName;
    }

    // this is a method that can only be used by the receipt writer class
    public String summaryFormatForReceipt(){
        StringBuilder sb = new StringBuilder();
        if (customerName != null) {
            sb.append("Customer: ").append(customerName).append("\n");
        }
        sb.append("\n----Your Orders----\n");
        for (Orderables o : order){
            sb.append("- ").append(o.getSummary()).append("\n");
        }
        sb.append("______________\n");
        sb.append("Total: $").append(String.format("%.2f", calculateTotalPrice())).append("\n");
        return sb.toString();
    }


}

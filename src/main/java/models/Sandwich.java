package models;

import java.util.ArrayList;

public class Sandwich implements Orderables{
    private String breadType;
    private String breadLength;
    private boolean isToasted;
    private double basePrice;
    private ArrayList<Toppings> toppings;

    public Sandwich(String type, String breadLength, boolean isToasted) {
        this.breadType = type;
        this.breadLength = breadLength;
        this.basePrice = basePrice;
        this.isToasted = isToasted;
        this.toppings = new ArrayList<>();
    }
    // sets base price for the sandwich in the case of no topping
    public double setBasePrice (String breadLength){
        double price = 0;
        if(breadLength.equalsIgnoreCase("4\"" )){
            price =5.50;
        }
        if (breadLength.equalsIgnoreCase("8\"")) {
            price = 7.00;
        }
        if (breadLength.equalsIgnoreCase("Footlong")){
            price = 6.00;
        }
        return price;
    }
    // this adds the selected toppings to the toppings list as the ArrayList is encapsulated
    public void addTopping(Toppings t){
        toppings.add(t);
    }
    // gets the total price of the sandwich and the toppings
    @Override
    public double getPrice(){
        double total = basePrice;
        for (Toppings t : toppings){
            total += t.calculatePrice(breadLength);
        }
        return total;
    }
    //gets the overview of ordered sandwich with corresponding toppings
    @Override
    public String getSummary(){
        String summary = breadLength + " " + breadType + "Sandwich";
        if(isToasted) {
            summary += " (toasted)";
        }
        for (Toppings t : toppings){
            summary += "\n -" + t.toppingSummary() + "\n";
        }
        summary += String.format("Total: %.2f", getPrice());
        return summary;
    }

}

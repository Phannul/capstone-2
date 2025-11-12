package models;
/*
This class implements Orderables because a customer can separately order just a drink
without ordering a sandwich.
 */
public class Drinks implements Orderables{
    private String flavor;
    private String size;

    public Drinks(String flavor, String size){
        this.flavor = flavor.trim();
        this.size = size.trim();
    }
    public String getFlavor() {
        return flavor;
    }
    public String getSize() {
        return size;
    }
    //Overridden method to get the price of the drinks while implementing Orderables
    @Override
    public double getPrice(){
        double price = 0.0;
        if(size.equalsIgnoreCase("small")){
            price = 2.00;
        } else if (size.equalsIgnoreCase("medium")) {
            price = 2.5;
        } else if (size.equalsIgnoreCase("large")) {
            price = 3.00;
        }
        return price;
    }
    //Overridden method to get the summary of the ordered drink while implementing Orderables
    @Override
    public String getSummary(){
        String summary = "Drink- " + flavor + " " + size;
        return summary;
    }



}


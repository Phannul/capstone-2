package models;


public class Toppings {
    private String name;
    private String type;
    private boolean isExtra;
    //Constructor
    public Toppings(String name, String type, boolean isExtra) {
        this.name = name;
        this.type = type.toLowerCase();
        this.isExtra = isExtra;

    }
    //getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isExtra() {
        return isExtra;
    }

    /* a method to calculate the individual prices of toppings accordingly to their
        categories
         */
    public double calculatePrice(String breadLength){
        double price = 0;
        // meat prices
        if (type.equalsIgnoreCase("meat")){
            if(breadLength.equals("4in")){
                price = 1.00;
            } else if (breadLength.equals("8in")) {
                price = 2.00;
            } else if (breadLength.equalsIgnoreCase("Footlong")) {
                price = 3.00;
            }
            //when extra meat is needed
            if (isExtra){
                if (breadLength.equals("4in")){
                    price += 0.5;
                } else if (breadLength.equals("8in")) {
                    price += 1.00;
                } else if (breadLength.equalsIgnoreCase("Footlong")) {
                    price += 1.50;
                }
            }
        }
        //cheese prices
        else if (type.equalsIgnoreCase("cheese")){
            if (breadLength.equals("4in")){
                price = 0.75;
            } else if (breadLength.equals("8in")) {
                price = 1.50;
            } else if(breadLength.equalsIgnoreCase("Footlong")){
                price = 2.25;
            }
            if (isExtra){
                if (breadLength.equals("4in")){
                    price += 0.30;
                } else if (breadLength.equals("8in")) {
                    price += 0.60;
                } else if (breadLength.equalsIgnoreCase("Footlong")) {
                    price += 0.90;
                }
            }
        }
        //free toppings and sauces
        else if (type.equalsIgnoreCase("veggie") || type.equalsIgnoreCase("sauce") ) {
            price = 0.0;
        }
        return price;
    }
    // labels the name of the topping and if it was extra or not
    public String toppingSummary(){
        String summary = name ;
        if (isExtra){
            summary += " [Extra] ";
        }
        return summary;
    }
}

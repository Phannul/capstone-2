package models;
// the price attribute of the chips class is static because any type of chips have the same price
public class Chips implements Orderables{
    private String chipType;
    private static double price = 1.50;
    public Chips(String chipType) {
        this.chipType = chipType;
        this.price = 1.50;
    }
    @Override
    public double getPrice(){
        return this.price;
    }
    @Override
    public String getSummary(){
        return chipType + " " + price;
    }


}

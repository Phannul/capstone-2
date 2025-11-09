package models;

public class Chips implements Orderables{
    private String chipType;

    public Chips(String chipType) {
        this.chipType = chipType;
    }
    @Override
    public double getPrice(){
        return 1.50;
    }
    @Override
    public double

}

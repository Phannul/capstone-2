package models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Orderables> order;
    public Order(){
        this.order = new ArrayList<>();
    }

}

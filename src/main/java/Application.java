import models.Sandwich;
import models.Toppings;

public class Application {
    public static void main(String[] args) {
        System.out.println();
        Sandwich sandwich = new Sandwich("WheatBread ", "4\"", true);
        sandwich.setBasePrice("4\"");
        Toppings t = new Toppings("salami", "meat", false);
        Toppings t1 = new Toppings("salami", "meat", false);
        Toppings t2 = new Toppings("salami", "meat", false);

        sandwich.addTopping(t);
        sandwich.addTopping(t1);
        sandwich.addTopping(t2);
        System.out.println(sandwich.getPrice());
        System.out.println(sandwich.getSummary());
    }
}

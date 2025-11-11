import models.Drinks;
import models.Order;
import models.Sandwich;
import models.Toppings;
import ui.UI;

public class Application {
    public static void main(String[] args) {
        Sandwich sandwich = new Sandwich("wheat", "4 in", true);
        Toppings t = new Toppings("salami", "meat", false);
        sandwich.addTopping(t);
        System.out.println(sandwich.getSummary());

        Order order = new Order();

        Sandwich sandwich2 = new Sandwich("wheat", "8 in", true);
        sandwich2.addTopping(new Toppings("cheese", "veggie", false));
        sandwich2.addTopping(new Toppings("ham", "meat", true));

        Drinks drink = new Drinks("Cola", "large");

        order.addOrder(sandwich2);
        order.addOrder(drink);
        order.calculateTotalPrice();
        order.orderSummary();

        UI ui = new UI();
        ui.start();
    }
}

package ui;

import models.Order;
import models.Sandwich;

import java.util.Scanner;

public class UI {
    private Order order;

    public UI() {
        this.order = new Order();
    }
    Scanner scanner = new Scanner(System.in);
    public void start(){
        System.out.println("==== Welcome To DELIcious ==== \n" +
                "How can We Help Today");
        boolean running = true;

        do{
            System.out.println("1) Add Sandwich" +
                    "\n2) Add Drink" +
                    "\n3) Add Chips" +
                    "\n4) Checkout");
            String input = scanner.nextLine();

            switch(input){
                case "1" -> createSandwich();
                case "2" -> System.out.println("addDrink");
                case "3" -> System.out.println("addChips");
                case "4" -> System.out.println("checkOut");
            }
        }while(running);
    }
    public void createSandwich(){

        System.out.println("Enter Bread Type (White/Wheat/Rye/Wrap)");
        String breadType = scanner.nextLine().strip();
        System.out.println("Enter Bread length (4in/8in/Footlong)");
        String breadLength = scanner.nextLine().strip();
        boolean toasted = false;
        while (true){
            System.out.println("Would you like it toasted(Yes/No)");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("yes")){
                toasted = true;
                break;
            } else if (input.equalsIgnoreCase("no")) {
                toasted = false;
                break;
            }else {
                System.out.println("Invalid Entry, Please type yes/no");
            }
        }
        Sandwich sandwich = new Sandwich(breadType, breadLength, toasted);
        boolean addingToppings = true;
        while(addingToppings) {
            System.out.println("Would you like any toppings? ");
            String iNeedTopping = scanner.nextLine();
            if (iNeedTopping.equalsIgnoreCase("yes")) {
                System.out.println("Awesome!!\n" +
                        "Here are the Topping we have: \n" +
                        "🥩Meats: Steak| Ham| Salami| Roast Beef| Chicken| Bacon\n" +
                        "🧀Cheeses: American| Provolone| Cheddar| Swiss\n" +
                        "Regulars: Lettuce| Peppers| Onions| Tomatoes| Jalapeños| Cucumbers| Pickles| Guacamole| Mushrooms\n" +
                        "Enter topping name (or type 'x' to finish):  ");
                while(true){
                    String toppingName = scanner.nextLine();
                    if(toppingName.equalsIgnoreCase("x")){
                        break;
                    }
                }
            }


        }

    }
    private String autoDetectCategory(String toppingName) {
        String lower = toppingName.toLowerCase();
        if (lower.contains("Steak") || lower.contains("Ham") || lower.contains("Salami") ||
                lower.contains("Roast Beef") || lower.contains("Chicken") || lower.contains("Bacon")){
            return "meat";
        }else if (lower.contains("cheddar") || lower.contains("swiss")
                || lower.contains("american") || lower.contains("provolone")) {
            return "cheese";
        } else if (lower.contains("lettuce") || lower.contains("tomato")
                || lower.contains("onion") || lower.contains("pickles")
                || lower.contains("peppers") || lower.contains("mushroom")
                || lower.contains("guacamole") || lower.contains("cucumber")
                || lower.contains("jalapeño")) {
            return "veggie";
        } else if (lower.contains("mayo") || lower.contains("mustard")
                || lower.contains("chipotle") || lower.contains("ranch") ||
                lower.contains("ketchup") || lower.contains("thousand island") || lower.contains("vinaigrette")) {
            return "sauce";
        } else {
            return null;
        }
    }

}


package ui;

import models.*;

import java.util.Scanner;

public class UI {
    private Order order;

    public UI() {
        this.order = new Order();
    }

    Scanner scanner = new Scanner(System.in);

    public void start(){
        boolean running = true;
        System.out.println("==== Welcome To DELIcious ==== \n" +
                "How can We Help Today");
        while (running){
            System.out.println("""
                    🏠 Home
                    1) New Order
                    0) Exit
                    """);
            String choice = scanner.nextLine().trim();

            switch(choice){
                case "1" -> startNewOrder();
                case "0" -> running = false;
            }
        }

    }

    private void startNewOrder() {

        boolean ordering = true;

        do {
            System.out.println(
                    "1) Add Sandwich" +
                    "\n2) Add Drink" +
                    "\n3) Add Chips" +
                    "\n4) Checkout" +
                    "\n0) Cancel Order ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> createSandwich();
                case "2" -> addDrink();
                case "3" -> addChips();
                case "4" -> checkOut();
                case "0" -> System.out.println("cancel");
            }
        } while (ordering);
    }

    private void createSandwich() {
        System.out.println("Enter Bread Type (White/Wheat/Rye/Wrap)");
        String breadType = scanner.nextLine().strip();
        System.out.println("Enter Bread length (4in/8in/Footlong)");
        String breadLength = scanner.nextLine().strip();
        boolean toasted = false;
        while (true) {
            System.out.println("Would you like it toasted(Yes/No)");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("yes")) {
                toasted = true;
                break;
            } else if (input.equalsIgnoreCase("no")) {
                toasted = false;
                break;
            } else {
                System.out.println("Invalid Entry, Please type yes/no");
            }
        }
        Sandwich sandwich = new Sandwich(breadType, breadLength, toasted);


        System.out.println("Would you like any toppings? ");
        String iNeedTopping = scanner.nextLine();

        if (iNeedTopping.equalsIgnoreCase("yes")) {
            toppingMenu();
            System.out.println("Enter topping name (or type 'done' to finish):  ");
            while (true) {
                String toppingName = scanner.nextLine().strip();
                if (toppingName.equalsIgnoreCase("done")) {
                    break;
                }
                String category = detectCategory(toppingName);
                if (category == null) {
                    System.out.println("Sorry, we don't have that topping at the moment");
                }
                assert category != null;
                Toppings topping = new Toppings(toppingName, category, false);
                sandwich.addTopping(topping);

            }
        }
        System.out.println("Would you like to add any extra topping? (yes/no)");
        String extraOrNot = scanner.nextLine();
        if (extraOrNot.equalsIgnoreCase("yes")){
            System.out.println("Good, which extra topping would you like to add? ");
            toppingMenu();
            while(true){
                System.out.println("Enter extra topping name(or 'x' to finish): ");
                String extraToppingName = scanner.nextLine().strip();

                if (extraToppingName.equalsIgnoreCase("x")){
                    break;
                }
                String category = detectCategory(extraToppingName);

                if (category == null) {
                    System.out.println("Sorry, we don't have that topping available as extra");

                }
                Toppings extraTopping = new Toppings(extraToppingName, category, true);
                sandwich.addTopping(extraTopping);
            }
        }
        order.addOrder(sandwich);
        System.out.println("Sandwich Added to Order");
    }

    private void toppingMenu() {
        System.out.println("Awesome!!\n" +
                "Here are the Topping we have: \n" +
                "🥩Meats: Steak| Ham| Salami| Roast Beef| Chicken| Bacon\n" +
                "🧀Cheeses: American| Provolone| Cheddar| Swiss\n" +
                "Regulars: Lettuce| Peppers| Onions| Tomatoes| Jalapeños| Cucumbers| Pickles| Guacamole| Mushrooms\n");
    }
    private void addDrink(){
        System.out.println("\n 🥤 Drink Menu: " +
                "\n Flavors: Coke | Pepsi | Sprite | Water | Iced Tea");
        System.out.println("Enter Drink Flavor: ");
        String drinkFlavor = scanner.nextLine().strip();

        System.out.println("Enter Drink Size (Small/Medium/Large): ");
        String drinkSize = scanner.nextLine().strip();

        Drinks drink = new Drinks(drinkFlavor, drinkSize);
        order.addOrder(drink);
    }
    private void addChips(){
        System.out.println("\n Chips Menu: " +
                "\n Types: Lays | Doritos | BBq | Sour cream| HotFlaminCheetos");
        System.out.println("Enter Chips Type: ");
        String chipsType = scanner.nextLine().strip();

        Chips chip = new Chips(chipsType);
        order.addOrder(chip);
    }

    private void checkOut(){
        System.out.println("\n Checkout Summary");
        order.orderSummary();
    }


    private String detectCategory(String toppingName) {
        String lower = toppingName.toLowerCase();
        if (lower.contains("steak") || lower.contains("ham") || lower.contains("salami") ||
                lower.contains("roast beef") || lower.contains("chicken") || lower.contains("bacon")) {
            return "meat";
        } else if (lower.contains("cheddar") || lower.contains("swiss")
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


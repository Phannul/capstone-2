package ui;

import models.*;

import java.util.Scanner;
import util.ReceiptWriter;
public class UI {
    private Order order;
    public UI() {
        this.order = new Order();
    }
    Scanner scanner = new Scanner(System.in);
    //A starter method as a gate way into the application
    public void start(){
        boolean running = true;
        System.out.println("==== Welcome To Drippy ==== \n" +
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
                case "0" -> System.exit(0);
            }
        }

    }
    // The ordering screen menu to let users use what they want to do with the app
    private void startNewOrder() {
        boolean ordering = true;
    // This loop will allow the menu to keep on prompting the user until they successfully checkout or cancel order
        do {
            System.out.println(
                    "1) 🥪Add Sandwich" +
                    "\n2) 🥤Add Drink" +
                    "\n3) 🍟Add Chips" +
                    "\n4) 🧾Checkout" +
                    "\n0) ❌Cancel Order ");
            String input = scanner.nextLine();
            switch (input) {
                case "1" -> createSandwich();
                case "2" -> addDrink();
                case "3" -> addChips();
                case "4" -> checkOut();
                case "0" -> {
                    System.out.println("Are you sure you want to cancel your order?\n" +
                            "1) yes \n" +
                            "2) No \n");
                    String confirm = scanner.nextLine().strip();
                    switch(confirm){
                        case "1" -> {
                            order = new Order();
                            startNewOrder();
                            System.out.println("Order Successfully Canceled");
                        }
                        case "2" -> startNewOrder();
                        default -> {
                            System.err.println("❌ Invalid Entry");
                            startNewOrder();
                        }
                    }
                }
                default -> {
                    System.err.println("❌ Invalid Entry");
                    startNewOrder();
                }
            }
        } while (ordering);
    }
/*
A method to create sandwich: It allows the user to choose from variety of topping, bread and size
options
*/
    private void createSandwich() {
        System.out.println("Choose Bread Type \n" +
                "1) White \n" +
                "2) Wheat \n" +
                "3) Rye \n" +
                "4) Wrap \n" +
                "B) back'");
        String breadType = scanner.nextLine().strip();
        switch(breadType){
            case "1" -> breadType = "white";
            case "2" -> breadType = "wheat";
            case "3" -> breadType = "rye";
            case "4" -> breadType = "Wrap";
            default -> {
                System.err.println("❌ Invalid Entry");
                createSandwich();
            }
        }
        back(breadType);
        boolean toasted = false;
        while (true) {
            System.out.println("Do you want it to be toasted(Yes/No) or type 'b' to go back");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("yes")) {
                toasted = true;
                break;
            } else if (input.equalsIgnoreCase("no")) {
                toasted = false;
                break;
            } else if (input.equalsIgnoreCase("b")) {
                back(input);
            } else {
                System.out.println("Invalid Entry, Please type yes/no");
            }
        }
        System.out.println("Enter Bread length \n" +
                "4) 4in\n" +
                "8) 8in\n" +
                "f) Footlong\n" +
                "B) Back");
        String breadLength = scanner.nextLine().strip();
        String footlong = "f";
        switch (breadLength){
            case "4" -> breadLength = "4in";
            case "8" -> breadLength = "8in";
            case "f" -> breadLength = "FootLong";
            default ->{
                System.err.println("❌ Invalid Entry");
                createSandwich();
            }
        }
        back(breadLength);
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
                    toppingMenu();
                    continue;
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
                System.out.println("Enter extra topping name(or 'done' to finish): ");
                String extraToppingName = scanner.nextLine().strip();

                if (extraToppingName.equalsIgnoreCase("done")){
                    break;
                }
                String category = detectCategory(extraToppingName);

                if (category == null) {
                    System.out.println("Sorry, we don't have that topping available as extra");
                    toppingMenu();
                    continue;
                }
                Toppings extraTopping = new Toppings(extraToppingName, category, true);
                sandwich.addTopping(extraTopping);
            }
        }
        order.addOrder(sandwich);
        System.out.println("Sandwich Added to Order");
    }
    /*a separate method to give the user the variety of toppings the shop has
    to avoid typing it again anda again when needed
     */
    private void toppingMenu() {
        System.out.println("Awesome!!\n" +
                "Here are the Topping we have: \n" +
                "🥩Meats: Steak| Ham| Salami| Roast Beef| Chicken| Bacon\n" +
                "🧀Cheeses: American| Provolone| Cheddar| Swiss\n" +
                "Regulars: Lettuce| Peppers| Onions| Tomatoes| Jalapeños| Cucumbers| Pickles| Guacamole| Mushrooms\n");
    }
    /*
   This is the method responsible to match the topping name with their corresponding
   category for pricing purposes
    */
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
        }
        else {
            return null;
        }
    }
    // an add drink method to add drink into an order
    private void addDrink(){
        System.out.println("\n 🥤 Drink Menu: " +
                "\n Flavors: " +
                "\n 1) Coke " +
                "\n 2) Pepsi " +
                "\n 3) Sprite " +
                "\n 4) Water " +
                "\n 5) Iced Tea" +
                "\n B) back");
        String drinkFlavor = scanner.nextLine().strip();
        switch (drinkFlavor){
            case "1" -> drinkFlavor = "Coke";
            case "2" -> drinkFlavor = "Pepsi";
            case "3" -> drinkFlavor = "Sprite";
            case "4" -> drinkFlavor = "Water";
            case "5" -> drinkFlavor = "Iced Tea";
            default -> {
                System.err.println("❌Invalid Entry");
                addDrink();
            }
        }
        back(drinkFlavor);
        System.out.println("Enter Drink Size " +
                "\n 1) Small" +
                "\n 2) Medium" +
                "\n 3) Large ");
        String drinkSize = scanner.nextLine().strip();
        switch (drinkSize){
            case "1" -> drinkSize = "Small";
            case "2" -> drinkSize = "Medium";
            case "3" -> drinkSize = "Large";
            default -> {
                System.out.println("❌ Invalid Entry");
                addDrink();
            }
        }
        Drinks drink = new Drinks(drinkFlavor, drinkSize);
        order.addOrder(drink);
    }
    // a method to add chips into the order
    private void addChips(){
        System.out.println("\n Chips Menu: " +
                "\n Types: " +
                "\n 1) Lays " +
                "\n 2) Doritos " +
                "\n 3) BBq " +
                "\n 4) Sour cream " +
                "\n 5) Hot Flamin Cheetos" +
                "\n B) back" +
                "\n Enter chips here: ");
        String chipsType = scanner.nextLine().strip();
        switch(chipsType){
            case "1" -> chipsType = "Lays";
            case "2" -> chipsType = "Doritos";
            case "3" -> chipsType = "BBQ";
            case "4" -> chipsType = "Sour Cream";
            case "5" -> chipsType = "Hot Flaming Cheetos";
            default -> {
                System.err.println("❌ Invalid Entry");
                addChips();
            }
        }
        back(chipsType);
        Chips chip = new Chips(chipsType);
        order.addOrder(chip);
    }
    /* a method that confirms if the user wants to proceed with their order and pay for it
    also asks for the name to be incorporated into the order plus if they have coupon
    and commands the receipt writer to save it as receipt
     */
    private void checkOut(){
        if (order.isEmpty()){
            System.err.println("⚠️You can't checkout an empty order");
            return;
        }
        System.out.println("\n Checkout Summary");
        String name = getName();
        order.setCustomerName(name);
        String code = getCoupon();
        order.setCoupon(code);
        if (order.getCoupon().isEmpty()){
        }
        order.orderSummary();
        System.out.println("Type '1' to confirm the order or type 'b' to go back");
        String confirm = scanner.nextLine().strip();
        back(confirm);
        if (confirm.equalsIgnoreCase("1")){
            ReceiptWriter receiptWriter = new ReceiptWriter();
            receiptWriter.saveReceipt(order);
            order = new Order();
            start();
        }else{
            System.out.println("Failed to Confirm your order");
        }
    }

    // a method to prompt the user to enter their name during checkout
    public String getName(){
        System.out.println("What will be the Name for the Order: ");
        String name = scanner.nextLine().strip();
        return name;
    }
    // a method to prompt the user to use their cupons if they have
    public String getCoupon(){
        System.out.println("Do you have a coupon code?\n" +
                "Enter here (or press return to skip):");
        String code = scanner.nextLine().strip();
        return code;
    }
    // a back method that takes back to the main order screen
    private void back(String input){
        if (input.equalsIgnoreCase("b")){
            startNewOrder();
        }
    }

}


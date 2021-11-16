//package ui;
//
//import model.Drink;
//
//public class OrderAppDrinkPanelStuff {
//
//    //MODIFIES: this
//    //EFFECTS: Prompts user to choose a drink and adds it to current order
//    private void doAddToOrder() {
//        Drink drink;
//
//        System.out.println("SELECT DRINK TO ADD FROM MENU");
//        String drinkName = input.nextLine().toLowerCase();
//        while (!checkName(drinkName)) {
//            System.out.println("THAT DRINK IS NOT ON THE MENU");
//            drinkName = input.nextLine().toLowerCase();
//        }
//
//        drink = new Drink(drinkName, currentOrder.isToGo());
//        currentOrder.addToOrder(drink);
//        customizeDrinkSize(drinkName);
//        includeAddOn(drinkName);
//        currentOrder.addPriceToDrink(drink);
//        System.out.println(drink.getDrinkName() + " HAS BEEN ADDED TO YOUR ORDER");
//    }
//
//    //MODIFIES: this
//    //EFFECTS: Prompts user to customize drink size for drink in current order
//    private void customizeDrinkSize(String drinkName) {
//        String drinkSize;
//        if (!(drinkName.equals("macchiato") || drinkName.equals("espresso"))) {
//            System.out.printf("SELECT DRINK SIZE ( %-10s%-10s%-6s)%n", "Small", "Medium", "Large");
//            drinkSize = input.nextLine();
//        } else {
//            drinkSize = "3 oz demitasse cup";
//        }
//        currentOrder.getDrink(drinkName).setDrinkSize(drinkSize);
//    }
//
//    //MODIFIES: this
//    //EFFECTS: Prompts user to customize add-ons for drink in current order
//    private void includeAddOn(String drinkName) {
//        System.out.println("ADD-ONS? (YES / NO)");
//        String includeAddOns = input.nextLine().toLowerCase();
//
//        if (includeAddOns.equals("yes")) {
//            do {
//                System.out.println("SELECT AN ADD-ON");
//                System.out.printf("%-15s%-15s%-15s%n", "Milk", "Cream", "Espresso shot");
//                System.out.printf("%-15s%-15s%n", "Honey", "Sugar");
//
//                String addOnChoice = input.nextLine();
//                currentOrder.getDrink(drinkName).addOn(addOnChoice);
//                System.out.println("1 " + addOnChoice.toUpperCase() + " HAS BEEN ADDED TO YOUR DRINK");
//                System.out.println("ANY MORE ADD-ONS? (YES / NO)");
//                includeAddOns = input.nextLine().toLowerCase();
//
//            } while (includeAddOns.equals("yes"));
//        }
//    }
//
//}

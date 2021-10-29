package model;

import java.util.*;

// Represents a drink
public class Drink implements Cloneable {

    //fields
    String drinkName;
    Double drinkPrice;
    String cupType;

    boolean toGo;
    String drinkSize;
    List<AddOn> addOns;
    HashMap<String, Double> menu;

    //EFFECTS: Creates a new medium drink with 0 price and cup type set
    // based on whether the drink is to-go
    public Drink(String drinkName, boolean toGo) {
        this.drinkName = drinkName;
        this.drinkSize = "medium";
        this.toGo = toGo;
        this.drinkPrice = 0.0;
        addOns = new ArrayList<>();
        menu = new HashMap<>();
        initMenu();
        setCupType(this.toGo);
    }

    //MODIFIES: this
    //EFFECTS: initializes a hashmap for the drinks menu
    public void initMenu() {
        menu.put("drip coffee", 2.2);
        menu.put("americano", 3.1);
        menu.put("espresso", 1.8);
        menu.put("macchiato", 3.5);
        menu.put("cappuccino", 4.2);
        menu.put("latte", 4.1);
    }

    //modifies: this
    //EFFECTS: sets the drink price to the calculated drink price based on the based price
    public void setPrice(String drinkName) {
        this.drinkPrice = priceCalculate(menu.get(drinkName));
    }

    //EFFECTS: Returns the drink price as a function of drink size and to-go
    public double priceCalculate(double price) {
        int drinkSizeNum = 0;
        String drinkSize = this.drinkSize.toLowerCase();

        switch (drinkSize) {
            case "small":
                drinkSizeNum = 12;
                break;
            case "medium":
                drinkSizeNum = 16;
                break;
            case "large":
                drinkSizeNum = 20;
                break;
        }

        double num = price + ((drinkSizeNum - 12.0) / 12) * 0.3 * price;
        return (double)Math.round(num * 10d) / 10d;
    }

    //MODIFIES: cup type, this
    //EFFECTS: Sets the cup type based on whether the drink is to-go and whether the drink is
    // served in an espresso cup
    public void setCupType(boolean toGo) {
        if (toGo) {
            this.cupType = "Paper cup";
            if (this.drinkName.equals("macchiato") || this.drinkName.equals("espresso")) {
                this.drinkSize = "small";
            }
        } else if (this.drinkName.equals("macchiato") || this.drinkName.equals("espresso")) {
            this.cupType = "Demitasse cup";
            this.drinkSize = "Demitasse cup";
        } else {
            this.cupType = "Mug";
        }
    }

    //MODIFIES: addOns, this
    //EFFECTS: initializes a list of add-on objects to the 5 add-ons available
    public void initAddOns() {
        addOns.add(new AddOn("milk"));
        addOns.add(new AddOn("cream"));
        addOns.add(new AddOn("honey"));
        addOns.add(new AddOn("sugar"));
        addOns.add(new AddOn("espresso shot"));
    }

    //EFFECTS: Returns a specified add-on object in a list, and returns null otherwise
    public AddOn getAddOn(String addOnName) {
        for (AddOn item: addOns) {
            if (item.getAddOnName().equals(addOnName)) {
                return item;
            }
        }
        return null;
    }

    //EFFECTS: Returns the add-on object in a list at index i
    public AddOn getAddOn(int i) {
        return addOns.get(i);
    }

    //REQUIRES: addOnName equals an addOnName String initialized in initAddOns()
    //MODIFIES: addOns, this
    //EFFECTS: Increments the add-on number of an add-on object by add-on name
    // and increases the drink price by 1 if the add-on is an espresso
    public void addOn(String addOnName) {
        addOnName = addOnName.toLowerCase();
        getAddOn(addOnName).incrementNum();

        if (addOnName.equals("espresso shot")) {
            drinkPrice += 1;
        }
    }

    public void setDrinkSize(String drinkSize) {
        this.drinkSize = drinkSize;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public Double getDrinkPrice() {
        return drinkPrice;
    }

    public String getCupType() {
        return cupType;
    }

    public String getDrinkSize() {
        return drinkSize;
    }


    public boolean isToGo() {
        return toGo;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// Represents a drink
public class SpecificDrink implements Cloneable {

    //fields
    String drinkName;
    Double drinkPrice;
    String cupType;
    boolean toGo;
    String drinkSize;
    List<AddOn> addOns = new ArrayList<>();

    //EFFECTS: Creates a new medium drink with 0 price and cup type set
    // based on whether the drink is to-go
    public SpecificDrink(String drinkName, boolean toGo) {
        this.drinkName = drinkName;
        this.drinkSize = "medium";
        this.toGo = toGo;
        this.drinkPrice = 0.0;
        setCupType(this.toGo);
    }

    //MODIFIES: drinkPrice, this
    //EFFECTS: Sets the drink price using the name of the drink and the
    // priceCalculate method
    public void setPrice(String drinkName) {
        drinkName = drinkName.toLowerCase();
        switch (drinkName) {
            case "drip coffee":
                this.drinkPrice = priceCalculate(2.2);
                break;
            case "americano":
                this.drinkPrice = priceCalculate(3.1);
                break;
            case "espresso":
                this.drinkPrice = 1.8;
                break;
            case "macchiato":
                this.drinkPrice = 3.5;
                break;
            case "cappuccino":
                this.drinkPrice = priceCalculate(4.2);
                break;
            case "latte":
                this.drinkPrice = priceCalculate(4.1);
                break;
        }
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
            if (this.drinkName.equals("Macchiato") || this.drinkName.equals("Espresso")) {
                this.drinkSize = "small";
            }
        } else if (this.drinkName.equals("Macchiato") || this.drinkName.equals("Espresso")) {
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

    //MODIFIES: addOns, this
    //EFFECTS: Increments the add-on number of an add-on object by add-on name
    // and increases the drink price by 1 if the add-on is an espresso
    public void addOn(String addOn) {
        addOn = addOn.toLowerCase();
        getAddOn(addOn).incrementNum();

        if (addOn.equals("espresso shot")) {
            drinkPrice += 1;
        }
    }

    public void setDrinkSize(String drinkSize) {
        this.drinkSize = drinkSize;
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

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
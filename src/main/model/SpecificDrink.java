package model;

import java.util.ArrayList;
import java.util.List;

public class SpecificDrink implements Cloneable {

    //fields
    String drinkName;
    Double drinkPrice;
    String cupType;
    boolean toGo;
    String drinkSize;
    List<AddOn> addOns = new ArrayList<>();

    //int numOfDrink;

    public SpecificDrink(String drinkName, boolean toGo) {
        this.drinkName = drinkName;
        this.drinkSize = "medium";
        this.toGo = toGo;

        setPrice(this.drinkName);
        setCupType(this.toGo);
    }

    public void setPrice(String drinkName) {
        switch (drinkName) {
            case "Drip Coffee":
                this.drinkPrice = priceCalculate(2.2);
                break;
            case "Americano":
                this.drinkPrice = priceCalculate(3.1);
                break;
            case "Espresso":
                this.drinkPrice = 1.8;
                break;
            case "Macchiato":
                this.drinkPrice = 3.5;
                break;
            case "Cappuccino":
                this.drinkPrice = priceCalculate(4.2);
                break;
            case "Latte":
                this.drinkPrice = priceCalculate(4.1);
                break;
        }
    }

    public void setDrinkSize(String drinkSize) {
        this.drinkSize = drinkSize;
    }

    public void setToGo(boolean toGo) {
        this.toGo = toGo;
    }

    public double priceCalculate(double price) {
        int drinkSizeNum = 0;
        String drinkSizeCalc = drinkSize.toLowerCase();

        switch (drinkSizeCalc) {
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

    //set cupType
    public void setCupType(boolean toGo) {
        if (toGo) {
            this.cupType = "Paper cup";
            this.drinkSize = "small";
        } else if (this.drinkName.equals("Macchiato") || this.drinkName.equals("Espresso")) {
            this.cupType = "Demitasse cup";
            this.drinkSize = "Demitasse cup";
        } else {
            this.cupType = "Mug";
        }
    }

    public void initAddOns() {
        addOns.add(new AddOn("milk"));
        addOns.add(new AddOn("cream"));
        addOns.add(new AddOn("honey"));
        addOns.add(new AddOn("sugar"));
        addOns.add(new AddOn("espresso shot"));
    }

    public AddOn getAddOn(String addOnName) {
        for (AddOn item: addOns) {
            if (item.getAddOnName().equals(addOnName)) {
                return item;
            }
        }
        return null;
    }

    public AddOn getAddOn(int index) {
        return addOns.get(index);
    }

    public void addOn(String addOn) {
        //addOn.toLowerCase();
        getAddOn(addOn).incrementNum(addOn);

        if (addOn.equals("espresso shot")) {
            drinkPrice += 1;
        }
    }

    //getters
    public String getDrinkName() {
        return drinkName;
    }

    public Double getDrinkPrice() {
        return drinkPrice;
    }

    public boolean isToGo() {
        return toGo;
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
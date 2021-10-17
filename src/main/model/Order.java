package model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    //fields
    boolean toGo;
    double total;
    double tax;
    double subtotal;
    int numberOfItems;
    List<SpecificDrink> currentOrder;

    public Order() {
        currentOrder = new ArrayList<>();
    }

    public void addToOrder(SpecificDrink drink) {
        currentOrder.add(drink);
        numberOfItems++;
        drink.initAddOns();
    }

    public void addPriceToDrink(SpecificDrink drink) {
        drink.setPrice(drink.getDrinkName());
        total += drink.getDrinkPrice();
        tax = (0.05 * total);
        subtotal = (double)Math.round((total + tax) * 100d) / 100d;
    }

//remove implementation for increment and decrement OR fix current implementation
    public boolean incrementDrink(String drinkName) throws CloneNotSupportedException {
        if (currentOrder.contains(getDrink(drinkName))) {
            SpecificDrink drinkCopy = (SpecificDrink) getDrink(drinkName).clone();
            addToOrder(drinkCopy);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeFromOrder(String drinkName) {
        if (currentOrder.contains(getDrink(drinkName))) {
            total -= getDrink(drinkName).getDrinkPrice();
            tax = (1.05 * total);
            subtotal = total + tax;
            numberOfItems++;

            currentOrder.remove(getDrink(drinkName));
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfInOrder(String drinkName) {
        for (SpecificDrink item: currentOrder) {
            if (item.getDrinkName().equals(drinkName)) {
                return true;
            }
        }
        return false;
    }

    public SpecificDrink getDrink(String drinkName) {
        for (SpecificDrink item: currentOrder) {
            if (item.getDrinkName().equals(drinkName)) {
                return item;
            }
        }
        return null;
    }

    public SpecificDrink getDrink(int index) {
        return currentOrder.get(index);
    }

    public void setToGo(boolean toGo) {
        this.toGo = toGo;
    }

    public double getTotal() {
        return total;
    }

    public double getTax() {
        return (double)Math.round(tax * 100d) / 100d;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public boolean isToGo() {
        return toGo;
    }
}

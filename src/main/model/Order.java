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
        total += drink.getDrinkPrice();
        tax = (1.05 * total);
        subtotal = total + tax;
        numberOfItems++;
        drink.initAddOns();
    }

    public void addToOrder(String drinkName) {
        SpecificDrink drink = new SpecificDrink(drinkName, isToGo());
        addToOrder(drink);
    }

//remove implementation for increment and decrement OR fix current implementation
    public void incrementAmount(String drinkName) throws CloneNotSupportedException {
        if (currentOrder.contains(getDrink(drinkName))) {
            SpecificDrink drinkCopy = (SpecificDrink) getDrink(drinkName).clone();
            addToOrder(drinkCopy);
        }
    }

    public boolean decrementAmount(String drinkName) {
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

    public SpecificDrink getDrink(String drinkName) {
        for (SpecificDrink item: currentOrder) {
            if (item.getDrinkName().equals(drinkName)) {
                return item;
            }
        }
        return null;
    }

    public void setToGo(boolean toGo) {
        this.toGo = toGo;
    }

    public SpecificDrink getDrink(int index) {
        return currentOrder.get(index);
    }

    public double getTotal() {
        return total;
    }

    public double getTax() {
        return tax;
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

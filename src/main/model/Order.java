/*
 * Class to instantiate an order. Contains a list of drink objects.
 * Implements the basis of user stories involving adding and removing drink objects.
 */

package model;

import java.util.ArrayList;
import java.util.List;

// Represents an order
public class Order {

    //fields
    boolean toGo;
    double total;
    double tax;
    double subtotal;
    int numberOfItems;
    List<Drink> currentOrder;

    //EFFECTS: initializes a list of drinks
    public Order() {
        currentOrder = new ArrayList<>();
    }

    //MODIFIES: this, numberOfItems
    //EFFECTS: Adds a drink to the list of drink objects
    public void addToOrder(Drink drink) {
        currentOrder.add(drink);
        numberOfItems++;
        drink.initAddOns();
    }

    //MODIFIES: this, drinkPrice, total, tax, subtotal
    //EFFECTS: Sets the drink price of a drink object and updates the total,
    // tax, and subtotal
    public void addPriceToDrink(Drink drink) {
        drink.setPrice(drink.getDrinkName());
        total += drink.getDrinkPrice();
        tax = (0.05 * total);
        subtotal = (double)Math.round((total + tax) * 100d) / 100d;
    }

    //MODIFIES: this
    //EFFECTS: Creates a copy of a drink in the order and adds it to the order.
    // Returns true if the drink was copied, false otherwise
    public boolean incrementDrink(String drinkName) throws CloneNotSupportedException {
        if (currentOrder.contains(getDrink(drinkName))) {
            Drink drinkCopy = (Drink) getDrink(drinkName).clone();
            addToOrder(drinkCopy);
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES: this, total, tax, subtotal, numberOfItems
    //EFFECTS: Removes a drink from the order. Returns true if the
    // drink was removed and false otherwise.
    public boolean removeFromOrder(String drinkName) {
        if (currentOrder.contains(getDrink(drinkName))) {
            total -= getDrink(drinkName).getDrinkPrice();
            tax = (0.05 * total);
            subtotal = (double)Math.round((total + tax) * 100d) / 100d;
            numberOfItems--;

            currentOrder.remove(getDrink(drinkName));
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: Returns true if a specified drink is in the order, and false otherwise
    public boolean checkIfInOrder(String drinkName) {
        for (Drink item: currentOrder) {
            if (item.getDrinkName().equals(drinkName)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: Returns drink by name if it is in the order, returns null otherwise
    public Drink getDrink(String drinkName) {
        for (Drink item: currentOrder) {
            if (item.getDrinkName().equals(drinkName)) {
                return item;
            }
        }
        return null;
    }

    //REQUIRES: i < currentOrder length
    //EFFECTS: Returns the drink at index i
    public Drink getDrink(int i) {
        return currentOrder.get(i);
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
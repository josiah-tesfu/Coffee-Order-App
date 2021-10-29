package persistence;

import model.Drink;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkThingy(String drinkName, boolean toGo, Drink drink) {
        assertEquals(drinkName, drink.getDrinkName());
        assertEquals(toGo, drink.isToGo());
    }
}
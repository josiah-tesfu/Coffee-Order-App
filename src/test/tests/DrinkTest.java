package tests;

import model.AddOn;
import model.Drink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DrinkTest {
    private Drink testDrink;
    private AddOn addOn;

    @BeforeEach
    void setUp() {
        testDrink = new Drink("latte", false);
        addOn = new AddOn("milk");
        testDrink.initAddOns();
    }

    @Test
    void testSetPrice() {
        testDrink.setDrinkName("latte");
        testDrink.setPrice("latte");
        Assertions.assertEquals(4.5, testDrink.getDrinkPrice());
    }

    @Test
    void testPriceCalculate() {
        Assertions.assertEquals(0, testDrink.priceCalculate(0));
        Assertions.assertEquals(2.4, testDrink.priceCalculate(2.2));
    }

    @Test
    void testSetCupTypePaper() {
        testDrink.setDrinkName("espresso");
        testDrink.setCupType(true);
        Assertions.assertEquals("Paper cup", testDrink.getCupType());
        Assertions.assertEquals("small", testDrink.getDrinkSize());
    }

    @Test
    void testSetCupTypeDemitasse() {
        testDrink.setDrinkName("espresso");
        testDrink.setCupType(false);
        Assertions.assertEquals("Demitasse cup", testDrink.getCupType());
        Assertions.assertEquals("Demitasse cup", testDrink.getDrinkSize());
    }

    @Test
    void testSetCupTypeMug() {
        testDrink.setDrinkName("latte");
        testDrink.setCupType(false);
        Assertions.assertEquals("Mug", testDrink.getCupType());
        Assertions.assertEquals("medium", testDrink.getDrinkSize());
    }

    @Test
    void testGetAddOn() {
        testDrink.getAddOn("milk");

        Assertions.assertNotNull(testDrink.getAddOn("milk"));
        Assertions.assertNull(testDrink.getAddOn("butter"));

    }

    @Test
    void testAddOnMilk() {
        testDrink.addOn("milk");
        Assertions.assertEquals(1, testDrink.getAddOn("milk").getAddOnNum());
        Assertions.assertEquals(0, testDrink.getDrinkPrice());
    }

    @Test
    void testAddOnEspressoShot() {
        testDrink.addOn("espresso shot");
        Assertions.assertEquals(1, testDrink.getAddOn("espresso shot").getAddOnNum());
        Assertions.assertEquals(1, testDrink.getDrinkPrice());
    }
}
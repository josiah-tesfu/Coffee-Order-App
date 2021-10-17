package model;

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
        assertEquals(4.5, testDrink.getDrinkPrice());
    }

    @Test
    void testPriceCalculate() {
        assertEquals(0, testDrink.priceCalculate(0));
        assertEquals(2.4, testDrink.priceCalculate(2.2));
    }

    @Test
    void testSetCupTypePaper() {
        testDrink.setDrinkName("espresso");
        testDrink.setCupType(true);
        assertEquals("Paper cup", testDrink.getCupType());
        assertEquals("small", testDrink.getDrinkSize());
    }

    @Test
    void testSetCupTypeDemitasse() {
        testDrink.setDrinkName("espresso");
        testDrink.setCupType(false);
        assertEquals("Demitasse cup", testDrink.getCupType());
        assertEquals("Demitasse cup", testDrink.getDrinkSize());
    }

    @Test
    void testSetCupTypeMug() {
        testDrink.setDrinkName("latte");
        testDrink.setCupType(false);
        assertEquals("Mug", testDrink.getCupType());
        assertEquals("medium", testDrink.getDrinkSize());
    }

    @Test
    void testGetAddOn() {
        testDrink.getAddOn("milk");

        assertNotNull(testDrink.getAddOn("milk"));
        assertNull(testDrink.getAddOn("butter"));

    }

    @Test
    void testAddOnMilk() {
        testDrink.addOn("milk");
        assertEquals(1, testDrink.getAddOn("milk").getAddOnNum());
        assertEquals(0, testDrink.getDrinkPrice());
    }

    @Test
    void testAddOnEspressoShot() {
        testDrink.addOn("espresso shot");
        assertEquals(1, testDrink.getAddOn("espresso shot").getAddOnNum());
        assertEquals(1, testDrink.getDrinkPrice());
    }

}
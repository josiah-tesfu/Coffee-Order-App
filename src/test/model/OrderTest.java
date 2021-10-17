package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    Order testCurrentOrder;
    Drink testDrink;

    @BeforeEach
    void setUp() {
        testCurrentOrder = new Order();
        testDrink = new Drink("latte", false);
    }

    @Test
    void testAddToOrder() {
        testCurrentOrder.addToOrder(testDrink);
        assertEquals(1, testCurrentOrder.getNumberOfItems());
        assertNotNull(testDrink.getAddOn(0));
    }

    @Test
    void testAddPriceToDrink() {
        testCurrentOrder.addPriceToDrink(testDrink);
        assertEquals(4.5, testCurrentOrder.getTotal());
        assertEquals(0.23, testCurrentOrder.getTax());
        assertEquals(4.72, testCurrentOrder.getSubtotal());
    }

    @Test
    void testIncrementDrink() throws CloneNotSupportedException {
        testCurrentOrder.addToOrder(testDrink);
        testCurrentOrder.incrementDrink("latte");
        assertEquals(2, testCurrentOrder.getNumberOfItems());
    }

    @Test
    void testRemoveFromOrderTrue() {
        testCurrentOrder.addToOrder(testDrink);
        testCurrentOrder.addPriceToDrink(testDrink);
        testCurrentOrder.addToOrder(testDrink);
        testCurrentOrder.addPriceToDrink(testDrink);
        assertTrue(testCurrentOrder.removeFromOrder("latte"));
        assertEquals(4.5, testCurrentOrder.getTotal());
        assertEquals(0.23, testCurrentOrder.getTax());
        assertEquals(4.72, testCurrentOrder.getSubtotal());
        assertEquals(1, testCurrentOrder.getNumberOfItems());
    }

    @Test
    void testRemoveFromOrderFalse() {
        testCurrentOrder.addToOrder(testDrink);
        testCurrentOrder.addPriceToDrink(testDrink);
        testCurrentOrder.addToOrder(testDrink);
        testCurrentOrder.addPriceToDrink(testDrink);
        assertFalse(testCurrentOrder.removeFromOrder("espresso"));
        assertEquals(9.0, testCurrentOrder.getTotal());
        assertEquals(0.45, testCurrentOrder.getTax());
        assertEquals(9.45, testCurrentOrder.getSubtotal());
        assertEquals(2, testCurrentOrder.getNumberOfItems());
    }

    @Test
    void testCheckIfInOrderTrue() {
        testCurrentOrder.addToOrder(testDrink);
        assertTrue(testCurrentOrder.checkIfInOrder("latte"));
    }

    @Test
    void testCheckIfInOrderFalse() {
        testCurrentOrder.addToOrder(testDrink);
        assertFalse(testCurrentOrder.checkIfInOrder("espresso"));
    }

    @Test
    void testGetDrinkNotNull() {
        testCurrentOrder.addToOrder(testDrink);
        assertNotNull(testCurrentOrder.getDrink("latte"));
    }

    @Test
    void testGetDrinkNull() {
        testCurrentOrder.addToOrder(testDrink);
        assertNull(testCurrentOrder.getDrink("espresso"));
    }


    @Test
    void testGetDrinkIndex() {
        testCurrentOrder.addToOrder(testDrink);
        assertNotNull(testCurrentOrder.getDrink(0));
    }
}
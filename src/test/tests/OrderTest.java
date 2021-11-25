package tests;

import model.Drink;
import model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderTest {
    Order testCurrentOrder;
    Drink testDrink;
    boolean testToGo;

    @BeforeEach
    void setUp() {
        testCurrentOrder = new Order(testToGo);
        testDrink = new Drink("latte", testToGo);
    }

    @Test
    void testAddToOrder() {
        testCurrentOrder.addToOrder(testDrink);
        Assertions.assertEquals(1, testCurrentOrder.getNumberOfItems());
        Assertions.assertNotNull(testDrink.getAddOn(0));
    }

    @Test
    void testAddPriceToDrink() {
        testCurrentOrder.addPriceToDrink(testDrink);
        Assertions.assertEquals(4.5, testCurrentOrder.getTotal());
        Assertions.assertEquals(0.23, testCurrentOrder.getTax());
        Assertions.assertEquals(4.72, testCurrentOrder.getSubtotal());
    }

//    @Test
//    void testIncrementDrink() throws CloneNotSupportedException {
//        testCurrentOrder.addToOrder(testDrink);
//        testCurrentOrder.incrementDrink("latte");
//        Assertions.assertEquals(2, testCurrentOrder.getNumberOfItems());
//    }

    @Test
    void testRemoveFromOrderTrue() {
        testCurrentOrder.addToOrder(testDrink);
        testCurrentOrder.addPriceToDrink(testDrink);
        testCurrentOrder.addToOrder(testDrink);
        testCurrentOrder.addPriceToDrink(testDrink);
        Assertions.assertTrue(testCurrentOrder.removeFromOrder("latte"));
        Assertions.assertEquals(4.5, testCurrentOrder.getTotal());
        Assertions.assertEquals(0.23, testCurrentOrder.getTax());
        Assertions.assertEquals(4.72, testCurrentOrder.getSubtotal());
        Assertions.assertEquals(1, testCurrentOrder.getNumberOfItems());
    }

    @Test
    void testRemoveFromOrderFalse() {
        testCurrentOrder.addToOrder(testDrink);
        testCurrentOrder.addPriceToDrink(testDrink);
        testCurrentOrder.addToOrder(testDrink);
        testCurrentOrder.addPriceToDrink(testDrink);
        Assertions.assertFalse(testCurrentOrder.removeFromOrder("espresso"));
        Assertions.assertEquals(9.0, testCurrentOrder.getTotal());
        Assertions.assertEquals(0.45, testCurrentOrder.getTax());
        Assertions.assertEquals(9.45, testCurrentOrder.getSubtotal());
        Assertions.assertEquals(2, testCurrentOrder.getNumberOfItems());
    }

    @Test
    void testCheckIfInOrderTrue() {
        testCurrentOrder.addToOrder(testDrink);
        Assertions.assertTrue(testCurrentOrder.checkIfInOrder("latte"));
    }

    @Test
    void testCheckIfInOrderFalse() {
        testCurrentOrder.addToOrder(testDrink);
        Assertions.assertFalse(testCurrentOrder.checkIfInOrder("espresso"));
    }

    @Test
    void testGetDrinkNotNull() {
        testCurrentOrder.addToOrder(testDrink);
        Assertions.assertNotNull(testCurrentOrder.getDrink("latte"));
    }

    @Test
    void testGetDrinkNull() {
        testCurrentOrder.addToOrder(testDrink);
        Assertions.assertNull(testCurrentOrder.getDrink("espresso"));
    }

    @Test
    void testGetDrinkIndex() {
        testCurrentOrder.addToOrder(testDrink);
        Assertions.assertNotNull(testCurrentOrder.getDrink(0));
    }
}
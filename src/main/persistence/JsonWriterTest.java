package persistence;

import model.Drink;
import model.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Order od = new Order(false);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyOrder() {
        try {
            Order od = new Order(false);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyOrder.json");
            writer.open();
            writer.write(od);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyOrder.json");
            od = reader.read();
            assertFalse(od.isToGo());
            assertEquals(0, od.getTotal());
            assertEquals(0, od.getTax());
            assertEquals(0, od.getSubtotal());
            assertEquals(0, od.getNumberOfItems());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralOrder() {
        try {
            Order od = new Order(false);
            od.addToOrder(new Drink("drip coffee", false));
            od.addToOrder(new Drink("americano", false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralOrder.json");
            writer.open();
            writer.write(od);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralOrder.json");
            od = reader.read();
            assertFalse(od.isToGo());
            assertEquals(0, od.getTotal());
            assertEquals(0, od.getTax());
            assertEquals(0, od.getSubtotal());
            List<Drink> drinks = od.getDrinks();
            assertEquals(2, drinks.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
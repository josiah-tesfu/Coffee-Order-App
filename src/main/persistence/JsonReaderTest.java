package persistence;

import model.Drink;
import model.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Order od = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyOrder.json");
        try {
            Order od = reader.read();
            assertFalse(od.isToGo());
            assertEquals(0, od.getTotal());
            assertEquals(0, od.getTax());
            assertEquals(0, od.getSubtotal());
            assertEquals(0, od.getNumberOfItems());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralOrder.json");
        try {
            Order od = reader.read();
            assertFalse(od.isToGo());
            assertEquals(5.5, od.getTotal());
            assertEquals(0.28, od.getTax());
            assertEquals(5.78, od.getSubtotal());
            List<Drink> drinks = od.getDrinks();
            assertEquals(2, drinks.size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

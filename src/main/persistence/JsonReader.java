package persistence;

import model.Drink;
import model.Order;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    public Order read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseOrder(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    private Order parseOrder(JSONObject jsonObject) {
        boolean toGo = jsonObject.getBoolean("toGo");
        double total = jsonObject.getDouble("total");
        double tax = jsonObject.getDouble("tax");
        double subtotal = jsonObject.getDouble("subtotal");
        Order od = new Order(toGo);
        od.setTotal(total);
        od.setTax(tax);
        od.setSubtotal(subtotal);
        addToOrder(od, jsonObject);
        return od;
    }

    private void addToOrder(Order od, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("currentOrder");
        for (Object json : jsonArray) {
            JSONObject nextDrink = (JSONObject) json;
            addDrink(od, nextDrink);
        }
    }

    private void addDrink(Order od, JSONObject jsonObject) {
        String drinkName = jsonObject.getString("drinkName");
        boolean toGo = jsonObject.has("toGo");
        Drink drink = new Drink(drinkName, toGo);
        od.addToOrder(drink);
    }
}
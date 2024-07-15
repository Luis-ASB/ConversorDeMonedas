import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;

public class FiltrarMonedas {
    public static Map<String, Double> filterCurrencies(JsonObject rates) {
        // Almacenar las tasas de cambio
        Map<String, Double> filteredRates = new HashMap<>();
        String[] currencies = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};

        // Iteración para obtener la tasa de cambio y almacenarla
        for (String currency : currencies) {
            filteredRates.put(currency, rates.getAsJsonObject("conversion_rates").get(currency).getAsDouble());
        }

        return filteredRates;
    }
}

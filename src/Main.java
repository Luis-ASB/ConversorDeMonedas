import java.util.Map;
import java.util.Scanner;
import com.google.gson.JsonObject;

public class Main {
    public static void main(String[] args) {
        try {
            // Obtener las tasas de cambio de la API
            JsonObject rates = ConsumoAPI.getRates();
            // Filtrar las tasas de cambio de interés
            Map<String, Double> filteredRates = FiltrarMonedas.filterCurrencies(rates);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Mostrar el menú de selección de moneda de origen
                System.out.println("Seleccione la moneda de origen:");
                String[] currencyArray = filteredRates.keySet().toArray(new String[0]);
                for (int i = 0; i < currencyArray.length; i++) {
                    System.out.println((i + 1) + ". " + currencyArray[i]);
                }
                int fromIndex = scanner.nextInt() - 1;
                String fromCurrency = currencyArray[fromIndex];

                // Mostrar el menú de selección de moneda de destino
                System.out.println("Seleccione la moneda de destino:");
                for (int i = 0; i < currencyArray.length; i++) {
                    System.out.println((i + 1) + ". " + currencyArray[i]);
                }
                int toIndex = scanner.nextInt() - 1;
                String toCurrency = currencyArray[toIndex];

                // Pedir al usuario que ingrese la cantidad a convertir
                System.out.println("Ingrese la cantidad a convertir:");
                double amount = scanner.nextDouble();

                // Calcular el monto convertido
                double convertedAmount = amount * filteredRates.get(toCurrency) / filteredRates.get(fromCurrency);
                System.out.println(amount + " " + fromCurrency + " son " + convertedAmount + " " + toCurrency);

                // Preguntar al usuario si desea realizar otra conversión
                System.out.println("¿Desea realizar otra conversión? (si/no)");
                scanner.nextLine(); // Limpiar el buffer
                String response = scanner.nextLine();
                if (!response.equalsIgnoreCase("si")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

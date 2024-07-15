import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConsumoAPI {

    // URL de la API con la clave de API.
    // Debes reemplazar "YOUR_API_KEY" con tu clave de API real.
    private static String API_URL = "https://v6.exchangerate-api.com/v6/YOUR_API_KEY/latest/USD";

    // Método que realiza una solicitud API y devuelve las tasas de cambio como un JsonObject.
    public static JsonObject getRates() {
        try {
            // Crear un objeto URI a partir de la URL de la API
            URI direccion = URI.create(API_URL);

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();

            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parsear el contenido de la respuesta a un JsonObject y devolverlo
            return JsonParser.parseString(response.body()).getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException("No se pudo obtener las tasas de cambio", e);
        }
    }
}

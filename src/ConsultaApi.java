import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {
    public RespuestaApi obtenerTasas(String monedaBase) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/0cd1b933eae00477f43c2d43/latest/" + monedaBase);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), RespuestaApi.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("No se pudo obtener la tasa de cambio: " + e.getMessage());
        }
    }
}
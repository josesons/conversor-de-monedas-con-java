import java.util.Map;

public record RespuestaApi(String USD, String ARS, String BRL, String COP, Map<String, Double> conversion_rates) {
}
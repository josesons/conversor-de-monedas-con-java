import java.util.Map;

public class Conversor {
    public double convertir(double cantidad, String monedaOrigen, String monedaDestino, Map<String, Double> tasas) {
        Double tasaOrigen = tasas.get(monedaOrigen);
        Double tasaDestino = tasas.get(monedaDestino);

        if (tasaOrigen == null || tasaDestino == null) {
            throw new IllegalArgumentException("Una o ambas monedas no fueron encontradas en las tasas de cambio.");
        }
        return (cantidad / tasaOrigen) *  tasaDestino;
    }
}

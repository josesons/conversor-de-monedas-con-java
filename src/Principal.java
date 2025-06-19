import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaApi consulta = new ConsultaApi();
        Conversor conversor = new Conversor();

        RespuestaApi tasas = consulta.obtenerTasas("USD");
        if (tasas == null || tasas.conversion_rates() == null) {
            System.out.println("Error al obtener las tasas de cambio. El programa no puede continuar.");
            return;
        }

        while (true) {
            System.out.println("*******************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda\n");
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Salir");
            System.out.print("Elija una opción válida: ");
            System.out.println("\n*******************************************");

            try {
                int opcion = Integer.parseInt(lectura.nextLine());

                if (opcion == 7) {
                    System.out.println("Saliendo del programa. ¡Gracias por usar nuestros servicios!");
                    break;
                }

                if (opcion < 1 || opcion > 6) {
                    System.out.println("Opción no válida. por favor, intente de nuevo.");
                    continue;
                }

                System.out.println("Ingrese el valor que deseas convertir: ");
                double cantidad = Double.parseDouble(lectura.nextLine());

                String monedaOrigen = "";
                String monedaDestino = "";

                switch (opcion) {
                    case 1 -> { monedaOrigen = "USD"; monedaDestino = "ARS"; }
                    case 2 -> { monedaOrigen = "ARS"; monedaDestino = "USD"; }
                    case 3 -> { monedaOrigen = "USD"; monedaDestino = "BRL"; }
                    case 4 -> { monedaOrigen = "BRL"; monedaDestino = "USD"; }
                    case 5 -> { monedaOrigen = "USD"; monedaDestino = "COP"; }
                    case 6 -> { monedaOrigen = "COP"; monedaDestino = "USD"; }
                }

                double resultado = conversor.convertir(cantidad, monedaOrigen, monedaDestino, tasas.conversion_rates());
                System.out.printf("El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s]%n%n",
                        cantidad, monedaOrigen, resultado, monedaDestino);
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese un número válido para la opción y la cantidad.");
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        }
        lectura.close();
    }
}

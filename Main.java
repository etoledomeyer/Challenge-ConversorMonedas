import java.util.*;
import java.io.IOException;
//Programa principal - menú con usuario
public class Main {

    public static void main(String[] args) {
        String from;
        String to;

        Scanner scanner = new Scanner(System.in);
        CurrencyConverterService service = new CurrencyConverterService();

        List<CurrencyCode> currencies;
        try {
            currencies = service.getSupportedCurrencies();
        }
        catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener las monedas: " + e.getMessage());
            return;
        }

        // Mostrar lista de monedas formateada
        System.out.println("Monedas disponibles:");
        printCurrencyList(currencies);

        while (true) {
            do {
                System.out.print("\nIngrese el código de la moneda ORIGEN (ej. USD) o 'salir' para terminar: ");
                from = scanner.nextLine().trim().toUpperCase();

                if (from.equalsIgnoreCase("salir")) {
                    System.out.println("\nGracias por usar el conversor de monedas. ¡Hasta luego!");
                    return;
                }

                if (from.isEmpty()) {
                    System.out.println("⚠️ El código de la moneda ORIGEN no puede estar vacío.");
                }
                else if (!isCurrencyCodeValid(from, currencies)) {
                    System.out.println("❌ Código de moneda ORIGEN no válido. Intente nuevamente.");
                }
                else {
                    break; // válido
                }
            } while (true);


            do {
                System.out.print("Ingrese el código de la moneda DESTINO (ej. EUR): ");
                to = scanner.nextLine().trim().toUpperCase();

                if (to.isEmpty()) {
                    System.out.println("⚠️ El código de la moneda DESTINO no puede estar vacío.");
                } else if (!isCurrencyCodeValid(to, currencies)) {
                    System.out.println("❌ Código de moneda DESTINO no válido. Intente nuevamente.");
                } else {
                    break; // válido
                }
            } while (true);

            System.out.print("Ingrese la cantidad a convertir: ");
            double amount;
            try {
                amount = Double.parseDouble(scanner.nextLine().trim());
            }
            catch (NumberFormatException e) {
                System.out.println("Cantidad inválida. Intente nuevamente.");
                continue;
            }

            try {
                ConversionResponse result = service.convert(from, to, amount);

                if ("success".equalsIgnoreCase(result.result())) {
                    System.out.printf("\nTasa: 1 %s = %.4f %s%n",
                            result.base_code(), result.conversion_rate(), result.target_code());
                    System.out.printf("Resultado: %.2f %s = %.2f %s%n",
                            amount, from, result.conversion_result(), to);
                }
                else {
                    System.out.println("Error al realizar la conversión. Verifique los códigos ingresados.");
                }
            }
            catch (IOException | InterruptedException e) {
                System.out.println("Error en la conversión: " + e.getMessage());
            }
        }

    }

    // Método auxiliar para imprimir las monedas en formato horizontal
    private static void printCurrencyList(List<CurrencyCode> currencies) {
        int count = 0;
        for (CurrencyCode currency : currencies) {
            System.out.printf("%-5s %-40s   ", currency.code(), currency.name());
            count++;
            if (count % 3 == 0) {
                System.out.println(); // salto de línea cada 4 monedas
            }
        }
        if (count % 3 != 0) {
            System.out.println(); // salto final si no se completó la última línea
        }
    }

    // ✅ Método auxiliar para validar si el código de moneda está en la lista
    private static boolean isCurrencyCodeValid(String code, List<CurrencyCode> currencies) {
        for (CurrencyCode currency : currencies) {
            if (currency.code().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

}


package api;

import java.util.Scanner;

public class Menu {
	
	private final Scanner sc = new Scanner(System.in);
	
	public void mostrarMenu() {
		int opcion;
		
		do {
			System.out.println("\n=== CONVERSOR DE MONEDAS ===");
            System.out.println("1. Dolares (USD) → Pesos Argentinos");
            System.out.println("2. Dolares (USD) → Reales Brasileros");
            System.out.println("3. Dolares (USD) → Pesos Colombianos");
            System.out.println("4. Pesos Argentinos → USD");
            System.out.println("5. Reales Brasileros → USD");
            System.out.println("6. Pesos Colombianos → USD");
            System.out.println("7. Salir");
            System.out.print("Selecciona una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> convertir("USD", "ARS");
                case 2 -> convertir("USD", "BRL");
                case 3 -> convertir("USD", "COP");
                case 4 -> convertir("ARS", "USD");
                case 5 -> convertir("BRL", "USD");
                case 6 -> convertir("COP", "USD");
                case 7 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción inválida. Intenta de nuevo.");
            }
		} while(opcion != 7);
	}

	private void convertir(String desde, String hasta) {
		try {
			System.out.println("Ingresa el monto a convertir");
			double monto = sc.nextDouble();
			double tasa = ApiCliente.obtenerTasa(desde, hasta);
			double convertido = monto * tasa;
			
			System.out.printf("Tasa actual: 1 %s = %.2f %s%n", desde, tasa, hasta);
            System.out.printf("Resultado: %.2f %s = %.2f %s%n", monto, desde, convertido, hasta);

		} catch (Exception e) {
			System.out.println("Error al convertir: " + e.getMessage());
		}
	}

}

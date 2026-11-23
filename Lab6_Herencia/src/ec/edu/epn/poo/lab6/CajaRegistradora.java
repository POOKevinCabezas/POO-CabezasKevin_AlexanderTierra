package ec.edu.epn.poo.lab6;

import java.util.Locale;
import java.util.Scanner;

public class CajaRegistradora {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Producto[] catalogo = {
            new Producto("Cuaderno", 2.50, 10),
            new Producto("Esfero", 0.75, 50),
            new Producto("Borrador", 0.40, 30),
            new Producto("Regla", 1.20),
            new Producto("Carpeta", 3.10, 8)
        };

        int[] carrito = new int[catalogo.length];
        System.out.println("Catálogo cargado: " + Producto.getCreados()
                + " productos");

        int opcion;
        do {
            System.out.println("\n=== CAJA REGISTRADORA ===");
            System.out.println("1. Ver catálogo");
            System.out.println("2. Agregar producto");
            System.out.println("3. Ver recibo");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1:
                    mostrarCatalogo(catalogo);
                    break;

                case 2:
                    System.out.print("Código (1 a " + catalogo.length + "): ");
                    int cod = leerEntero(sc);
                    if (cod < 1 || cod > catalogo.length) {
                        System.out.println("Código inválido.");
                        break;
                    }
                    int k = cod - 1;
                    Producto p = catalogo[k];

                    System.out.print("Cantidad: ");
                    int cantidad = leerEntero(sc);

                    if (p.vender(cantidad)) {
                        carrito[k] += cantidad;
                        System.out.println("Agregado: " + cantidad
                                + " x " + p.getNombre());
                    } else {
                        System.out.println("Stock insuficiente (disponible: "
                                + p.getStock() + ")");
                    }
                    break;

                case 3:
                    double subtotal = 0;
                    System.out.println("----------- RECIBO -----------");
                    for (int i = 0; i < catalogo.length; i++) {
                        if (carrito[i] > 0) {
                            Producto prod = catalogo[i];
                            double linea = prod.calcularSubtotal(carrito[i]);
                            subtotal += linea;
                            System.out.printf(Locale.US,
                                    "%-10s %d x %6.2f %8.2f%n",
                                    prod.getNombre(), carrito[i],
                                    prod.getPrecio(), linea);
                        }
                    }
                    double descuento = 0.00;
                    double iva = (subtotal - descuento) * Producto.IVA;
                    double total = subtotal - descuento + iva;

                    System.out.println("------------------------------");
                    System.out.printf(Locale.US, "%-20s %9.2f%n", "Subtotal", subtotal);
                    System.out.printf(Locale.US, "%-20s %9.2f%n", "Descuento", descuento);
                    System.out.printf(Locale.US, "%-20s %9.2f%n", "IVA 15 %", iva);
                    System.out.printf(Locale.US, "%-20s %9.2f%n", "TOTAL", total);
                    break;

                case 0:
                    System.out.println("Gracias por su compra.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        sc.close();
    }

    private static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("No es un entero. Cantidad: ");
        }
        return sc.nextInt();
    }

    private static void mostrarCatalogo(Producto[] catalogo) {
        System.out.printf("%-4s %-12s %8s %7s%n",
                "Cód", "Producto", "Precio", "Stock");
        for (int i = 0; i < catalogo.length; i++) {
            Producto p = catalogo[i];
            System.out.printf(Locale.US, "%-4d %-12s %8.2f %7d%n",
                    i + 1, p.getNombre(), p.getPrecio(), p.getStock());
        }
    }
}
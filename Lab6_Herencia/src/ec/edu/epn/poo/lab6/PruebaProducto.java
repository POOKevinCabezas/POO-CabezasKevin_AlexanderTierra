package ec.edu.epn.poo.lab6;

public class PruebaProducto {
    static int ok = 0;
    static int fallas = 0;

    static void verificar(String caso, boolean condicion) {
        if (condicion) {
            ok++;
            System.out.println("OK     " + caso);
        } else {
            fallas++;
            System.out.println("FALLA  " + caso);
        }
    }

    public static void main(String[] args) {
        Producto c = new Producto("Cuaderno", 2.50, 10);

        verificar("1. vender(3) devuelve true", c.vender(3));
        verificar("2. el stock baja a 7", c.getStock() == 7);
        verificar("3. vender(99) devuelve false", !c.vender(99));
        verificar("4. un pedido rechazado no cambia el stock",
                c.getStock() == 7);

        // TODO 1: casos 5 y 6. vender(0) y vender(-2) devuelven false
        verificar("5. vender(0) devuelve false", !c.vender(0));
        verificar("6. vender(-2) devuelve false", !c.vender(-2));

        // TODO 2: casos 7 y 8. setPrecio(-1) devuelve false y el precio sigue en 2.50
        verificar("7. setPrecio(-1) devuelve false", !c.setPrecio(-1));
        verificar("8. el precio sigue en 2.50", c.getPrecio() == 2.50);

        // TODO 3: casos 9 y 10. new Producto("Regla", 1.20) nace con stock 0 y isAgotado() es true
        Producto r = new Producto("Regla", 1.20);
        verificar("9. la regla nace con stock 0", r.getStock() == 0);
        verificar("10. la regla está agotada", r.isAgotado());

        // TODO 4: caso 11. un stock negativo en el constructor lanza IllegalArgumentException
        boolean lanzo = false;
        try {
            new Producto("Lápiz", 0.30, -5);
        } catch (IllegalArgumentException e) {
            lanzo = true;
        }
        verificar("11. stock negativo en el constructor lanza excepción",
                lanzo);

        System.out.printf("%n%d OK · %d FALLA%n", ok, fallas);
    }
}
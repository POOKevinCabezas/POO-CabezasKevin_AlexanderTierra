package ec.edu.epn.poo.lab6;

public class PruebaDigital {
    public static void main(String[] args) {
        ProductoDigital tarjeta = new ProductoDigital("Tarjeta Regalo", 10.00, "GIFT-10");
        System.out.println(tarjeta);
        System.out.println(tarjeta.isAgotado());
        System.out.println(tarjeta.vender(1));
        tarjeta.reponer(1000);
        System.out.println(tarjeta.vender(1));
        System.out.println(tarjeta);
    }
}
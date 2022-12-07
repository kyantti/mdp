package main.java.es.unex.cum.mdp.prueba;

public class Prueba {
    public static void main(String[] args) {
        String message = "1*-3";
        String[] msg = message.split("\\*");
        Float x = Float.parseFloat(msg[0]);
        Float y = Float.parseFloat(msg[1]);

        System.out.println(x);
        System.out.println(y);
    }
}

package main.java.es.unex.cum.mdp.sesion0;

import java.io.*;

/** Clase para la entrada de datos por Teclado
 * @author MDP
 */
public class Teclado {

    private BufferedReader Input = new BufferedReader(new InputStreamReader(System.in));
    private boolean CV= false;
    public Teclado() {
    }

  /**
     * Lee un entero. Sigue pidiendo el dato hasta que sea correcto.
     * @return Entero leido
     * @throws IOException 
     */
  public int leerEntero() throws IOException {
        while (true) {
            String s = Input.readLine();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
               if (!CV)
               	   System.out.println("Error en el numero, prueba de nuevo.");
            }
        }
    }

    /**
     * Lee un carácter. Si se leen varios, se queda únicamente con el primero
     * @return Caracterleido
     * @throws IOException 
     */
    public char leerCaracter() throws IOException {
        while (true) {
            String s = Input.readLine();
            try {
                return s.trim().charAt(0);
            } catch (StringIndexOutOfBoundsException e) {
            	if (!CV)
                System.out.println("Linea vacia, prueba de nuevo.");
            }
        }
    }

    /**
     * Lee un double. Sigue pidiendo el dato hasta que sea correcto.
     * @return Double leido
     * @throws IOException 
     */
    public double leerDouble() throws IOException {
        while (true) {
            String s = Input.readLine();
            try {
                return Double.valueOf(s.trim()).doubleValue();
            } catch (NumberFormatException e) {
            	if (!CV)
                System.out.println("Error en el numero, prueba de nuevo.");
            }
        }
    }

    /**
     * Lee un float. Sigue pidiendo el dato hasta que sea correcto.
     * @return Float leido
     * @throws IOException 
     */
    public float leerFloat() throws IOException {
        while (true) {
            String s = Input.readLine();
            try {
                return Float.valueOf(s.trim()).floatValue();
            } catch (NumberFormatException e) {
            	if (!CV)
                System.out.println("Error en el numero, prueba de nuevo.");
            }
        }
    }

    /**
     * Lee una línea
     * @return String con la cadena leída
     * @throws IOException 
     */
    public String leerLinea() throws IOException {
        return Input.readLine();
    }

    /**
     * Muestra por pantalla un menú y lee por teclado. Controla que el valor devuelto
     * este entre el rango del menú
     * @param args Vector de cadena con las distintas posiblidades del menu
     * @param min Mínimo valor del menú
     * @param max Máximo valor del menú
     * @return Entero con la opción leída
     * @throws IOException 
     */
    public int Menu(String[] args, int min, int max) throws IOException {
        int opcion = 0;
        do {
            try { //Try de la excepción por si se introduce una letra en vez de un número en el menú o no se introduce nada

                if (!CV) {
                	System.out.println("\n\n########################################");
                System.out.println("######            MENU            ######");
                System.out.println("########################################");
                }
                if (!CV) {
                    for (int i = 0; i < args.length; i++) {
                        System.out.println(args[i]);
                    }
                }
                if (!CV) {
                System.out.println("########################################");
                System.out.print("Elija una opcion: ");
                }
                opcion = Integer.parseInt(Input.readLine()); //Convertimos lo que leemos por teclado y lo convertimos a entero
                if (!CV)
                	System.out.print("\n");

            } catch (NumberFormatException e) {  //Excepción por si se introduce una letra en vez de un número en el menú o no se introduce nada
                if (!CV)
                	System.out.print("\nHa introducido un carácter y no un número o no ha introducido nada\n");
            }
        } while (!(opcion >= min) || !(opcion <= max));
        return opcion;

    }
    
    /**
     * Método que se encarga de mostrar un literal (s) y leer un valor entero.
     * @param s Literal a mostrar
     * @return Entero leído
     * @throws IOException 
     */
    public int literalConEntero(String s) throws IOException{
        if (!CV)
        	System.out.println(s);
        return this.leerEntero();                    
    }
    
    /**
     * Método que se encarga de mostrar un literal (s) y leer un valor String
     * @param s Literal a mostrar
     * @return String leído
     * @throws IOException 
     */
    public String literalConString(String s) throws IOException{
        if (!CV)
        	System.out.println(s);
        return this.leerLinea();
    }
    
        /**
     * Método que se encarga de mostrar un literal (s) y leer un valor float
     * @param s Literal a mostrar
     * @return Float leído
     * @throws IOException 
     */
    public Float literalConFloat(String s) throws IOException{
        if (!CV)
        	System.out.println(s);
        return this.leerFloat();
    }
}

package main.java.es.unex.cum.mdp.ef2;

public class FactoriaPersona {
    
    /** 
     * Crea una persona segun su tipo y atributos.
     * @param tipo
     * @param nombre
     * @param id
     * @param edad
     * @param value1
     * @param value2
     * @return Persona
     */
    public Persona build(String tipo, String nombre, int id, int edad, String value1, String value2){
        Persona persona = null;

        if (tipo.equals("Jugador")) {
            persona = new Jugador(nombre, edad, id, value1, Integer.parseInt(value2));
        }
        else if(tipo.equals("Juez")){
            persona = new Juez(nombre, edad, id, Integer.parseInt(value1));
        }
        else if(tipo.equals("Directivo")){
            persona = new Directivo(nombre, edad, id, value1);
        }

        return persona;
    }
}

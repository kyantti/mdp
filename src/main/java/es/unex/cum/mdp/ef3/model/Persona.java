package main.java.es.unex.cum.mdp.ef3.model;

import java.io.Serializable;

public class Persona implements Serializable {
    private String nombre;
    private int edad;
    private int id;
    

    public Persona (){
        nombre = "";
		id    =  0;
		edad   = 0;
    }

    public Persona(String nombre, int edad, int id) {
        this.nombre = nombre;
        this.edad = edad;
        this.id = id;
    }
    
    /** 
     * Obtiene el nombre de la persona.
     * @return String
     */
    public String getNombre() {
        return nombre;
    }
    
    /** 
     * Establece el nombre de la persona.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /** 
     * Obtiene la edad de la persona.
     * @return int
     */
    public int getEdad() {
        return edad;
    }
    
    /** 
     * Establece la edad de la persona.
     * @param edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    /** 
     * Obtiene el identificador de la persona.
     * @return int
     */
    public int getId() {
        return id;
    }
    
    /** 
     * Establece el identificador de la persona.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + edad;
        result = prime * result + id;
        return result;
    }
    
    /** 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Persona other = (Persona) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (edad != other.edad)
            return false;
        if (id != other.id)
            return false;
        return true;
    }
    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Persona [nombre=" + nombre + ", edad=" + edad + ", id=" + id + "]";
    }

    
}

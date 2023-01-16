package main.java.es.unex.cum.mdp.ef3.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Temporada implements Serializable {
    private String nombre;
    private Map <String, Liga> ligas;

    public Temporada(String nombre){
        this.nombre = nombre;
        ligas = new HashMap<>();
    }
    
    public Temporada(String nombre, Map<String, Liga> ligas) {
        this.nombre = nombre;
        this.ligas = ligas;
    }

    
    /** 
     * Obtiene el nombre de la temporada.
     * @return String
     */
    public String getNombre() {
        return nombre;
    }

    
    /** 
     * Establece el nombre de la temporada.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    /** 
     * Obtiene las ligas de la temporada.
     * @return Map<String, Liga>
     */
    public Map<String, Liga> getLigas() {
        return ligas;
    }

    
    /** 
     * Establece las ligas de la temporada.
     * @param ligas
     */
    public void setLigas(Map<String, Liga> ligas) {
        this.ligas = ligas;
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((ligas == null) ? 0 : ligas.hashCode());
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
        Temporada other = (Temporada) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (ligas == null) {
            if (other.ligas != null)
                return false;
        } else if (!ligas.equals(other.ligas))
            return false;
        return true;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Temporada [nombre=" + nombre + ", ligas=" + ligas + "]";
    }

    public String getNombresLigas(){
        String nombresLigas = "";
        for (Map.Entry<String, Liga> set : ligas.entrySet()){
            nombresLigas = nombresLigas + set.getValue().getNombre() + ", ";
        }
        return nombresLigas; 
    }

    
}

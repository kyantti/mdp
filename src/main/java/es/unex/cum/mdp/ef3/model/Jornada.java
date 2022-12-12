package main.java.es.unex.cum.mdp.ef3.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Jornada {
    private Date fecha;
    private int numero;
    private List <Partido> partidos;

    public Jornada(int numero) {
        fecha = new Date();
        this.numero = numero;
        partidos = new ArrayList<>();
    }

    public Jornada(Date fecha, int numero, List<Partido> partidos) {
        this.fecha = fecha;
        this.numero = numero;
        this.partidos = partidos;
    }

    
    /** 
     * Obtiene la fecha de la jornada.
     * @return Date
     */
    public Date getFecha() {
        return fecha;
    }

    
    /** 
     * Obtiene la fecha de la jornada.
     * @param fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
    /** 
     * Obtiene el numero de la jornada.
     * @return int
     */
    public int getNumero() {
        return numero;
    }

    
    /** 
     * Obtiene el numero de la jornada.
     * @param numero
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    
    /** 
     * Obtine la lista de partidos de la jornada.
     * @return List<Partido>
     */
    public List<Partido> getPartidos() {
        return partidos;
    }

    
    /** 
     * Establece la lista de partidos de la jornada.
     * @param partidos
     */
    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
        result = prime * result + numero;
        result = prime * result + ((partidos == null) ? 0 : partidos.hashCode());
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
        Jornada other = (Jornada) obj;
        if (fecha == null) {
            if (other.fecha != null)
                return false;
        } else if (!fecha.equals(other.fecha))
            return false;
        if (numero != other.numero)
            return false;
        if (partidos == null) {
            if (other.partidos != null)
                return false;
        } else if (!partidos.equals(other.partidos))
            return false;
        return true;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Jornada [fecha=" + fecha + ", numero=" + numero + ", partidos=" + partidos + "]";
    }

    
    /** 
     * Añade un partido a la lista de partidos.
     * @param partido
     * @return boolean
     */
    public boolean addPartido(Partido partido){
        if (partido != null && !partidos.contains(partido)) {
            partidos.add(partido);
        }
        return false;
    }

    
    /** 
     * Obtiene un partido de la lista de partidos a través de su identificador.
     * @param id
     * @return Partido
     */
    public Partido gePartido(int id){
        for (Partido partido : partidos) {
            if (partido.getId() == id) {
                return partido;
            }
        }
        return null;
    }

    /**
     * Simula todos los partidos de la jornada y actualiza las estadisticas de cada partido.
     */
    public void simular() {
        for (Partido partido : partidos) {
            partido.simular();
            partido.actualizarEstadistica();
        }
    }

    
    /** 
     * Juega el partido con un determinado identificador de la jornada y actualiza sus estadísticas.
     * @param numPartido
     * @param golLocal
     * @param golVis
     */
    public void jugar(int numPartido, int[] golLocal, int[] golVis){
        for (Partido partido : partidos) {
            if (partido.getId() == numPartido) {
                partido.jugar(golLocal, golVis);
                partido.actualizarEstadistica();
            }
        }
    }
    
}

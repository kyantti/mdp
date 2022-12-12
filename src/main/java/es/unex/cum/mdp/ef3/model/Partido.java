package main.java.es.unex.cum.mdp.ef3.model;

import java.util.LinkedList;
import java.util.List;

public abstract class Partido {
    private int id;
    private int puntosLocal;
    private int puntosVisitante;
    private static int numEnfrentamiento = 3;
    private EquipoLiga local;
    private EquipoLiga visitante;
    private Juez j;
    private List <EnfrentamientoFutbolin> enfrentamientosFutbolin;

    protected Partido(int id){
        this.id = id;
        puntosLocal = 0;
        puntosVisitante = 0;
        local = new EquipoLiga();
        visitante = new EquipoLiga();
        j = new Juez();
        enfrentamientosFutbolin = new LinkedList<>();
    }

    protected Partido(int id, EquipoLiga local, EquipoLiga visitante){
        this.id = id;
        this.local = local;
        this.visitante = visitante;
    }

    
    /** 
     * Obtiene el identificador del partido.
     * @return int
     */
    public int getId() {
        return id;
    }

    
    /** 
     * Establece el identiciador del partido.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /** 
     * Obtiene el numero de enfrentamientos ganados por el equipo local.
     * @return int
     */
    public int getPuntosLocal() {
        return puntosLocal;
    }

    
    /** 
     * Establece el numero de enfrentamientos ganados por el equipo local.
     * @param puntosLocal
     */
    public void setPuntosLocal(int puntosLocal) {
        this.puntosLocal = puntosLocal;
    }

    
    /** 
     * Obtiene el numero de enfrentamientos ganados por el equipo visitante.
     * @return int
     */
    public int getPuntosVisitante() {
        return puntosVisitante;
    }

    
    /** 
     * Establece el numero de enfrentamientos ganados por el equipo visitante.
     * @param puntosVisitante
     */
    public void setPuntosVisitante(int puntosVisitante) {
        this.puntosVisitante = puntosVisitante;
    }

    
    /** 
     * Obtiene el numero de enfrentamientos que se juegan en el partido.
     * @return int
     */
    public static int getNumEnfrentamiento() {
        return numEnfrentamiento;
    }

    
    /** 
     * Establece el numero de enfrentamientos que se juegan en el partido.
     * @param numEnfrentamiento
     */
    public static void setNumEnfrentamiento(int numEnfrentamiento) {
        Partido.numEnfrentamiento = numEnfrentamiento;
    }

    
    /** 
     * Obtiene el equipo local.
     * @return EquipoLiga
     */
    public EquipoLiga getLocal() {
        return local;
    }

    
    /** 
     * Establece el equipo local.
     * @param local
     */
    public void setLocal(EquipoLiga local) {
        this.local = local;
    }

    
    /** 
     * Obtiene el equipo visitante.
     * @return EquipoLiga
     */
    public EquipoLiga getVisitante() {
        return visitante;
    }

    
    /** 
     * Establece el equipo visitante.
     * @param visitante
     */
    public void setVisitante(EquipoLiga visitante) {
        this.visitante = visitante;
    }

    
    /** 
     * Obtiene el juez del partido.
     * @return Juez
     */
    public Juez getJ() {
        return j;
    }

    
    /** 
     * Establece el juez del partido.
     * @param j
     */
    public void setJ(Juez j) {
        this.j = j;
    }

    
    /**
     * Obtiene la lista de enfrentamientos del partido.
     * @return List<EnfrentamientoFutbolin>
     */
    public List<EnfrentamientoFutbolin> getEs() {
        return enfrentamientosFutbolin;
    }

    
    /** 
     * Establece la lista de enfrentamientos del partido.
     * @param enfrentamientosFutbolin
     */
    public void setEnfrentamientosFutbolin(List<EnfrentamientoFutbolin> enfrentamientosFutbolin) {
        this.enfrentamientosFutbolin = enfrentamientosFutbolin;
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + puntosLocal;
        result = prime * result + puntosVisitante;
        result = prime * result + ((local == null) ? 0 : local.hashCode());
        result = prime * result + ((visitante == null) ? 0 : visitante.hashCode());
        result = prime * result + ((j == null) ? 0 : j.hashCode());
        result = prime * result + ((enfrentamientosFutbolin == null) ? 0 : enfrentamientosFutbolin.hashCode());
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
        Partido other = (Partido) obj;
        if (id != other.id)
            return false;
        if (puntosLocal != other.puntosLocal)
            return false;
        if (puntosVisitante != other.puntosVisitante)
            return false;
        if (local == null) {
            if (other.local != null)
                return false;
        } else if (!local.equals(other.local))
            return false;
        if (visitante == null) {
            if (other.visitante != null)
                return false;
        } else if (!visitante.equals(other.visitante))
            return false;
        if (j == null) {
            if (other.j != null)
                return false;
        } else if (!j.equals(other.j))
            return false;
        if (enfrentamientosFutbolin == null) {
            if (other.enfrentamientosFutbolin != null)
                return false;
        } else if (!enfrentamientosFutbolin.equals(other.enfrentamientosFutbolin))
            return false;
        return true;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Partido [id=" + id + ", puntosLocal=" + puntosLocal + ", puntosVisitante=" + puntosVisitante
                + ", local=" + local + ", visitante=" + visitante + ", j=" + j + ", enfrentamientosFutbolin="
                + enfrentamientosFutbolin + "]";
    }

    /**
     * Realiza la simulación de todos los enfrentamientos
     * del partido, actualizando los puntos de cada equipo. Además de actualizar las
     * estadísticas de los equipos con respecto al enfrentamiento: goles, enfrentamientos.
     */
    public abstract void simular();

    /**
     * Juega todos los enfrentamientos del partido.
     * Tras cada enfrentamiento actualiza los puntos de cada equipo así como las estadísticas
     * de los equipos con respecto al enfrentamiento: goles, enfrentamientos.
     * @param gLocal
     * @param gVis
     */
    public abstract void jugar(int[] gLocal, int[] gVis);

    /**
     * Actualiza las estadísticas de los equipos referidas: partidos y puntos. Este método es llamada desde Jornada
     */
    public abstract void actualizarEstadistica();

    /**
     * Crea los distintos enfrentamientos del partido. En base al atributo del número de enfrentamientos que debe realizar se
     * crearán tanto enfrentamientos como se indique.
     * @param juglocales
     * @param jugVisitantes
     */
    public abstract void crearEnfrentamientos(List <Jugador> juglocales, List<Jugador> jugVisitantes);

}

    

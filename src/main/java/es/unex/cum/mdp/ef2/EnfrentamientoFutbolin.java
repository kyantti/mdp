package main.java.es.unex.cum.mdp.ef2;

public class EnfrentamientoFutbolin implements Enfrentamiento {

    private int local;
    private int visitante;
    private int id;
    private Jugador jugadorLocal1;
    private Jugador jugadorLocal2;
    private Jugador jugadorVisitante1;
    private Jugador jugadorVisitante2;


    public EnfrentamientoFutbolin(Jugador jugadorLocal1, Jugador jugadorLocal2, Jugador jugadorVisitante1, Jugador jugadorVisitante2) {
        this.jugadorLocal1 = jugadorLocal1;
        this.jugadorLocal2 = jugadorLocal2;
        this.jugadorVisitante1 = jugadorVisitante1;
        this.jugadorVisitante2 = jugadorVisitante2;
    }

    
    /**
     * @return Puntuación que ha obtenido el equipo local en el enfrentamiento (Número de goles): int
     */
    public int getLocal() {
        return local;
    }

    
    /** 
     * Establece la puntuación que ha obtenido el equipo local en el enfrentamiento (Número de goles)
     * @param Puntuación 
     */
    public void setLocal(int local) {
        this.local = local;
    }

    
    /** 
     * @return Puntuación que ha obtenido el equipo visitante en el enfrentamiento (Número de goles): int
     */
    public int getVisitante() {
        return visitante;
    }

    
    /** 
     *  Establece la puntuación que ha obtenido el equipo visitante en el enfrentamiento (Número de goles)
     * @param visitante
     */
    public void setVisitante(int visitante) {
        this.visitante = visitante;
    }

    
    /** 
     * @return Devuelve el identificador del enfrentamiento: {@code int}
     */
    public int getId() {
        return id;
    }

    
    /** 
     * Establece el identificador del enfrentamiento
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /** 
     * Devuelve el jugador local 1.
     * @return Jugador
     */
    public Jugador getJugadorLocal1() {
        return jugadorLocal1;
    }

    
    /** 
     * Establece el jugador local 1.
     * @param jugadorLocal1
     */
    public void setJugadorLocal1(Jugador jugadorLocal1) {
        this.jugadorLocal1 = jugadorLocal1;
    }

    
    /** 
     * Devuelve el jugador local 2.
     * @return Jugador
     */
    public Jugador getJugadorLocal2() {
        return jugadorLocal2;
    }

    
    /** 
     * Establece el jugador local 2.
     * @param jugadorLocal2
     */
    public void setJugadorLocal2(Jugador jugadorLocal2) {
        this.jugadorLocal2 = jugadorLocal2;
    }

    
    /** 
     * Devuelve el jugador visitante 1.
     * @return Jugador
     */
    public Jugador getJugadorVisitante1() {
        return jugadorVisitante1;
    }

    
    /** 
     * Establece el jugador visitante 1.
     * @param jugadorVisitante1
     */
    public void setJugadorVisitante1(Jugador jugadorVisitante1) {
        this.jugadorVisitante1 = jugadorVisitante1;
    }

    
    /** 
     * Devuelve el jugador visitante 2.
     * @return Jugador
     */
    public Jugador getJugadorVisitante2() {
        return jugadorVisitante2;
    }

    
    /** 
     * Establece el jugador visitante 2.
     * @param jugadorVisitante2
     */
    public void setJugadorVisitante2(Jugador jugadorVisitante2) {
        this.jugadorVisitante2 = jugadorVisitante2;
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + local;
        result = prime * result + visitante;
        result = prime * result + id;
        result = prime * result + ((jugadorLocal1 == null) ? 0 : jugadorLocal1.hashCode());
        result = prime * result + ((jugadorLocal2 == null) ? 0 : jugadorLocal2.hashCode());
        result = prime * result + ((jugadorVisitante1 == null) ? 0 : jugadorVisitante1.hashCode());
        result = prime * result + ((jugadorVisitante2 == null) ? 0 : jugadorVisitante2.hashCode());
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
        EnfrentamientoFutbolin other = (EnfrentamientoFutbolin) obj;
        if (local != other.local)
            return false;
        if (visitante != other.visitante)
            return false;
        if (id != other.id)
            return false;
        if (jugadorLocal1 == null) {
            if (other.jugadorLocal1 != null)
                return false;
        } else if (!jugadorLocal1.equals(other.jugadorLocal1))
            return false;
        if (jugadorLocal2 == null) {
            if (other.jugadorLocal2 != null)
                return false;
        } else if (!jugadorLocal2.equals(other.jugadorLocal2))
            return false;
        if (jugadorVisitante1 == null) {
            if (other.jugadorVisitante1 != null)
                return false;
        } else if (!jugadorVisitante1.equals(other.jugadorVisitante1))
            return false;
        if (jugadorVisitante2 == null) {
            if (other.jugadorVisitante2 != null)
                return false;
        } else if (!jugadorVisitante2.equals(other.jugadorVisitante2))
            return false;
        return true;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "EnfrentamientoFutbolin [local=" + local + ", visitante=" + visitante + ", id=" + id + ", jugadorLocal1="
                + jugadorLocal1 + ", jugadorLocal2=" + jugadorLocal2 + ", jugadorVisitante1=" + jugadorVisitante1
                + ", jugadorVisitante2=" + jugadorVisitante2 + "]";
    }

    
    /** 
     * Simula un enfrentamiento a partir de una operación que usa el coeficiente de cada equipo. Se establecen el numero de goles del equipo local y visitante
     * @param coefEqLocal
     * @param coefEqVisitante
     * @return {@code int } < 0: El equipo local pierde, = 0: Empate, > 0: El equipo local gana.
     */
    @Override
    public int simular(int coefEqLocal, int coefEqVisitante) {
        local = coefEqLocal + jugadorLocal1.getCoef() + jugadorLocal2.getCoef();
        visitante = coefEqVisitante + jugadorVisitante1.getCoef() + jugadorVisitante2.getCoef();
        
        return local - visitante;
    }

    
    /** 
     * Juega un enfrentamiento estableciendo el numero de goles de cada equipo.
     * @param gLocal
     * @param gVis
     * @return  {@code int } < 0: El equipo local pierde, = 0: Empate, > 0: El equipo local gana.
     */
    @Override
    public int jugar(int gLocal, int gVis) {
        local = gLocal;
        visitante = gVis;

        return local - visitante;
    }

}

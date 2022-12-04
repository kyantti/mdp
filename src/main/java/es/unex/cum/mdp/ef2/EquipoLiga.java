package main.java.es.unex.cum.mdp.ef2;
import java.util.ArrayList;
import java.util.List;

public class EquipoLiga {
    protected int coef;
    protected Equipo equipo;
    protected Estadistica estadistica;
    protected List <Jugador> jugadores;

    public EquipoLiga(int coef, Equipo equipo){
        this.coef = coef;
        this.equipo = equipo;
        estadistica = new EstadisticaFutbolin();
        jugadores = new ArrayList<>();
    }

    public EquipoLiga(int coef, Equipo equipo, Estadistica estadistica) {
        this.coef = coef;
        this.equipo = equipo;
        this.estadistica = estadistica;
        jugadores = new ArrayList<>();
    }

    public EquipoLiga() {
    }

    
    /** 
     * Devuelve el coeficiente del equipo.
     * @return int
     */
    public int getCoef() {
        return coef;
    }
    
    /** 
     * Establece el coeficiente del equipo.
     * @param coef
     */
    public void setCoef(int coef) {
        this.coef = coef;
    }
    
    /** 
     * Devuelve el equipo.
     * @return Equipo
     */
    public Equipo getE() {
        return equipo;
    }
    
    /** 
     * Establece el equipo.
     * @param equipo
     */
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    
    /** 
     * Devuelve la estadistica del equipo.
     * @return Estadistica
     */
    public Estadistica getEst() {
        return estadistica;
    }
    
    /** 
     * Establece la estadistica del equipo.
     * @param estadistica
     */
    public void setEstadistica(Estadistica estadistica) {
        this.estadistica = estadistica;
    }
    
    /** 
     * Devuelve la lista de jugadores del equipo.
     * @return List<Jugador>
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }
    
    /** 
     * Establece la lista de jugadores del equipo.
     * @param jugadores
     */
    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + coef;
        result = prime * result + ((equipo == null) ? 0 : equipo.hashCode());
        result = prime * result + ((estadistica == null) ? 0 : estadistica.hashCode());
        result = prime * result + ((jugadores == null) ? 0 : jugadores.hashCode());
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
        EquipoLiga other = (EquipoLiga) obj;
        if (coef != other.coef)
            return false;
        if (equipo == null) {
            if (other.equipo != null)
                return false;
        } else if (!equipo.equals(other.equipo))
            return false;
        if (estadistica == null) {
            if (other.estadistica != null)
                return false;
        } else if (!estadistica.equals(other.estadistica))
            return false;
        if (jugadores == null) {
            if (other.jugadores != null)
                return false;
        } else if (!jugadores.equals(other.jugadores))
            return false;
        return true;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "EquipoLiga [coef=" + coef + ", equipo=" + equipo + ", estadistica=" + estadistica + ", jugadores="
                + jugadores + "]";
    }

    
    /** 
     * Añade un jugador a la lista de jugadores del equipo
     * @param j
     * @return {@code true} si el jugador se ha añadido, {@code false} de lo contrario.
     */
    public boolean addJugadores(Jugador j) {
        if (j != null) {
            jugadores.add(j);
            return true;
        }
        return false;
    }

    
    /** 
     * Obtiene un jugador con un determinado identificador de la lista de jugadores.
     * @param id
     * @return Jugador
     */
    public Jugador getJugador(int id){
        for (Jugador jugador : jugadores) {
            if (jugador.getId() == id) {
                return jugador;
            }
        }
        return null;
    }

}

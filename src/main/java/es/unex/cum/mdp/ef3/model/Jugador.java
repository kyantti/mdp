package main.java.es.unex.cum.mdp.ef3.model;

public class Jugador extends Persona implements Comparable <Jugador> {

    private String nick;
    private int coef;
    private boolean enEquipoLiga;

    public Jugador(String nombre, int edad, int id, String nick, int coef, String aspecto) {
        super(nombre, edad, id, aspecto);
        this.nick = nick;
        this.coef = coef;
        enEquipoLiga = false;
    }

    public Jugador(String nick){
        this.nick = nick;
        coef = 0;
    }

    
    /** 
     * Obtiene el nick del jugador.
     * @return String
     */
    public String getNick() {
        return nick;
    }

    
    /** 
     * Establece el nick del jugador.
     * @param nick
     */
    public void setNick(String nick) {
        this.nick = nick;
    }
    
    /** 
     * Obtiene el coeficiente del jugador.
     * @return int
     */
    public int getCoef() {
        return coef;
    }
    
    /** 
     * Establece el coeficiente del jugador.
     * @param coef
     */
    public void setCoef(int coef) {
        this.coef = coef;
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((nick == null) ? 0 : nick.hashCode());
        result = prime * result + coef;
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
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Jugador other = (Jugador) obj;
        if (nick == null) {
            if (other.nick != null)
                return false;
        } else if (!nick.equals(other.nick))
            return false;
        if (coef != other.coef)
            return false;
        return true;
    }

    
    @Override
    public String toString() {
        return "Jugador [nick=" + nick + ", coef=" + coef + ", enEquipoLiga=" + enEquipoLiga + "]";
    }

    
    /**
     * Compara el jugador con otro jugador en base a sus coeficientes.
     * @param rival
     * @return {@code int} 1 si el coeficiente del jugador es mayor que el del rival, -1 si el coeficiente del jugador es menor que el del rival y 0 si el coeficiente del jugador es igual que el del rival.
     */
    @Override
    public int compareTo(Jugador rival) {
        int coefRival = rival.getCoef();
        if (coef > coefRival) {
            return 1;
        }
        else if(coefRival > coef){
            return -1;
        }
        return 0;
    }

    public boolean isEnEquipoLiga() {
        return enEquipoLiga;
    }

    public void setEnEquipoLiga(boolean enEquipoLiga) {
        this.enEquipoLiga = enEquipoLiga;
    }
    
}

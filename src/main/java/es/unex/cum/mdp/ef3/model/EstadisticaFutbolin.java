package main.java.es.unex.cum.mdp.ef3.model;

public class EstadisticaFutbolin extends Estadistica {
    
    protected int golesFavor;
    protected int golesContra;

    public EstadisticaFutbolin(){

    }

    public EstadisticaFutbolin(int golesFavor, int golesContra) {
        this.golesFavor = golesFavor;
        this.golesContra = golesContra;
    }

    
    /** 
     * Devuelve el numero total de goles marcados.
     * @return int
     */
    public int getGolesFavor() {
        return golesFavor;
    }

    
    /** 
     * Establece el total de goles marcados.
     * @param golesFavor
     */
    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    
    /** 
     * Devuelve el total de goles recibidos.
     * @return int
     */
    public int getGolesContra() {
        return golesContra;
    }

    
    /** 
     * Establece el total de goles recibidos.
     * @param golesContra
     */
    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

    
    /** 
     * @return int
     */
    @Override
    public synchronized int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + golesFavor;
        result = prime * result + golesContra;
        return result;
    }

    
    /** 
     * @param obj
     * @return boolean
     */
    @Override
    public synchronized boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        EstadisticaFutbolin other = (EstadisticaFutbolin) obj;
        if (golesFavor != other.golesFavor)
            return false;
        if (golesContra != other.golesContra)
            return false;
        return true;
    }

    
    /** 
     * @return String
     */
    @Override
    public synchronized String toString() {
        return "EstadisticaFutbolin [golesFavor=" + golesFavor + ", golesContra=" + golesContra + "]";
    }

}

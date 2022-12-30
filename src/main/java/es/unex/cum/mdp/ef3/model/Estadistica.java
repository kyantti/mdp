package main.java.es.unex.cum.mdp.ef3.model;

import java.io.Serializable;

public abstract class Estadistica implements Serializable{
    protected int puntos;
    protected int partJugados;
    protected int partGanados;
    protected int partEmpatados;
    protected int partPerdidos;
    protected int enfrenGanados;
    protected int enfrenEmpatados;
    protected int enfrenPerdidos;
    
    
    /** 
     * Devuelve los puntos de un equipo:
     * +3 por partido ganado
     * -1 por partido perdido.
     * +-0 por partido empatado.
     * @return int
     */
    public int getPuntos() {
        return puntos;
    }
    
    /** 
     * Establece los puntos de un equipo.
     * @param puntos
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    /** 
     * Devuelve el nuemro de partidos jugados.
     * @return int
     */
    public int getPartJugados() {
        return partJugados;
    }
    
    /** 
     * Establece el numero de partidos jugados.
     * @param partJugados
     */
    public void setPartJugados(int partJugados) {
        this.partJugados = partJugados;
    }
    
    /** 
     * Devuelve en numero de partidos ganados.
     * @return int
     */
    public int getPartGanados() {
        return partGanados;
    }
    

    /** 
     * Establece el numero de partidos ganados.
     * @param partGanados
     */
    public void setPartGanados(int partGanados) {
        this.partGanados = partGanados;
    }
    
    /** 
     * Devuelve el numero de partidos empatados.
     * @return int
     */
    public int getPartEmpatados() {
        return partEmpatados;
    }
    
    /** 
     * Establece el numero de partidos empatados.
     * @param partEmpatados
     */
    public void setPartEmpatados(int partEmpatados) {
        this.partEmpatados = partEmpatados;
    }
    
    /** 
     * Devuelve el numero de partidos perdidos.
     * @return int
     */
    public int getPartPerdidos() {
        return partPerdidos;
    }
    
    
    /** 
     * Establece el numero de partidos perdidos.
     * @param partPerdidos
     */
    public void setPartPerdidos(int partPerdidos) {
        this.partPerdidos = partPerdidos;
    }
    
    /** 
     * Devuelve el nuemro de enfrentamientos ganados.
     * @return int
     */
    public int getEnfrenGanados() {
        return enfrenGanados;
    }
    
    /** 
     * Establece el numero de enfrentamientos ganados.
     * @param enfrenGanados
     */
    public void setEnfrenGanados(int enfrenGanados) {
        this.enfrenGanados = enfrenGanados;
    }
    
    /** 
     * Devuelve el numero de enfrentamientos empatados.
     * @return int
     */
    public int getEnfrenEmpatados() {
        return enfrenEmpatados;
    }
    
    /** 
     * Establece el numero de enfrentamientos empatados.
     * @param enfrenEmpatados
     */
    public void setEnfrenEmpatados(int enfrenEmpatados) {
        this.enfrenEmpatados = enfrenEmpatados;
    }
    
    /** 
     * Devuelve el numero de enfrentamientos perdidos.
     * @return int
     */
    public int getEnfrenPerdidos() {
        return enfrenPerdidos;
    }
    
    /** 
     * Establece el numero de enfrentamientos perdidos.
     * @param enfrenPerdidos
     */
    public void setEnfrenPerdidos(int enfrenPerdidos) {
        this.enfrenPerdidos = enfrenPerdidos;
    }
    
    /** 
     * @return int
     */
    @Override
    public synchronized int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + puntos;
        result = prime * result + partJugados;
        result = prime * result + partGanados;
        result = prime * result + partEmpatados;
        result = prime * result + partPerdidos;
        result = prime * result + enfrenGanados;
        result = prime * result + enfrenEmpatados;
        result = prime * result + enfrenPerdidos;
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
        Estadistica other = (Estadistica) obj;
        if (puntos != other.puntos)
            return false;
        if (partJugados != other.partJugados)
            return false;
        if (partGanados != other.partGanados)
            return false;
        if (partEmpatados != other.partEmpatados)
            return false;
        if (partPerdidos != other.partPerdidos)
            return false;
        if (enfrenGanados != other.enfrenGanados)
            return false;
        if (enfrenEmpatados != other.enfrenEmpatados)
            return false;
        if (enfrenPerdidos != other.enfrenPerdidos)
            return false;
        return true;
    }
      
}

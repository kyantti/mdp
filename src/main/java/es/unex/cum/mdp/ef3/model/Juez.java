package main.java.es.unex.cum.mdp.ef3.model;

public class Juez extends Persona {

    private int antiguedad;

    public Juez(){
        super();
        antiguedad = 0;
    }

    public Juez(String nombre, int edad, int id, int antiguedad) {
        super(nombre, edad, id);
        this.antiguedad = antiguedad;
    }

    
    /** 
     * Obtiene la antiguedad del juez.
     * @return int
     */
    public int getAntiguedad() {
        return antiguedad;
    }

    
    /** 
     * Establece la antiguedad del juez.
     * @param antiguedad
     */
    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + antiguedad;
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
        Juez other = (Juez) obj;
        if (antiguedad != other.antiguedad)
            return false;
        return true;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Juez [antiguedad=" + antiguedad + "]";
    }
        
}

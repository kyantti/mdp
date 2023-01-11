package main.java.es.unex.cum.mdp.ef3.model;

public class Directivo extends Persona {
    private String puesto;

    public Directivo(String puesto){
        super();
        this.puesto = puesto;
    }

    public Directivo(String nombre, int edad, int id, String puesto, String aspecto) {
        super(nombre, edad, id, aspecto);
        this.puesto = puesto;
    }

    
    /** 
     * Devuelve el puesto del directivo
     * @return String
     */
    public String getPuesto() {
        return puesto;
    }

    
    /** 
     * Establece el puesto del directivo.
     * @param puesto
     */
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((puesto == null) ? 0 : puesto.hashCode());
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
        Directivo other = (Directivo) obj;
        if (puesto == null) {
            if (other.puesto != null)
                return false;
        } else if (!puesto.equals(other.puesto))
            return false;
        return true;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Directivo [puesto=" + puesto + "]";
    }

    
}

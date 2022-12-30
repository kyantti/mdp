package main.java.es.unex.cum.mdp.sesion7;

public class Pieza implements Comparable <Pieza> {

    private String id;
    private String nombre;
    private int stock;

    public Pieza(){
        id = null;
        nombre = null;
        stock = 0;
        
    }

    public Pieza(Pieza p){
        id = p.id;
        nombre = p.nombre;
        stock = p.stock;
    }

    public Pieza(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Pieza(String id, String nombre, int cont) {
        this.id = id;
        this.nombre = nombre;
        this.stock = cont;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + stock;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pieza other = (Pieza) obj;
        if (stock != other.stock)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pieza [id=" + id + ", nombre=" + nombre + ", stock=" + stock + "]";
    }

    @Override
    public int compareTo(Pieza piezaAux) {

        int comparation = 0;

        if (id.compareTo(piezaAux.getId()) < 0) {
            comparation = -1;
        }
        else if (id.compareTo(piezaAux.getId()) == 0) {
            comparation = 0;
        }
        else if (id.compareTo(piezaAux.getId()) > 0) {
            comparation = 1;
        }
        
        return comparation;

    }

    

    
    
}
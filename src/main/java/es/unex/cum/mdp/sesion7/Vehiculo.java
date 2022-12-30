package main.java.es.unex.cum.mdp.sesion7;

import java.util.Arrays;

public class Vehiculo implements Comparable <Vehiculo>{

    private String marca;
    private String modelo;
    private Persona propietario;
    private int cont;
    private Pieza[] piezas;
    private Integer bastidor;

    public Vehiculo(){
        marca = null;
        modelo = null;
        propietario = null;
        bastidor = null;
        piezas = new Pieza[3];
        cont = 0;
    }


    public Vehiculo(Vehiculo v){
        marca = v.marca;
        modelo = v.modelo;
        propietario = v.propietario;
        cont = v.cont;
        piezas = v.piezas;
        bastidor = v.bastidor;
    }

    public Vehiculo(String marca, String modelo, Persona p) {
        this.marca = marca;
        this.modelo = modelo;
        this.propietario = p;
        piezas = new Pieza[3];
    }

    public Vehiculo(String marca, String modelo, Persona p, Integer bastidor) {
        this.marca = marca;
        this.modelo = modelo;
        this.propietario = p;
        this.bastidor = bastidor;
        piezas = new Pieza[3];
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Persona getPropietario() {
        return propietario;
    }

    public void setPropietario(Persona propietario) {
        this.propietario = propietario;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public Integer getBastidor() {
        return bastidor;
    }

    public void setBastidor(int bastidor) {
        this.bastidor = bastidor;
    }

    public Pieza[] getPiezas() {
        return piezas;
    }
    
    public Pieza getPiezaV(int pos){
        if(pos<cont && pos>=0){
            return piezas[pos];
        }
        else{
            return null;
        }
        
    }

    public void setPiezas(Pieza[] piezas) {
        this.piezas = piezas;
    }

    public boolean addPiezaV(Pieza p)
    {
        boolean add = false;
        if(cont==piezas.length || p==null){
            return false;
        }
        else {
            for (int i = 0; i < piezas.length; i++) {
                if (piezas[i]!=null && piezas[i].equals(p)) {
                    return false;
                }
            }
            piezas[cont]=p;
            cont++;
            add = !add;
        }
        return add;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vehiculo other = (Vehiculo) obj;
        if (bastidor != other.bastidor)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Vehiculo [bastidor=" + bastidor + ", cont=" + cont + ", marca=" + marca + ", modelo=" + modelo
                + ", piezas=" + Arrays.toString(piezas) + ", propietario=" + propietario + "]";
    }

    @Override
    public int compareTo(Vehiculo vehiculoAux) {
        int comparation = 0;

        if (cont - vehiculoAux.getCont() < 0) {
            comparation = -1;
        }
        else if(cont - vehiculoAux.getCont() == 0){
            comparation = 0;
        }
        else if (cont - vehiculoAux.getCont() > 0) {
            comparation = 1;
        }
        
        return comparation;
    }
    
}
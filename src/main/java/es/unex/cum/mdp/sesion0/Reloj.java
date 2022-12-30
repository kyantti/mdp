package main.java.es.unex.cum.mdp.sesion0;


/*
Cada alumno deberá implementar una clase Reloj compuesta por los siguientes atributos: int horas; int minutos; int segundos.
Además debe tener los siguientes métodos (CUIDADO: deben llamarse tal y como aparecen a continuación):

• Reloj()
• Reloj(int hora, int minutos, int segundos)
• int getHoras()
• void setHoras(int hora)
• int getMinutos()
• void setMinutos(int minutos)
• int getSegundos()
• void setSegundos(int segundos)
• boolean equals(Reloj obj)
• String toString() deberá devolver la información en el siguiente formato:
◦
"Reloj [horas=" + horas + ", minutos=" + minutos + ", segundos=" + segundos + "]";
• void incrementarSegundos(): Incrementa en uno los segundos
• void incrementarMinutos() : Incrementa en uno los minutos
• void incrementarHoras(): : Incrementa en uno las horas
• void decrementarSegundos(): Decrementa en uno los segundos
• void decrementarMinutos(): Decrementa en uno los minutos
• void decrementarHoras(): Decrementa en uno las horas
• long getTiempo(): Devuelve en número entero las horas, minutos y segundos.
• void setTiempo(long s): Asigna las horas, minutos y segundos en base a un número long
• void anadirTiempo(Reloj r): Añade al reloj actual, la información de otro reloj
• void restarTiempo(Reloj r): Resta al reloj actual, la información de otro reloj
*/
public class Reloj {

    private int horas;
    private int minutos;
    private int segundos;

    public Reloj(){

    }

    public Reloj(int horas, int minutos, int segundos){
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    public int getHoras(){
        return horas;
    }

    public void setHoras(int hora){
        this.horas = hora;
    }

    public int getMinutos(){
        return minutos;
    }

    public void setMinutos(int minutos){
        this.minutos = minutos;
    }

    public int getSegundos(){
        return segundos;
    }

    public void setSegundos(int segundos){
        this.segundos = segundos;
    }

    public boolean equals(Reloj obj){
        if (this == obj) {
            return true;
        }
        if(obj == null || obj.getClass()!=this.getClass()){
            return false;
        }
        Reloj reloj = (Reloj) obj;
        return (reloj.horas == this.horas && reloj.minutos == this.minutos && reloj.segundos == this.segundos);
    }

    public String toString(){
        return "Reloj [horas=" + horas + ", minutos=" + minutos + ", segundos=" + segundos + "]";
    }

    public void incrementarSegundos(){
        segundos++;
        if(segundos==60){
            incrementarMinutos();
            segundos=0;
        }

    }

    public void incrementarMinutos(){
        minutos++;
        if(minutos==60){
            incrementarHoras();
            minutos=0;
        }
    }

    public void incrementarHoras(){
        horas++;
        if(horas==24){
            horas=0;
        }
    }

    public void decrementarSegundos(){
        /*if (segundos<=60) {
            segundos--;
        }
        else if(segundos==0){
            decrementarMinutos();
            segundos=59;
        }*/
        segundos--;
        if(segundos < 0){
            segundos = 59;
            decrementarMinutos();
        }
    }

    public void decrementarMinutos(){
        minutos--;
        if(minutos < 0){
            minutos = 59;
            decrementarHoras();
        }
    }

    public void decrementarHoras(){
        horas--;
        if(horas < 0){
            horas = 23;
        }
    }

    public long getTiempo(){
        return horas*3600 + minutos*60 + segundos;
    }

    public void setTiempo(long s){
        segundos = (int) s % 60;
        horas = (int) s / 60;
        minutos = horas % 60;
        horas = horas / 60;
    }

    public void anadirTiempo(Reloj r){
        long s = r.getTiempo() + this.getTiempo();
        segundos = (int) s % 60;
        horas = (int) s / 60;
        minutos = horas % 60;
        horas = horas / 60;
    }

    public void restarTiempo(Reloj r){
        long s = this.getTiempo() - r.getTiempo();
        segundos = (int) s % 60;
        horas = (int) s / 60;
        minutos = horas % 60;
        horas = horas / 60;
    }
}

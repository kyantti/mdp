package main.java.es.unex.cum.mdp.ef3.model;

import java.util.Collections;
import java.util.List;

public class PartidoFutbolin extends Partido {

    public PartidoFutbolin(int id){
        super(id);
    }

    public PartidoFutbolin(int id, EquipoLiga local, EquipoLiga visitante){
        super(id, local, visitante);
    }

    @Override
    public void simular() {
        //est del equipo local
        Estadistica estLocal = super.getLocal().getEst();
        //est del equipo vistinante
        Estadistica estVis = super.getVisitante().getEst();
        //res del enfrentamiento
        int resEnfrentamiento = 0;
        
        /*int golesFavorLocal = ((EstadisticaFutbolin) estLocal).getGolesFavor();
        int golesContraLocal = ((EstadisticaFutbolin) estLocal).getGolesContra();
        int golesFavorVis = ((EstadisticaFutbolin) estVis).getGolesFavor();
        int golesContraVis = ((EstadisticaFutbolin) estVis).getGolesContra();*/

        for (EnfrentamientoFutbolin enfrentamiento : super.getEs()) {
            //se simula el enfrentamiento
            resEnfrentamiento = enfrentamiento.simular(super.getLocal().getCoef(), super.getVisitante().getCoef());

            // actualizar estadistica goles eq local

            //golesFavorLocal =  golesFavorLocal + enfrentamiento.getLocal();
            ((EstadisticaFutbolin) estLocal).setGolesFavor(((EstadisticaFutbolin) estLocal).getGolesFavor() + enfrentamiento.getLocal());

            //golesContraLocal = golesContraLocal + enfrentamiento.getVisitante();
            ((EstadisticaFutbolin) estLocal).setGolesContra(((EstadisticaFutbolin) estLocal).getGolesContra() + enfrentamiento.getVisitante());

            // actualziar estadistica goles eq visitante
            //golesFavorVis = golesFavorVis + enfrentamiento.getVisitante();
            ((EstadisticaFutbolin) estVis).setGolesFavor(((EstadisticaFutbolin) estVis).getGolesFavor() + enfrentamiento.getVisitante());

            //golesContraVis = golesContraVis + enfrentamiento.getLocal();
            ((EstadisticaFutbolin) estVis).setGolesContra(((EstadisticaFutbolin) estVis).getGolesContra() + enfrentamiento.getLocal());

            //se actualizan los puntos de cada equipo y los enfrentamientos el numero de enfren ganados, perdidos o empatados
            if (resEnfrentamiento > 0) {
                super.setPuntosLocal(super.getPuntosLocal() + 1);
                estLocal.setEnfrenGanados(estLocal.getEnfrenGanados() + 1);
                estVis.setEnfrenPerdidos(estVis.getEnfrenPerdidos() + 1);
            }
            else if (resEnfrentamiento < 0) {
                super.setPuntosVisitante(super.getPuntosVisitante() + 1);
                estVis.setEnfrenGanados(estLocal.getEnfrenGanados() + 1);
                estLocal.setEnfrenPerdidos(estVis.getEnfrenPerdidos() + 1);
            }
            else{
                estLocal.setEnfrenEmpatados(estLocal.getEnfrenEmpatados() + 1);
                estVis.setEnfrenEmpatados(estVis.getEnfrenEmpatados() + 1);
            }
        }

    }

    @Override
    public void actualizarEstadistica() {
        //est del equipo local
        Estadistica estLocal = super.getLocal().getEst();
        //est del equipo vistinante
        Estadistica estVis = super.getVisitante().getEst();

        estLocal.setPartJugados(estLocal.getPartJugados() + 1);
        estVis.setPartJugados(estVis.getPartJugados() + 1);

        //actualizo la estadistica de puntos de los equipos y de partidos ganados/perdidos
        //+3 si ha ganado, -1 si ha perdido, +- 0 si ha empatado
        if (super.getPuntosLocal() > super.getPuntosVisitante()) {
            //actualizo partidos ganados/perdidos
            estLocal.setPartGanados(estLocal.getPartGanados() + 1);
            estVis.setPartPerdidos(estVis.getPartPerdidos() + 1);
            //actualizo puntos
            estLocal.setPuntos(estLocal.getPuntos() + 3);
            
        }
        else if(super.getPuntosLocal() < super.getPuntosVisitante()){
            //actualizo partidos ganados/ perdidos
            estLocal.setPartPerdidos(estLocal.getPartPerdidos() + 1);
            estVis.setPartGanados(estVis.getPartGanados() + 1);
            //actualizo puntos
            estVis.setPuntos(estVis.getPuntos() + 3);
        }
        else{
            estLocal.setPartEmpatados(estLocal.getPartEmpatados() + 1);
            estVis.setPartEmpatados(estVis.getPartEmpatados() + 1);

            //+1 punto cada uno si empatan
            estLocal.setPuntos(estLocal.getPuntos() + 1);
            estVis.setPuntos(estVis.getPuntos() + 1);

        }
    }

    @Override
    public void crearEnfrentamientos(List<Jugador> juglocales, List<Jugador> jugVisitantes) {
        if (juglocales != null && jugVisitantes != null ) {
            Collections.shuffle(juglocales);
            Collections.shuffle(jugVisitantes);
            for (int i = 0; i < Partido.getNumEnfrentamiento(); i++) {
                super.getEs().add(new EnfrentamientoFutbolin(juglocales.get(0), juglocales.get(1), jugVisitantes.get(0), jugVisitantes.get(1)));
            }
        }     
    }


    @Override
    public void jugar(int[] gLocal, int[] gVis) {
        if (gLocal.length == Partido.getNumEnfrentamiento() && gVis.length == Partido.getNumEnfrentamiento()) {
            // est del equipo local
            Estadistica estLocal = super.getLocal().getEst();
            // est del equipo vistinante
            Estadistica estVis = super.getVisitante().getEst();
            //res del enfrentamiento
            int resEnfrentamiento = 0;
            int golesFavorLocal = ((EstadisticaFutbolin) estLocal).getGolesFavor();
            int golesContraLocal = ((EstadisticaFutbolin) estLocal).getGolesContra();
            int golesFavorVis = ((EstadisticaFutbolin) estVis).getGolesFavor();
            int golesContraVis = ((EstadisticaFutbolin) estVis).getGolesContra();

            for (int i = 0; i < Partido.getNumEnfrentamiento(); i++) {
                EnfrentamientoFutbolin enfrentamiento = super.getEs().get(i);

                resEnfrentamiento = enfrentamiento.jugar(gLocal[i], gVis[i]);
                
                // actualizar estadistica goles eq local

                golesFavorLocal =  golesFavorLocal + gLocal[i];
                ((EstadisticaFutbolin) estLocal).setGolesFavor(golesFavorLocal);

                golesContraLocal = golesContraLocal + gVis[i];
                ((EstadisticaFutbolin) estLocal).setGolesContra(golesContraLocal);

                // actualziar estadistica goles eq visitante
                golesFavorVis = golesFavorVis + gVis[i];
                ((EstadisticaFutbolin) estVis).setGolesFavor(golesFavorVis);

                golesContraVis = golesContraVis + gLocal[i];
                ((EstadisticaFutbolin) estVis).setGolesContra(golesContraVis);

                if (resEnfrentamiento > 0) {
                    super.setPuntosLocal(super.getPuntosLocal() + 1);
                    estLocal.setEnfrenGanados(estLocal.getEnfrenGanados() + 1);
                    estVis.setEnfrenPerdidos(estVis.getEnfrenPerdidos() + 1);
                }
                else if (resEnfrentamiento < 0) {
                    super.setPuntosVisitante(super.getPuntosVisitante() + 1);
                    estVis.setEnfrenGanados(estVis.getEnfrenGanados() + 1);
                    estLocal.setEnfrenPerdidos(estLocal.getEnfrenPerdidos() + 1);
                }
                else{
                    estLocal.setEnfrenEmpatados(estLocal.getEnfrenEmpatados() + 1);
                    estVis.setEnfrenEmpatados(estVis.getEnfrenEmpatados() + 1);
                }
            }
        }

    }
    
}

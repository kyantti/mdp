package main.java.es.unex.cum.mdp.ef3.model;

import java.util.Comparator;

public class ComparatorPartidos implements Comparator <EquipoLiga>{

    @Override
    public int compare(EquipoLiga eqLocal, EquipoLiga eqVis) {
        if (eqLocal != null & eqVis != null) {
            int partGanadosLocal = eqLocal.getEst().getPartGanados();
            int partGanadosVis = eqVis.getEst().getPartGanados();

            if (partGanadosLocal > partGanadosVis) {
                return -1;
            }
            else if (partGanadosLocal < partGanadosVis) {
                return 1;
            }
            else{
                int partEmpatadosLocal = eqLocal.getEst().getPartEmpatados();
                int partEmpatadosVis = eqVis.getEst().getPartEmpatados();
                if (partEmpatadosLocal > partEmpatadosVis) {
                    return -2;
                }
                else if (partEmpatadosLocal < partEmpatadosVis) {
                    return 2;
                }
                else{
                    int partPerdidosLocal = eqLocal.getEst().getPartPerdidos();
                    int partPerdidosVis = eqVis.getEst().getPartPerdidos();

                    if (partPerdidosLocal < partPerdidosVis) {
                        return -3;
                    }
                    else if (partPerdidosLocal > partPerdidosVis) {
                        return 3;
                    }
                    else{
                        return 0;
                    }
                }
            }
            
        }
        return 0;
    }
    
}

package main.java.es.unex.cum.mdp.ef3.model;

import java.util.Comparator;

public class ComparatorGoles implements Comparator <EquipoLiga>{

    @Override
    public int compare(EquipoLiga eqLocal, EquipoLiga eqVis) {
        if (eqLocal != null && eqVis != null) {
            int golesLocal = ((EstadisticaFutbolin)eqLocal.getEst()).getGolesFavor() - ((EstadisticaFutbolin)eqLocal.getEst()).getGolesContra();
            int golesVis = ((EstadisticaFutbolin)eqVis.getEst()).getGolesFavor() - ((EstadisticaFutbolin)eqVis.getEst()).getGolesContra();

            if (golesLocal > golesVis) {
                return -1;
            }
            else if (golesLocal < golesVis) {
                return 1;
            }
            else{
                return 0;
            }
        }
        return 0;
    }
    
    
}

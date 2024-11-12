package Boundary;

import Entidades.Vino;
import Observer.IObserverNotiActualizacion;

import java.util.List;

public class InterfazNotUsuario implements IObserverNotiActualizacion {
    @Override
    public void actualizar(List<Vino> vinos) {
            for(Vino v: vinos) {
                notificarNovedadImportada(v);
            };
    }


    public void notificarNovedadImportada(Vino vino) {

        String notificacion = vino.getNombre() + vino.getPrecioARS() +
                vino.getImagenEtiqueta() + vino.getNotaDeCataBodega();

        System.out.println("Notificacion: " + notificacion);
    }
}

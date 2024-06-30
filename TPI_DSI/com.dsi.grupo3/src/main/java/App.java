import Boundary.PantallaAdminActualizaciones;
import Control.GestorActualizaciones;
import Entidades.*;
import Soporte.Init;

import java.util.ArrayList;

public class App {



    public static void main(String[] args) {

        PantallaAdminActualizaciones pantallaAdminActualizaciones = new PantallaAdminActualizaciones();
        GestorActualizaciones control = new GestorActualizaciones();

        pantallaAdminActualizaciones.setGestor(control);
        control.setPantalla(pantallaAdminActualizaciones);


        pantallaAdminActualizaciones.opcionImportarActDeVinoDeBodega();


        /*if (hayActualizaciones) {

            pantallaAdminActualizaciones.mostrarBodegas(control.getBodegas());

            control.solicitarSeleccionBodegas(pantallaAdminActualizaciones, bodegasSist); //dentro de este metodo esta el paso 5 que busca las actualizaciones
            pantallaAdminActualizaciones.mostrarListaVinos(control.getVinosImportados(), "Vinos Importados");
            control.determinarVinosActualizar(vinosDelSist);
            pantallaAdminActualizaciones.mostrarListaVinos(control.getVinosActualizables(), "Vinos para actualizar: ");
            pantallaAdminActualizaciones.mostrarListaVinos(control.getVinosCreables(), "Vinos para crear: ");

            control.actualizarDatosDeVino(vinosDelSist, maridajesSist, varietalSist);
            pantallaAdminActualizaciones.mostrarActDeVinosActualizadosYcreados(vinosDelSist, "VINOS ACTUALIZADOS");
            control.buscarSeguidores(enofilosDelSistema);
            System.out.println(control.getUsuarios().stream().toList());
            pantallaAdminActualizaciones.mostrarOpcionFinalizar(control);

        } else {
            System.out.println("No hay actualizaciones");
        }*/

    }


}

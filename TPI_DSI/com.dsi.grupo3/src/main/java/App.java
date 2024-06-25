import Boundary.PantallaAdminActualizaciones;
import Control.GestorActualizaciones;
import Entidades.*;
import Soporte.Init;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {

    private static ArrayList<Bodega> bodegasSist = new ArrayList<>();
    private static ArrayList<Vino> vinosDelSist = new ArrayList<>();
    private static ArrayList<Enofilo> enofilosDelSistema = new ArrayList<>();

    public static void main(String[] args) {

        PantallaAdminActualizaciones pantallaAdminActualizaciones = new PantallaAdminActualizaciones();
        GestorActualizaciones control = new GestorActualizaciones();

        Init.init(bodegasSist, vinosDelSist, enofilosDelSistema);

        //Arranca el CU

        pantallaAdminActualizaciones.opcionImportarActDeVinoDeBodega(bodegasSist);

        Boolean hayActualizaciones = control.opcionImportarActDeVinoDeBodega(bodegasSist, LocalDate.now()); //
        if (hayActualizaciones) {


            pantallaAdminActualizaciones.mostrarBodega(control.getBodegas());

            control.solicitarSeleccionBodegas(pantallaAdminActualizaciones, bodegasSist); //dentro de este metodo esta el paso 5 que busca las actualizaciones

            pantallaAdminActualizaciones.mostrarListaVinos(control.getVinosImportados(), "Vinos Importados");

            control.determinarVinosActualizar(vinosDelSist);
            //no forma parte del CU(es para verificar que funciona el método)
            pantallaAdminActualizaciones.mostrarListaVinos(control.getVinosActualizables(), "Vinos para actualizar: ");
            pantallaAdminActualizaciones.mostrarListaVinos(control.getVinosCreables(), "Vinos para crear: ");

            control.actualizarDatosDeVino(vinosDelSist);
            pantallaAdminActualizaciones.mostrarActDeVinosActualizadosYcreados(vinosDelSist, "VINOS ACTUALIZADOS");
            //envia notificacion a enofilos
            control.buscarSeguidores(enofilosDelSistema, control.getBodegaSeleccionada());
            System.out.println(control.getUsuarios().stream().toList());

            pantallaAdminActualizaciones.mostrarOpcionFinalizar(control);


        } else {
            System.out.println("No hay actualizaciones");
        }

    }


}

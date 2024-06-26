import Boundary.PantallaAdminActualizaciones;
import Control.GestorActualizaciones;
import Entidades.*;
import Soporte.Init;

import java.time.LocalDate;
import java.util.ArrayList;

public class App {

    private static ArrayList<Bodega> bodegasSist = new ArrayList<>();
    private static ArrayList<Vino> vinosDelSist = new ArrayList<>();
    private static ArrayList<Enofilo> enofilosDelSistema = new ArrayList<>();
    private static ArrayList<Maridaje> maridajesSist = new ArrayList<>();
    private static ArrayList<Varietal> varietalSist = new ArrayList<>();

    public static void main(String[] args) {

        PantallaAdminActualizaciones pantallaAdminActualizaciones = new PantallaAdminActualizaciones();
        GestorActualizaciones control = new GestorActualizaciones();

        Init.init(bodegasSist, vinosDelSist, enofilosDelSistema, maridajesSist, varietalSist);

        //Arranca el CU

        System.out.println(maridajesSist.stream().toList());
        System.out.println(varietalSist.stream().toList());

        pantallaAdminActualizaciones.opcionImportarActDeVinoDeBodega(bodegasSist);

        Boolean hayActualizaciones = control.opcionImportarActDeVinoDeBodega(bodegasSist, LocalDate.now()); //
        if (hayActualizaciones) {


            pantallaAdminActualizaciones.mostrarBodegas(control.getBodegas());

            control.solicitarSeleccionBodegas(pantallaAdminActualizaciones, bodegasSist); //dentro de este metodo esta el paso 5 que busca las actualizaciones

            pantallaAdminActualizaciones.mostrarListaVinos(control.getVinosImportados(), "Vinos Importados");

            control.determinarVinosActualizar(vinosDelSist);
            //no forma parte del CU(es para verificar que funciona el método)
            pantallaAdminActualizaciones.mostrarListaVinos(control.getVinosActualizables(), "Vinos para actualizar: ");
            pantallaAdminActualizaciones.mostrarListaVinos(control.getVinosCreables(), "Vinos para crear: ");

            control.actualizarDatosDeVino(vinosDelSist, maridajesSist);
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

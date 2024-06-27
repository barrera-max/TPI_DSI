import Boundary.PantallaAdminActualizaciones;
import Control.GestorActualizaciones;
import Entidades.*;
import Soporte.Init;

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

        Boolean hayActualizaciones = pantallaAdminActualizaciones.opcionImportarActDeVinoDeBodega(bodegasSist, control); //
        System.out.println("hay actualizaciones");
        if (hayActualizaciones) {

            System.out.println("se ejecuta mostrar bodegas");
            pantallaAdminActualizaciones.mostrarBodegas(control.getBodegas());

            control.solicitarSeleccionBodegas(pantallaAdminActualizaciones, bodegasSist); //dentro de este metodo esta el paso 5 que busca las actualizaciones
            System.out.println("se seleccionan bodegas");
            pantallaAdminActualizaciones.mostrarListaVinos(control.getVinosImportados(), "Vinos Importados");
            System.out.println("muestra vinos importados");
            control.determinarVinosActualizar(vinosDelSist);
            //no forma parte del CU(es para verificar que funciona el método)
            pantallaAdminActualizaciones.mostrarListaVinos(control.getVinosActualizables(), "Vinos para actualizar: ");
            System.out.println("muestra vinos que se actualizan");
            pantallaAdminActualizaciones.mostrarListaVinos(control.getVinosCreables(), "Vinos para crear: ");

            control.actualizarDatosDeVino(vinosDelSist, maridajesSist, varietalSist);
            pantallaAdminActualizaciones.mostrarActDeVinosActualizadosYcreados(vinosDelSist, "VINOS ACTUALIZADOS");
            //envia notificacion a enofilos
            control.buscarSeguidores(enofilosDelSistema, control.getBodegaSeleccionada());
            System.out.println("se envia notificacion a los usuarios:");
            System.out.println(control.getUsuarios().stream().toList());
            pantallaAdminActualizaciones.mostrarOpcionFinalizar(control);

        } else {
            System.out.println("No hay actualizaciones");
        }

    }


}

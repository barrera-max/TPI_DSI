import Boundary.PantallaAdminActualizaciones;
import Control.GestorActualizaciones;
import Entidades.*;
import Soporte.Init;

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
        System.out.println();

        pantallaAdminActualizaciones.habilitarPantalla();

        Boolean flag = control.opcionImportarActDeVinoDeBodega(bodegasSist, LocalDate.now()); //
        if (flag) {
            System.out.println("Hay actualizaciones!!!!");
            System.out.println("TAMAÑO INICIAL DEL ARREGLO:" + vinosDelSist.size());
            System.out.println("\nCargando las bodegas ccon actualizaciones...");

            pantallaAdminActualizaciones.mostrarBodega(control.getBodegas());
            System.out.println("\n");
            //aca hay que ingresar el nombre de la bodega tal cual aparece porque si no no funciona
            control.solicitarSeleccionBodegas(pantallaAdminActualizaciones, bodegasSist); //dentro de este metodo esta el paso 5 que busca las actualizaciones
            System.out.println("\n");

            //esto es a modo de comprobacion para ver si funcionaba el metodo
            System.out.println(":::VINOS IMPORTADOS:::");
            System.out.println(control.getVinosImportados().stream().toList());

            System.out.println("::::::::::::::::::::::::::::::::");
            control.determinarVinosActualizar(vinosDelSist);
            //y si el metodo funcionó
            System.out.println("\nVinos Actualizables");
            System.out.println(control.getVinosActualizables().stream().toList());
            System.out.println(":::::::::::::::::::::::::::::");
            System.out.println("\nVinos creables");
            System.out.println(control.getVinosCreables());

            System.out.println("::::::::::::::::::::::::::::::::");
            control.actualizarDatosDeVino(vinosDelSist);
            System.out.println("NOVEDADES\n");
            vinosDelSist.stream().forEach(vino -> System.out.println(vino.toString()));

            //envia notificacion a enofilos
            control.buscarSeguidores(enofilosDelSistema, control.getBodegaSeleccionada()); // falta probarlo

            System.out.println("TAMAÑO FINAL DEL ARREGLO: " + vinosDelSist.size());
            //Finalizar el programa
            pantallaAdminActualizaciones.mostrarOpcionFinalizar(control);
        } else {
            System.out.println("No hay actualizaciones");
        }

    }


}

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

        Boolean flag = control.opcionImportarActDeVinoDeBodega(bodegasSist, LocalDate.now()); //
        if (flag) {


            pantallaAdminActualizaciones.mostrarBodega(control.getBodegas());
            //aca hay que ingresar el nombre de la bodega tal cual aparece porque si no no funciona
            control.solicitarSeleccionBodegas(pantallaAdminActualizaciones, bodegasSist); //dentro de este metodo esta el paso 5 que busca las actualizaciones

            pantallaAdminActualizaciones.mostrarListaVinos(control.getVinosImportados(), "Vinos Importados");

            control.determinarVinosActualizar(vinosDelSist);
            //y si el metodo funcionó
            pantallaAdminActualizaciones.mostrarListaVinos(control.getVinosActualizables(), "Vinos para actualizar: ");
            pantallaAdminActualizaciones.mostrarListaVinos(control.getVinosCreables(), "Vinos para crear: ");

            control.actualizarDatosDeVino(vinosDelSist);
            mostrarListaVinos(vinosDelSist, "VINOS ACTUALIZADOS");
            //envia notificacion a enofilos
            control.buscarSeguidores(enofilosDelSistema, control.getBodegaSeleccionada());
            System.out.println(control.getUsuarios().stream().toList());

            //Finalizar el programa
            pantallaAdminActualizaciones.mostrarOpcionFinalizar(control);


        } else {
            System.out.println("No hay actualizaciones");
        }

    }


    public static void mostrarListaVinos(List<Vino> vinos, String mensaje) {
        StringBuilder sb = new StringBuilder();
        sb.append(":::" + mensaje + ":::\n\n");
        for (Vino vino : vinos) {
            sb.append(vino.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }


}

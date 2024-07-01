import Boundary.PantallaAdminActualizaciones;
import Control.GestorActualizaciones;

public class App {

    public static void main(String[] args) {

        GestorActualizaciones control = new GestorActualizaciones();
        PantallaAdminActualizaciones pantallaAdminActualizaciones = new PantallaAdminActualizaciones(control);

        control.setPantalla(pantallaAdminActualizaciones);

        pantallaAdminActualizaciones.opcionImportarActDeVinoDeBodega();

    }
}

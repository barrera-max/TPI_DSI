import Boundary.PantallaAdminActualizaciones;
import Control.GestorActualizaciones;

public class App {

    public static void main(String[] args) {

        PantallaAdminActualizaciones pantallaAdminActualizaciones = new PantallaAdminActualizaciones();
        GestorActualizaciones control = new GestorActualizaciones();

        pantallaAdminActualizaciones.setGestor(control);
        control.setPantalla(pantallaAdminActualizaciones);

        pantallaAdminActualizaciones.opcionImportarActDeVinoDeBodega();

    }
}

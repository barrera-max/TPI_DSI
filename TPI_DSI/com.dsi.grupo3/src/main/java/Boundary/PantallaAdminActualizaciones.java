package Boundary;

import Control.GestorActualizaciones;
import Entidades.Bodega;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
@Data
public class PantallaAdminActualizaciones {

    private GestorActualizaciones gestor;

    private List<String> nombresBodega;

    private String bodegaSeleccionada;


    public void setNombresBodega(List<String> nombresBodega) {
        this.nombresBodega = nombresBodega;
    }

    public void opcionImportarActDeVinoDeBodega(ArrayList<Bodega> bodegasDelSistema){
        habilitarPantalla();

    }

    public void habilitarPantalla(){
        System.out.println("####### PANTALLA ADMIN ACTUALIZACIONES ######");
        System.out.println("\n\n");
    }

    public void mostrarBodega(List<String> nombresBodega){
        for(String b : nombresBodega){
            System.out.println(b);
        }
    }

    public void solicitarSeleccionBodegas(){
        tomarSeleccionBodega();
        System.out.println("\n");
        System.out.println("##### Estas son las nuevas actualizaciones #####");
    }

    public void tomarSeleccionBodega(){

        Scanner sc = new Scanner(System.in);
        System.out.print("Seleccione la bodega a actualizar: ");
        String bodega = sc.nextLine();
        setBodegaSeleccionada(bodega);

    }

    public void mostrarOpcionFinalizar(GestorActualizaciones control) {
        System.out.println("Desea finalizar? (sí/no)");
        Scanner sc = new Scanner(System.in);
        String respuesta = sc.nextLine();
        if (respuesta.equalsIgnoreCase("sí")) {
            opcionFinalizar(control);
        }
    }

    public void opcionFinalizar(GestorActualizaciones control) {
        System.out.println("Finalizando...");
        control.finDelCU();
    }
}

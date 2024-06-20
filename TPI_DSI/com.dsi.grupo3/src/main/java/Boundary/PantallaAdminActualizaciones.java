package Boundary;

import Control.GestorActualizaciones;
import DTOs.VinoDto;
import Entidades.Bodega;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
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


    public void opcionImportarActDeVinoDeBodega(ArrayList<Bodega> bodegasDelSistema) {
        habilitarPantalla();

    }

    public void habilitarPantalla() {
        JOptionPane.showMessageDialog(null, "####### PANTALLA ADMIN ACTUALIZACIONES ######");

    }

    public void mostrarBodega(List<String> nombresBodega) {
        StringBuilder message = new StringBuilder("Bodegas disponibles:\n");
        for (String b : nombresBodega) {
            message.append(b).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    public void solicitarSeleccionBodega() {

        tomarSeleccionBodega();
        JOptionPane.showMessageDialog(null, "Seleccione la bodega a actualizar:");
        JOptionPane.showMessageDialog(null, "##### Estas son las nuevas actualizaciones #####");
        //aca muestra la lista de vinos
    }

    public void tomarSeleccionBodega() {
        String bodega = JOptionPane.showInputDialog(null, "Ingrese el nombre de la bodega:");
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


    public void mostrarListaVinosImportados(List<VinoDto> vinosImportados) {
        StringBuilder sb = new StringBuilder();
        sb.append(":::VINOS IMPORTADOS:::\n\n");
        for (VinoDto vino : vinosImportados) {
            sb.append(vino.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}

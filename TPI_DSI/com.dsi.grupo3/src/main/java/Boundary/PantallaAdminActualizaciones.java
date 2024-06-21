package Boundary;

import Control.GestorActualizaciones;
import DTOs.VinoDto;
import Entidades.Bodega;
import Entidades.Vino;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
@Data
public class PantallaAdminActualizaciones {



    private String bodegaSeleccionada;

    public void opcionImportarActDeVinoDeBodega(ArrayList<Bodega> bodegasDelSistema) {
        // Crear un botón personalizado para Importar Actualizaciones
        JButton importarActButton = new JButton("Importar Actualizaciones");

        // Crear un panel para contener el botón
        JPanel panel = new JPanel();
        panel.add(importarActButton);

        // Crear el cuadro de diálogo con el panel que contiene el botón
        JOptionPane optionPane = new JOptionPane(
                panel, // Componente de entrada
                JOptionPane.PLAIN_MESSAGE, // Tipo de mensaje (sin icono)
                JOptionPane.DEFAULT_OPTION, // Tipo de opción (por defecto)
                null, // Icono personalizado (null para ninguno)
                new Object[]{}, // Opciones (ninguna adicional)
                null // Opción predeterminada (null para ninguna)
        );

        // Crear un cuadro de diálogo modal personalizado
        JDialog dialog = optionPane.createDialog("Importar Actualizaciones de Vino");

        // Configurar acción para el botón Importar Actualizaciones
        importarActButton.addActionListener(e -> {
            habilitarPantalla(bodegasDelSistema);
            dialog.dispose(); // Cerrar el cuadro de diálogo después de habilitar la pantalla
        });

        // Mostrar el cuadro de diálogo
        dialog.setVisible(true);
    }

    public void habilitarPantalla(ArrayList<Bodega> bodegas) {
        JOptionPane.showMessageDialog(null, "####### PANTALLA ADMIN ACTUALIZACIONES ######");
        mostrarListaBodegas(bodegas);
    }

    public void mostrarBodega(List<String> nombresBodega) {
        StringBuilder message = new StringBuilder("Bodegas con actualizaciones:\n");
        for (String b : nombresBodega) {
            message.append(b).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    public void solicitarSeleccionBodega() {

        tomarSeleccionBodega();
        JOptionPane.showMessageDialog(null, "##### Estas son las nuevas actualizaciones #####");
        //aca muestra la lista de vinos
    }

    public void tomarSeleccionBodega() {
        String bodega = JOptionPane.showInputDialog(null, "Ingrese el nombre de la bodega:");
        setBodegaSeleccionada(bodega);

    }

    public void mostrarOpcionFinalizar(GestorActualizaciones control) {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea finalizar?", "Finalizar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            opcionFinalizar(control);
        }
    }

    public void opcionFinalizar(GestorActualizaciones control) {
        System.out.println("Finalizando...");
        control.finDelCU();
    }


    public void mostrarListaVinos(List<VinoDto> vinosImportados, String mensaje) {
        StringBuilder sb = new StringBuilder();
        sb.append(":::" + mensaje + ":::\n\n");
        for (VinoDto vino : vinosImportados) {
            sb.append(vino.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
    public void mostrarListaBodegas(List<Bodega> bodegas) {
        StringBuilder sb = new StringBuilder();
        for (Bodega bodega : bodegas) {
            sb.append(bodega.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, "BODEGAS:\n" + sb.toString());
    }


}


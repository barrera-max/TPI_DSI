package Boundary;

import Control.GestorActualizaciones;
import DTOs.VinoDto;
import Entidades.Bodega;
import Entidades.Vino;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class PantallaAdminActualizaciones {

    private JFrame frame;

    private String bodegaSeleccionada;

    private JPanel panel;

    public PantallaAdminActualizaciones() {
        frame = new JFrame("PANTALLA ADMIN ACTUALIZACIONES");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    public void opcionImportarActDeVinoDeBodega(ArrayList<Bodega> bodegasDelSistema) {
        JButton importarActButton = new JButton("Importar Actualizaciones");
        panel.add(importarActButton);

        importarActButton.addActionListener(e -> {
            habilitarPantalla(bodegasDelSistema);
        });
        frame.setVisible(true);
    }

    public void habilitarPantalla(ArrayList<Bodega> bodegas) {
        mostrarListaBodegas(bodegas);
    }

    public void mostrarBodegas(List<String> nombresBodega) {
        StringBuilder message = new StringBuilder("Hay bodegas con actualizaciones!!!\n");
        for (String b : nombresBodega) {
            message.append(b).append("\n");
        }
        JOptionPane.showMessageDialog(null, message, "PANTALLA ADMIN ACTUALIZACIONES", JOptionPane.NO_OPTION);
    }

    public void solicitarSeleccionBodega(List<String> nombresBodega) {
        JComboBox<String> comboBox = new JComboBox<>(nombresBodega.toArray(new String[0]));

        JPanel panel = new JPanel();
        panel.add(new JLabel("Seleccione una bodega:"));
        panel.add(comboBox);

        int opcion = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Seleccionar Bodega",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {
            String bodega = (String) comboBox.getSelectedItem();
            tomarSeleccionBodega(bodega);
        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionaron bodegas");
        }
    }

    public void tomarSeleccionBodega(String bodega) {
        setBodegaSeleccionada(bodega);
    }

    public void mostrarOpcionFinalizar(GestorActualizaciones control) {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea finalizar?", "Finalizar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.CLOSED_OPTION) {
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
        JOptionPane.showMessageDialog(null, sb.toString(), "PANTALLA ADMIN ACTUALIZACIONES", JOptionPane.INFORMATION_MESSAGE);
    }


    public void mostrarActDeVinosActualizadosYcreados(List<Vino> vinos, String mensaje) {
        StringBuilder sb = new StringBuilder();
        sb.append(":::" + mensaje + ":::\n\n");
        for (Vino vino : vinos) {
            sb.append(vino.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "VINOS", JOptionPane.WIDTH);
    }


    public void mostrarListaBodegas(List<Bodega> bodegas) {
        StringBuilder sb = new StringBuilder();
        for (Bodega bodega : bodegas) {
            sb.append(bodega.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, "BODEGAS:\n" + sb.toString(), "PANTALLA ADMIN ACTUALIZACIONES", JOptionPane.INFORMATION_MESSAGE);
    }


}


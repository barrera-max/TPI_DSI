package Boundary;

import Control.GestorActualizaciones;
import DTOs.VinoDto;
import Entidades.Bodega;
import Entidades.Vino;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class PantallaAdminActualizaciones {

    private GestorActualizaciones gestor;

    private List<String> nombresBodega;

    private String bodegaSeleccionada;

    private JFrame frame;

    private JPanel panel;

    public PantallaAdminActualizaciones() {
        frame = new JFrame("PANTALLA ADMIN ACTUALIZACIONES");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        // Mostrar el frame
        frame.setVisible(true);
    }

    public void opcionImportarActDeVinoDeBodega(ArrayList<Bodega> bodegasDelSistema) {

        // Crear un botón personalizado para Importar Actualizaciones
        JButton importarActButton = new JButton("Importar Actualizaciones");

        panel.removeAll();
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
        // Limpiar el panel antes de añadir nuevos componentes
        panel.removeAll();

        // Añadir etiquetas referidas a Bom Vino
        panel.add(new JLabel("BomVino"));
        panel.add(new JLabel("Descripción de Bom Vino"));
        // Revalidar y repintar el panel para reflejar los cambios
        panel.revalidate();
        panel.repaint();

        // Mostrar la lista de bodegas
        mostrarListaBodegas(bodegas);
    }

    public void mostrarBodegas(List<String> nombresBodega) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> bodegaJList = new JList<>(listModel);
        listModel.addElement("BODEGAS CON ACTUALIZACIONES:\n");
        for (String bodega : nombresBodega) {
            listModel.addElement(bodega);
        }
        JScrollPane scrollPane = new JScrollPane(bodegaJList);
        panel.removeAll();
        panel.add(scrollPane);

        panel.revalidate();
        panel.repaint();
    }

    public void solicitarSeleccionBodega(List<String> nombresBodega) {
        JComboBox<String> comboBox = new JComboBox<>(nombresBodega.toArray(new String[0]));

        panel.add(new JLabel("Seleccione una bodega:"));
        panel.add(comboBox);

        int opcion = JOptionPane.showConfirmDialog(
                panel,
                panel,
                "Seleccionar Bodega",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (opcion == JOptionPane.OK_OPTION) {
            String bodega = (String) comboBox.getSelectedItem();
            tomarSeleccionBodega(bodega);
        } else {
            JOptionPane.showMessageDialog(panel, "No se seleccionaron bodegas");
        }
    }

    public void tomarSeleccionBodega(String bodega) {
        setBodegaSeleccionada(bodega);
    }

    public void mostrarOpcionFinalizar(GestorActualizaciones control) {
        int opcion = JOptionPane.showConfirmDialog(panel, "¿Desea finalizar?", "Finalizar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.CLOSED_OPTION) {
            opcionFinalizar(control);
        }
    }

    public void opcionFinalizar(GestorActualizaciones control) {
        System.out.println("Finalizando...");
        control.finDelCU();
        System.exit(0);
    }


    public void mostrarListaVinos(List<VinoDto> vinosImportados, String mensaje) {
        StringBuilder sb = new StringBuilder();
        sb.append(":::" + mensaje + ":::\n\n");
        for (VinoDto vino : vinosImportados) {
            sb.append(vino.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(panel, sb.toString(), "PANTALLA ADMIN ACTUALIZACIONES", JOptionPane.INFORMATION_MESSAGE);
    }


    public void mostrarActDeVinosActualizadosYcreados(List<Vino> vinos, String mensaje) {
        StringBuilder sb = new StringBuilder();
        sb.append(":::" + mensaje + ":::\n\n");
        for (Vino vino : vinos) {
            sb.append(vino.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(panel, sb.toString(), "VINOS", JOptionPane.WIDTH);
    }


    public void mostrarListaBodegas(List<Bodega> bodegas) {
        String[] columnas = {"Nombre", "Descripcion", "Fecha Ultima Actualizacion"};
        // Crear el modelo de la tabla
        DefaultTableModel tabla = new DefaultTableModel(columnas, 0);

        for (Bodega bodega : bodegas) {

            String nombreBodega = bodega.getNombre();
            String desc = bodega.getDescripcion();
            LocalDate fecha = bodega.getFechaUltimaActualizacion();
            Object[] rowData = {nombreBodega, desc, fecha};
            tabla.addRow(rowData);
        }

        // Crear la tabla con el modelo
        JTable bodegaTable = new JTable(tabla);

        // Crear el JScrollPane para contener la tabla
        JScrollPane scrollPane = new JScrollPane(bodegaTable);

        // Limpiar el panel antes de añadir el JScrollPane
        panel.removeAll();
        panel.add(scrollPane);

        panel.revalidate();
        panel.repaint();
    }


}


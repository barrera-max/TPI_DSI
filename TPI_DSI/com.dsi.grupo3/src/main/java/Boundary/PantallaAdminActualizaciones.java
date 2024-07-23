package Boundary;

import Control.GestorActualizaciones;
import lombok.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

@Data
public class PantallaAdminActualizaciones {

    private static GestorActualizaciones gestor;

    private List<String> nombresBodega;

    private String bodegaSeleccionada;

    private JFrame frame;

    private JPanel panel;

    public PantallaAdminActualizaciones() {
        frame = new JFrame("PANTALLA ADMIN ACTUALIZACIONES");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public void opcionImportarActDeVinoDeBodega() {
        gestor = new GestorActualizaciones(this);
        habilitarPantalla();
        JButton btnImportarACt = new JButton("Importar actualizaciones");
        btnImportarACt.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnImportarACt.addActionListener(actionEvent -> {
            gestor.opcionImportarActDeVinoDeBodega();
        });


        panel.add(btnImportarACt, BorderLayout.CENTER);
        panel.add(Box.createVerticalGlue());

        frame.revalidate();
        frame.repaint();
    }

    public void habilitarPantalla() {
        JLabel lblVino = new JLabel("BOM VINO");
        lblVino.setFont(new Font("Arial", Font.BOLD, 16));
        lblVino.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(lblVino);
        panel.add(Box.createVerticalGlue());

        mostrarListaBodegas(); //modificar: no debe recibir un objeto Bodega
    }

    public void mostrarBodega(List<String> nombresBodega) {
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

    public void solicitarSeleccionBodegas(List<String> nombresBodega) {
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
            String bodegaSeleccion = (String) comboBox.getSelectedItem();
            tomarSeleccionBodega(bodegaSeleccion);
        } else {
            JOptionPane.showMessageDialog(panel, "No se seleccionaron bodegas");
        }
    }

    public void tomarSeleccionBodega(String nombreBodega) {
        gestor.tomarSeleccionBodega(nombreBodega);
    }

    public void mostrarOpcionFinalizar() {
        int opcion = JOptionPane.showConfirmDialog(panel, "¿Desea finalizar?", "Finalizar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            opcionFinalizar();
        }
    }

    public void opcionFinalizar() {
        gestor.tomarOpcionFinalizar();
    }

    public void mostrarActDeVinosActualizadosYcreados(String vinosActualizados) {
        JTextArea textArea = new JTextArea(vinosActualizados);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(900, 650));

        JOptionPane.showMessageDialog(panel, scrollPane, "VINOS BODEGA INTERNACIONAL", JOptionPane.INFORMATION_MESSAGE);

        gestor.buscarSeguidores();

    }

    public void mostrarListaBodegas() {
        JLabel lblBodegas = new JLabel("LISTADO DE BODEGAS");
        lblBodegas.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 12));

        lblBodegas.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(Box.createVerticalStrut(50));
        panel.add(lblBodegas);

        String[] columnas = {"Nombre", "Descripcion", "Fecha Ultima Actualizacion"};
        DefaultTableModel tabla = new DefaultTableModel(columnas, 0);

        List<String> listaBodegas = gestor.obtenerListaBodegas();
        //Desde el gestor mandar una lista<string> y la pantalla separa los campos con split('-')
        for (String campo : listaBodegas) {

            String[] datos = campo.split("--");

            String nombreBodega = datos[0];
            String desc = datos[1];
            String fecha = datos[2];
            Object[] rowData = {nombreBodega, desc, fecha};
            tabla.addRow(rowData);
        }

        JTable bodegaTable = new JTable(tabla);
        JScrollPane scrollPane = new JScrollPane(bodegaTable);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }
}


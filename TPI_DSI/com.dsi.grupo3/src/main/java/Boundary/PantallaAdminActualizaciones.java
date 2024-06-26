package Boundary;

import Control.GestorActualizaciones;
import DTOs.VinoDto;
import Entidades.Bodega;
import Entidades.Vino;
import lombok.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class PantallaAdminActualizaciones {

    private static GestorActualizaciones gestor;

    private List<String> nombresBodega;

    private String bodegaSeleccionada;

    private JFrame frame;

    private JPanel panel;

    public PantallaAdminActualizaciones(GestorActualizaciones gestor) {
        this.gestor = gestor;
        frame = new JFrame("PANTALLA ADMIN ACTUALIZACIONES");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 400);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);


    }

    public static void setGestor(GestorActualizaciones gestor) {
        PantallaAdminActualizaciones.gestor = gestor;
    }

    public void opcionImportarActDeVinoDeBodega() {
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

        mostrarListaBodegas(gestor.getBodegasSist());
    }

    public void mostrarBodegas(List<String> nombresBodega) {
        System.out.println("MOSTRANDO BODEGAS");
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

        gestor.tomarSeleccionBodega(bodega);
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

            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false); // Para que el texto no sea editable
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(900, 650)); // TamaÃ±o preferido del JScrollPane

            JOptionPane.showMessageDialog(panel, scrollPane, "VINOS", JOptionPane.INFORMATION_MESSAGE);

            gestor.buscarSeguidores();

    }


    public void mostrarListaBodegas(List<Bodega> bodegas) {
        JLabel lblBodegas = new JLabel("LISTADO DE BODEGAS");
        lblBodegas.setFont(new Font("Arial", Font.ITALIC | Font.BOLD,12));
        lblBodegas.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(Box.createVerticalStrut(50));
        panel.add(lblBodegas);

        String[] columnas = {"Nombre", "Descripcion", "Fecha Ultima Actualizacion"};
        DefaultTableModel tabla = new DefaultTableModel(columnas, 0);

        for (Bodega bodega : bodegas) {
            String nombreBodega = bodega.getNombre();
            String desc = bodega.getDescripcion();
            LocalDate fecha = bodega.getFechaUltimaActualizacion();
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


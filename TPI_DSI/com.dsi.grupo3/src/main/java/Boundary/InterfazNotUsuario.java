package Boundary;

import Observer.IObserverNotiActualizacion;
import lombok.NoArgsConstructor;

import javax.swing.*;

@NoArgsConstructor
public class InterfazNotUsuario implements IObserverNotiActualizacion {

    private JFrame frame;

    @Override
    public void actualizar(String nombreVino, double precio, String imagenEtiqueta, String notaDeCata) {

        String notiPush = "Se actualizaron los datos de Vino: " + nombreVino + " Precio:$" + precio +
                "\n Etiqueta: " + imagenEtiqueta + "\n Nota de cata: " + notaDeCata;

        notificarNovedad(notiPush);
    }

    public void notificarNovedad(String novedades) {
        Icon icono = UIManager.getIcon("OptionPane.informationIcon");

        // Mostrar el cuadro de diálogo personalizado
        JOptionPane.showMessageDialog(
                frame,
                novedades,
                "Actualización de Vino",
                JOptionPane.INFORMATION_MESSAGE,
                icono
        );
    }

}

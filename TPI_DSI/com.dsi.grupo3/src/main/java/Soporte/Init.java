package Soporte;

import Entidades.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Init {

    public static void init(List<Bodega> bodegasSist, List<Vino> vinosDelSist, List<Enofilo> enofilosDelSistema, List<Maridaje> maridajesSist,
                            List<Varietal> varietalSist) {
        TipoUva tipoUva = new TipoUva("Descripción del tipo de uva.",
                "Tipo Uva 1");
        TipoUva tipoUva2 = new TipoUva("Descripción del tipo de uva.",
                "Tipo Uva 2");
        TipoUva tipoUva3 = new TipoUva("Descripción del tipo de uva.",
                "Tipo Uva 3");


        Bodega bodega1 = new Bodega(123456, "Una bodega con una gran selección de vinos internacionales.",
                "Fundada en 1990, esta bodega ha sido un punto de referencia para los amantes del vino.",
                "Bodega Internacional",
                2,
                LocalDate.of(2024, 3, 1));
        Bodega bodega2 = new Bodega(654321,
                "Bodega local famosa por su vino tinto artesanal.",
                "Desde 1985, esta bodega ha producido vinos de alta calidad usando técnicas tradicionales.",
                "Bodega Artesanal",
                3,
                LocalDate.of(2024, 5, 3));
        Bodega bodega3 = new Bodega(123456,
                "Bodega local famosa por su vino tinto artesanal.",
                "Desde 1985, esta bodega ha producido vinos de alta calidad usando técnicas tradicionales.",
                "Bodega Artesanal",
                2,
                LocalDate.of(2024, 5, 3));
        Bodega bodega4 = new Bodega(123456,
                "Bodega local famosa por su vino tinto artesanal.",
                "Desde 1985, esta bodega ha producido vinos de alta calidad usando técnicas tradicionales.",
                "Bodega Artesanal",
                5,
                LocalDate.of(2024, 5, 3));

        bodegasSist.add(bodega1);
        bodegasSist.add(bodega2);

        Varietal varietal = new Varietal("Varietal único con sabor distintivo.", 75.5f, tipoUva);
        Varietal varietal2 = new Varietal("Varietal único con sabor distintivo.", 50.5f, tipoUva2);
        Maridaje maridaje = new Maridaje("Perfecto con carnes rojas y quesos fuertes.", "Maridaje 1");
        Maridaje maridaje2 = new Maridaje("Ideal con pescados y mariscos frescos.", "Maridaje 2");

        maridajesSist.add(maridaje);
        maridajesSist.add(maridaje2);


        varietalSist.add(varietal);
        varietalSist.add(varietal2);

        Vino vino1 = new Vino(2018, bodega1, "favicon.ico", "Gran Reserva", "nota de cata", 1000, varietal, maridaje2);
        Vino vino2 = new Vino(2, bodega2, "favicon.ico", "Bon Appétit", "nota de cata", 1000, varietal, maridaje);
        Vino vino3 = new Vino(3, bodega3, "favicon.ico", "Dom Pérignon", "nota de cata", 1000, varietal, maridaje);

        vinosDelSist.add(vino1);
        vinosDelSist.add(vino2);
        vinosDelSist.add(vino3);


        List<Bodega> seguidoBodegas = new ArrayList<>();
        seguidoBodegas.add(bodega1);
        //creo las bodegas que sigue

        Siguiendo seguido = new Siguiendo(seguidoBodegas, null, "24-04-04", "24-04-04");
        ArrayList<Siguiendo> seguidos = new ArrayList<>();
        seguidos.add(seguido);

        Usuario usuario = new Usuario("user@user", "admin123", false);

        Enofilo enofilo = new Enofilo(usuario, seguidos, "Maxi", "Martinez", "img.png");
        enofilosDelSistema.add(enofilo);
    }


}

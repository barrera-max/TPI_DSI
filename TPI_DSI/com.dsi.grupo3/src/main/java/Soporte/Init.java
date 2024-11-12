package Soporte;

import Entidades.*;
import Util.Conexion;

import java.util.ArrayList;
import java.util.List;




public class Init {

    //private static final Conexion ENTITY_MANAGER_UTIL = new Conexion();

    public static void init(List<Bodega> bodegasSist, List<Vino> vinosDelSist, List<Enofilo> enofilosDelSistema, List<Maridaje> maridajesSist,
                            List<Varietal> varietalSist, List<TipoUva> tiposUva) {

        /*maridajesSist.addAll(ENTITY_MANAGER_UTIL.getMaridajes());
        tiposUva.addAll(ENTITY_MANAGER_UTIL.getTiposUva());
        vinosDelSist.addAll(ENTITY_MANAGER_UTIL.getVinos());
        bodegasSist.addAll(ENTITY_MANAGER_UTIL.getBodegas());
        varietalSist.addAll(ENTITY_MANAGER_UTIL.getVarietales());
*/

        List<Bodega> seguidoBodegas = new ArrayList<>();
        //seguidoBodegas.add(bodega1);
        //creo las bodegas que sigue

        Siguiendo seguido = new Siguiendo(seguidoBodegas, null, "24-04-04", "24-04-04");
        ArrayList<Siguiendo> seguidos = new ArrayList<>();
        seguidos.add(seguido);

        Usuario usuario = new Usuario("user@user", "admin123", false);

        Enofilo enofilo = new Enofilo(usuario, seguidos, "Maxi", "Martinez", "img.png");
        enofilosDelSistema.add(enofilo);
    }


}

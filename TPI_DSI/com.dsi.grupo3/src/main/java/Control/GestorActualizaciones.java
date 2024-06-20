package Control;

import Boundary.InterfazSistemaDeBodegas;
import Boundary.PantallaAdminActualizaciones;
import DTOs.VinoDto;
import Entidades.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GestorActualizaciones {

    private List<String> bodegas; //solo guardo el nombre de las bodegas

    private Bodega bodegaSeleccionada;

    private Vino vino;

    private List<VinoDto> vinosImportados;

    private List<VinoDto> vinosActualizables;

    private List<VinoDto> vinosCreables;

    private List<String> usuarios;

    private Varietal varietal;

    private TipoUva tipoUva;

    private Maridaje maridaje;


    public boolean opcionImportarActDeVinoDeBodega(ArrayList<Bodega> bodega, LocalDate fechaActual) {
        // busca las bodegas del sistema con actualizaciones
        buscarBodegasConActualizaciones(bodega, fechaActual);
        if (!bodegas.isEmpty()) {
            return true;
        }
        return false;
    }


    public void buscarBodegasConActualizaciones(ArrayList<Bodega> bodegas, LocalDate fechaActual) { // Esto no sería mejor como guardar bodegas con actualizaciones?
        //busca entre las bodegas existentes en el sistema
        ArrayList<String> buscadas = new ArrayList<>();
        for (Bodega b : bodegas) {
            if (b.hayActualizaciones(fechaActual)) {
                buscadas.add(b.getNombre());
                setBodegas(buscadas);
            }
        }
    }

    public void solicitarSeleccionBodegas(PantallaAdminActualizaciones pantalla, List<Bodega> bodegasDelSist) {
        pantalla.solicitarSeleccionBodega();
        tomarSeleccionBodega(pantalla.getBodegaSeleccionada(), bodegasDelSist);
    }

    public void tomarSeleccionBodega(String nombreBodega, List<Bodega> bodegasDelSist) { // nombreBodega es ingresado por el usuario para buscar entre las Bodegas existentes
        for (Bodega bodega : bodegasDelSist) {
            if (bodega.getNombre().equals(nombreBodega)) {
                setBodegaSeleccionada(bodega);
            }
        }

        buscarActualizaciones();
    }

    public void buscarActualizaciones() {
        List<VinoDto> vinosAux;
        vinosAux = InterfazSistemaDeBodegas.buscarActualizaciones(this.bodegaSeleccionada); //el segundo es el metodo de interfaz sist de bodegas
        setVinosImportados(vinosAux);
    }

    public void determinarVinosActualizar(List<Vino> vinosSistema) {
        List<VinoDto> auxActualizables = new ArrayList<>();
        List<VinoDto> auxNoActualizables = new ArrayList<>();
        for (VinoDto vino : vinosImportados) {
            if (bodegaSeleccionada.tenesEsteVino(vino, vinosSistema)) auxActualizables.add(vino);
            else auxNoActualizables.add(vino);
        }
        setVinosActualizables(auxActualizables);
        setVinosCreables(auxNoActualizables);
    }

    public void actualizarDatosDeVino(List<Vino> vinosSistema) {
        bodegaSeleccionada.actualizarDatosDeVino(vinosSistema, vinosActualizables);

        for (VinoDto vino : vinosCreables) {
            Vino nuevo = crearVino(vino);
            vinosSistema.add(nuevo);
        }
    }

    public void buscarTipoUva() {

    }

    public void buscarMaridaje(String nombre, List<Maridaje> maridajeSistema) {
        setMaridaje(maridajeSistema.stream()
                .filter(maridaje -> maridaje.getNombre().equals(nombre))
                .findFirst()
                .orElse(null));
    }

    public Vino crearVino(VinoDto vinoDto) {

        Vino nuevo = new Vino(vinoDto.getAñada(), bodegaSeleccionada, vinoDto.getImagenEtiqueta(), vinoDto.getNombre(), vinoDto.getNotaDeCataBodega(), vinoDto.getPrecioARS(), varietal, maridaje);

        return nuevo;
    }

    public void buscarSeguidores(List<Enofilo> enofilosSistema, Bodega bodega) {
        List<String> auxEnofilos = new ArrayList<>(0);
        for (Enofilo enofilo : enofilosSistema) {
            if (enofilo.seguisBodega(bodega)) auxEnofilos.add(enofilo.getUsuario().getNombre());
        }
        setUsuarios(auxEnofilos);
    }

    public void finDelCU() {
        System.exit(0);
    }

    public void confirmarActualizacion() {
        // TODO - finalizar con la creación/modicicacion de los vinos
    }

    public void cancelarActualizacion() {
        // El cancelar actualizacion no hace nada, simplemente retorna para que continue con la siguiente bodega de la lsita de bodegas seleccionadas
        return;
    }


}


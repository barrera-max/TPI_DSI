package Control;

import Boundary.InterfazSistemaDeBodegas;
import Boundary.PantallaAdminActualizaciones;
import DTOs.VinoDto;
import Entidades.*;
import Soporte.Init;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@AllArgsConstructor
@Data
public class GestorActualizaciones {

    private ArrayList<String> bodegasConActualizaciones = new ArrayList<>(0);
    @Getter
    private ArrayList<Bodega> bodegasSist = new ArrayList<>();
    private ArrayList<Vino> vinosSist = new ArrayList<>();
    private ArrayList<Enofilo> enofilosSist = new ArrayList<>();
    private ArrayList<Maridaje> maridajesSist = new ArrayList<>();
    private ArrayList<Varietal> varietalSist = new ArrayList<>();
    private ArrayList<TipoUva> tipoUvaSist = new ArrayList<>();


    private PantallaAdminActualizaciones pantalla;

    private Bodega bodegaSeleccionada;

    private Vino vino;
    @Setter
    private ArrayList<VinoDto> vinosImportados = new ArrayList<>(0);

    private ArrayList<VinoDto> vinosActualizables = new ArrayList<>(0);

    private ArrayList<VinoDto> vinosCreables = new ArrayList<>(0);

    private ArrayList<String> usuarios = new ArrayList<>(0);

    private Varietal varietal;

    private TipoUva tipoUva;

    private Maridaje maridaje;


    public GestorActualizaciones(PantallaAdminActualizaciones pantalla) {
        this.pantalla = pantalla;
        Init.init(bodegasSist, vinosSist, enofilosSist, maridajesSist, varietalSist, tipoUvaSist);
    }


    public void opcionImportarActDeVinoDeBodega() {
        if (buscarBodegasConActualizaciones()) {
            pantalla.mostrarBodega(bodegasConActualizaciones);
            pantalla.solicitarSeleccionBodegas(bodegasConActualizaciones);
        } else {
            pantalla.mostrarOpcionFinalizar();
        }
    }

    public Boolean buscarBodegasConActualizaciones() {
        for (Bodega b : bodegasSist) {
            if (b.hayActualizaciones(LocalDate.now())) {
                bodegasConActualizaciones.add(b.getNombre());
            }
        }
        return (!bodegasConActualizaciones.isEmpty());
    }

    public void tomarSeleccionBodega(String nombreBodega) { // nombreBodega es ingresado por el usuario para buscar entre las Bodegas existentes
        for (Bodega bodega : bodegasSist) {
            if (bodega.esTuNombre(nombreBodega)) {
                setBodegaSeleccionada(bodega);
            }
        }

        buscarActualizaciones();
    }

    public void buscarActualizaciones() { //se importan bien los vinos
        ArrayList<VinoDto> vinosAux = InterfazSistemaDeBodegas.buscarActualizaciones();
        setVinosImportados(vinosAux);

        if (!vinosImportados.isEmpty()) {
            determinarVinosActualizar();
        }

    }

    public void determinarVinosActualizar() {//problema al setear los actualizables y creables
        int index = 0;
        for (VinoDto vino : vinosImportados) {
            if (bodegaSeleccionada.tenesEsteVino(vino.getAñada(), vino.getNombre(), vinosSist.get(index))) { //ver si a bodega le tengo que pasar los vinos del sist
                vinosActualizables.add(vino);
            } else vinosCreables.add(vino);
            index++;
            System.out.println(index);
        }
        actualizarDatosDeVino();
    }

    public void actualizarDatosDeVino() {
        int index = 0;
        for (VinoDto vinoDto : vinosActualizables) {
            bodegaSeleccionada.actualizarDatosDeVino(vinosSist.get(index), vinoDto.getAñada(), bodegaSeleccionada.getNombre(),
                    vinoDto.getPrecioARS(), vinoDto.getImagenEtiqueta(), vinoDto.getNotaDeCataBodega());
            index++;
        }

        for (VinoDto vino : vinosCreables) {
            buscarVarietal(vino.getVarietal().getDescripcion());
            buscarTipoUva(vino.getVarietal().getTipoDeUva()); //podria agregar otro atributo en dto que sea el nombre del tipo de uva
            buscarMaridaje(vino.getMaridaje()); //ver los paramtros de estos metodos buscar
            Vino nuevo = crearVino(vino);
            vinosSist.add(nuevo);
        }

        bodegaSeleccionada.setFechaUltimaActualizacion(LocalDate.now());
        String listadoDeVinosACtualizados = mostrarVinosActualizadosYcreados();
        pantalla.mostrarActDeVinosActualizadosYcreados(listadoDeVinosACtualizados);
    }

    /*Metodo que sirve para construir el string necesario para que la pantalla cree la tabla y muestre los vinos actualizados*/
    public String mostrarVinosActualizadosYcreados() {
        StringBuilder sb = new StringBuilder(":::" + "VINOS ACTUALIZADOS" + ":::\n");
        for (Vino vino : vinosSist) {
            sb.append(vino.toString());
        }
        return sb.toString();
    }

    public void buscarTipoUva(String tipoUva) {
        for (TipoUva uva : tipoUvaSist) {
            if (uva.sosTipoUva(tipoUva)) {
                setTipoUva(uva);
            }
        }
    }

    public void buscarVarietal(String descripcion) {
        for (Varietal var : varietalSist) {
            if (var.buscarVarietal(descripcion)) {
                setVarietal(var);
                break;
            } else setVarietal(null);
        }
    }

    public void buscarMaridaje(String nombreMaridaje) {
        for (Maridaje m : maridajesSist) {
            if (m.sosMaridaje(nombreMaridaje)) setMaridaje(m);
        }
    }

    public Vino crearVino(VinoDto vinoDto) {
        System.out.println(varietal);
        System.out.println("TIPO UVA" + tipoUva);
        Vino vino;
        if (varietal == null) {
            vino = new Vino(vinoDto.getAñada(), bodegaSeleccionada, vinoDto.getImagenEtiqueta(), vinoDto.getNombre(), vinoDto.getNotaDeCataBodega(),
                    vinoDto.getPrecioARS(), vinoDto.getVarietal().getDescripcion(), vinoDto.getVarietal().getPorcentajeComposicion(),
                    tipoUva, maridaje);
        } else {
            vino = new Vino(vinoDto.getAñada(), bodegaSeleccionada, vinoDto.getImagenEtiqueta(),
                    vinoDto.getNombre(),
                    vinoDto.getNotaDeCataBodega(),
                    vinoDto.getPrecioARS(),
                    varietal,   //varietal existente
                    maridaje);
        }
        return vino;

    }

    public void buscarSeguidores() {
        ArrayList<String> auxEnofilos = new ArrayList<>(0);
        for (Enofilo enofilo : enofilosSist) {
            if (enofilo.seguisBodega(bodegaSeleccionada)) auxEnofilos.add(enofilo.getUsuario().getNombre());
        }
        setUsuarios(auxEnofilos);
        pantalla.mostrarOpcionFinalizar();
    }

    public void tomarOpcionFinalizar() {
        finDelCU();
    }

    public void finDelCU() {
        System.exit(0);
    }
}


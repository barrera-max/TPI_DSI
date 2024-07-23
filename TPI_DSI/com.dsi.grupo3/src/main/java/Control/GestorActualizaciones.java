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
import java.util.List;

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

    public ArrayList<String> obtenerListaBodegas() {
        ArrayList<String> listaBodegas = new ArrayList<>();
        for (Bodega bodega : bodegasSist) {
            String dato = bodega.getNombre() + "--" + bodega.getDescripcion() + "--" + bodega.getFechaUltimaActualizacion() + "--" + bodega.getPeriodoActualizacion() + "--";
            System.out.println(dato);
            listaBodegas.add(dato);

        }
        return listaBodegas;
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
                System.out.println(b.hayActualizaciones(LocalDate.now()));
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

    public void buscarActualizaciones() {
        try {  //InterfazBodegas retorna un array de dtos
            setVinosImportados(InterfazSistemaDeBodegas.buscarActualizaciones());
            actualizarDatosDeVino();
            String listadoDeVinosACtualizados = mostrarVinosActualizadosYcreados();
            pantalla.mostrarActDeVinosActualizadosYcreados(listadoDeVinosACtualizados);
        } catch (Exception e) { //NullPointerException?
            System.out.println(e.getMessage());
        }
    }


    public void actualizarDatosDeVino() {
        int index = 0;
        for (VinoDto vinoDto : vinosImportados) {
            if (bodegaSeleccionada.actualizarDatosDeVino(vinosSist.get(index), vinoDto.getAniada(), bodegaSeleccionada.getNombre(),
                    vinoDto.getPrecioARS(), vinoDto.getImagenEtiqueta(), vinoDto.getNotaDeCataBodega())) {
                ;

                index++;
            } else {
                buscarVarietal(vinoDto.getVarietal());
                buscarTipoUva(vinoDto.getTipoDeUva());
                buscarMaridaje(vinoDto.getMaridaje());
                Vino nuevo = crearVino(vinoDto);
                vinosSist.add(nuevo);

            }
        }
        bodegaSeleccionada.setFechaUltimaActualizacion(LocalDate.now());

    }

    /*Metodo que sirve para construir el string necesario para que la pantalla cree la tabla y muestre los vinos actualizados*/
    public String mostrarVinosActualizadosYcreados() {
        StringBuilder sb = new StringBuilder(":::" + "VINOS ACTUALIZADOS: " + bodegaSeleccionada.getNombre() + ":::\n");
        for (Vino vino : vinosSist) {
            sb.append(vino.toString());
        }
        return sb.toString();
    }

    public void buscarTipoUva(String tipoUva) {
        for (TipoUva uva : tipoUvaSist) {
            if (uva.sosTipoUva(tipoUva)) {
                setTipoUva(uva);
                break;
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
        if (varietal == null) {  //constructor con creacion de varietal
            return new Vino(vinoDto.getAniada(), bodegaSeleccionada, vinoDto.getImagenEtiqueta(),
                    vinoDto.getNombre(), vinoDto.getNotaDeCataBodega(), vinoDto.getPrecioARS(),
                    vinoDto.getVarietal(),
                    tipoUva, maridaje);
        } else {
            return new Vino(vinoDto.getAniada(), bodegaSeleccionada, vinoDto.getImagenEtiqueta(),
                    vinoDto.getNombre(),
                    vinoDto.getNotaDeCataBodega(),
                    vinoDto.getPrecioARS(),
                    varietal,   //varietal existente
                    maridaje);
        }
    }

    public void buscarSeguidores() {
        ArrayList<String> auxEnofilos = new ArrayList<>(0);
        for (Enofilo enofilo : enofilosSist) {
            if (enofilo.seguisBodega(bodegaSeleccionada)) auxEnofilos.
                    add(enofilo.getUsuario().getNombre());
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


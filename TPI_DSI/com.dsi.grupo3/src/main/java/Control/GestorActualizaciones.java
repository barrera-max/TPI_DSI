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
        Init.init(bodegasSist, vinosSist, enofilosSist, maridajesSist, varietalSist);
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
        ArrayList<VinoDto> vinosAux = InterfazSistemaDeBodegas.buscarActualizaciones(bodegaSeleccionada);
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
        System.out.println("VINOS CREABLES;" + vinosCreables.stream().toList());
        System.out.println("VINOS ACTUALIZA: " + vinosActualizables.stream().toList());
        actualizarDatosDeVino();
    }

    public void actualizarDatosDeVino() {
        int index = 0;
        for(VinoDto vinoDto: vinosActualizables){
            bodegaSeleccionada.actualizarDatosDeVino(vinosSist.get(index),vinoDto.getAñada(),vinoDto.getBodega(),/*para metodo sosVinoActualizable*/
                    vinoDto.getPrecioARS(),vinoDto.getImagenEtiqueta(), vinoDto.getNotaDeCataBodega());
            index++;
        }

        for (VinoDto vino : vinosCreables) {
            buscarVarietal(vino.getVarietal());
            System.out.println(varietal);
            buscarMaridaje(vino.getMaridaje());
            Vino nuevo = crearVino(vino);
            vinosSist.add(nuevo);
        }

        bodegaSeleccionada.setFechaUltimaActualizacion(LocalDate.now());
        String listadoDeVinosACtualizados = mostrarVinosActualizadosYcreados();
        pantalla.mostrarActDeVinosActualizadosYcreados(listadoDeVinosACtualizados);
    }

    /*Metodo que sirve para construir el string necesario para que la pantalla cree la tabla y muestre los vinos actualizados*/
    public String mostrarVinosActualizadosYcreados(){
        StringBuilder sb = new StringBuilder(":::" + "VINOS ACTUALIZADOS" + ":::\n\n");
        for(Vino vino: vinosSist){
            sb.append(vino.toString());
        }
        return sb.toString();
    }

    public void buscarTipoUva() {

    }

    public void buscarVarietal(String tipoDeUva) {
        for (Varietal var : varietalSist) {
            System.out.println("El varietal:" + var.toString() + "\n");
            if (var.buscarVarietal(tipoDeUva)) setVarietal(var);
        }
    }

    public void buscarMaridaje(String nombreMaridaje) {
        for (Maridaje m : maridajesSist) {
            if (m.sosMaridaje(nombreMaridaje)) setMaridaje(m);
        }
    }

    public Vino crearVino(VinoDto vinoDto) {

        Vino nuevo = new Vino(vinoDto.getAñada(), bodegaSeleccionada, vinoDto.getImagenEtiqueta(),
                vinoDto.getNombre(),
                vinoDto.getNotaDeCataBodega(),
                vinoDto.getPrecioARS(),
                varietal,
                maridaje);

        return nuevo;
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


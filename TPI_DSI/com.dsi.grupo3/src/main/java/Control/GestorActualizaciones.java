package Control;

import Boundary.InterfazSistemaDeBodegas;
import Boundary.PantallaAdminActualizaciones;
import DTOs.VinoDto;
import Entidades.*;
import Soporte.Init;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class GestorActualizaciones {

    private ArrayList<String> bodegasConActualizaciones = new ArrayList<>(0); //solo guardo el nombre de las bodegas

    @Getter
    private  ArrayList<Bodega> bodegasSist = new ArrayList<>();
    private  ArrayList<Vino> vinosSist = new ArrayList<>();
    private  ArrayList<Enofilo> enofilosSist = new ArrayList<>();
    private  ArrayList<Maridaje> maridajesSist = new ArrayList<>();
    private  ArrayList<Varietal> varietalSist = new ArrayList<>();

    @Setter
    private PantallaAdminActualizaciones pantalla;

    private Bodega bodegaSeleccionada;

    private Vino vino;
    @Setter
    private ArrayList<VinoDto> vinosImportados;

    private ArrayList<VinoDto> vinosActualizables;

    private ArrayList<VinoDto> vinosCreables;

    private ArrayList<String> usuarios;

    private Varietal varietal;

    private TipoUva tipoUva;

    private Maridaje maridaje;


    public GestorActualizaciones(){
        Init.init(bodegasSist, vinosSist, enofilosSist, maridajesSist, varietalSist);
    }

    public boolean opcionImportarActDeVinoDeBodega() {
        if(buscarBodegasConActualizaciones()){
        pantalla.mostrarBodegas(bodegasConActualizaciones);}
        solicitarSeleccionBodegas();
        return (!bodegasConActualizaciones.isEmpty());
    }

    public Boolean buscarBodegasConActualizaciones() {
        //busca entre las bodegas existentes en el sistema
        for (Bodega b : bodegasSist) {
            if (b.hayActualizaciones(LocalDate.now())) {
                bodegasConActualizaciones.add(b.getNombre());
                return true;
            }
        }
        return false;
    }

    public void solicitarSeleccionBodegas() {
        pantalla.solicitarSeleccionBodega(bodegasConActualizaciones);
        tomarSeleccionBodega(pantalla.getBodegaSeleccionada());
    }

    public void tomarSeleccionBodega(String nombreBodega) { // nombreBodega es ingresado por el usuario para buscar entre las Bodegas existentes
        for (Bodega bodega : bodegasSist) {
            if (bodega.getNombre().equals(nombreBodega)) {
                setBodegaSeleccionada(bodega);
            }
        }

        buscarActualizaciones();
    }

    public void buscarActualizaciones() {
        ArrayList<VinoDto> vinosAux;
        vinosAux = InterfazSistemaDeBodegas.buscarActualizaciones(this.bodegaSeleccionada);
        setVinosImportados(vinosAux);
        if(!vinosImportados.isEmpty()){
            determinarVinosActualizar();
        };
    }

    public void determinarVinosActualizar() {
        ArrayList<VinoDto> auxActualizables = new ArrayList<>();
        ArrayList<VinoDto> auxCreables = new ArrayList<>();
        for (VinoDto vino : vinosImportados) {
            if (bodegaSeleccionada.tenesEsteVino(vino, vinosSist)){
                System.out.println("Agrego el vino " + vino.toString() + "para actualizar");
                auxActualizables.add(vino);}
            else auxCreables.add(vino);
        }
        setVinosActualizables(auxActualizables);
        setVinosCreables(auxCreables);

        actualizarDatosDeVino();
    }

    public void actualizarDatosDeVino() {
        bodegaSeleccionada.actualizarDatosDeVino(vinosSist, vinosActualizables);

        for (VinoDto vino : vinosCreables) {
            buscarVarietal(varietalSist, vino.getVarietal());
            System.out.println(varietal);
            buscarMaridaje(vino.getMaridaje(), maridajesSist);
            Vino nuevo = crearVino(vino);
            vinosSist.add(nuevo);
        }

        bodegaSeleccionada.setFechaUltimaActualizacion(LocalDate.now());
        pantalla.mostrarActDeVinosActualizadosYcreados(vinosSist,"ACTUALIZADOS");
    }

    public void buscarTipoUva() {

    }

    public void buscarVarietal(List<Varietal> varietalList, String tipoDeUva) {
        for (Varietal var : varietalList) {
            System.out.println("El varietal:" + var.toString() + "\n");
            if (var.buscarVarietal(tipoDeUva)) setVarietal(var);
        }
    }

    public void buscarMaridaje(String nombreMaridaje, List<Maridaje> maridajeSistema) {
        for (Maridaje m : maridajeSistema) {
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

    public void tomarOpcionFinalizar(){
        finDelCU();
    }

    public void finDelCU() {
        System.exit(0);
    }


}


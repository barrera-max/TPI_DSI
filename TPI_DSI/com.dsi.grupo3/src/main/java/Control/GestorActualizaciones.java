package Control;

import Boundary.InterfazSistemaDeBodegas;
import Boundary.PantallaAdminActualizaciones;
import DTOs.VinoDto;
import Entidades.*;
import Soporte.Init;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class GestorActualizaciones {

    private ArrayList<String> bodegasConActualizaciones = new ArrayList<>(0);

    private static final ArrayList<Bodega> BODEGAS_SIST = new ArrayList<>();
    private static final ArrayList<Vino> VINOS_SIST = new ArrayList<>();
    private static final ArrayList<Enofilo> ENOFILOS_SIST = new ArrayList<>(); //ver si es necesario que sean constantes
    private ArrayList<Maridaje> maridajesSist = new ArrayList<>();
    private ArrayList<Varietal> varietalSist = new ArrayList<>();
    private ArrayList<TipoUva> tipoUvaSist = new ArrayList<>();


    private PantallaAdminActualizaciones pantalla;

    private Bodega bodegaSeleccionada;

    private ArrayList<VinoDto> vinosImportados;

    private List<String> usuarios ;

    private Varietal varietal;

    private TipoUva tipoUva;

    private Maridaje maridaje;


    public GestorActualizaciones(PantallaAdminActualizaciones pantalla) {
        this.pantalla = pantalla;
        Init.init(BODEGAS_SIST, VINOS_SIST, ENOFILOS_SIST, maridajesSist, varietalSist, tipoUvaSist);
    }

    public List<String> obtenerListaBodegas() {
        return BODEGAS_SIST.stream()
                .map(bodega -> bodega.getNombre()
                        + "--" + bodega.getDescripcion() + "--"
                        + bodega.getFechaUltimaActualizacion()
                        + "--" + bodega.getPeriodoActualizacion() + "--")
                .collect(Collectors.toList());
    }

    public void opcionImportarActDeVinoDeBodega() {

        buscarBodegasConActualizaciones();
        if (!bodegasConActualizaciones.isEmpty()) {
            pantalla.mostrarBodega(bodegasConActualizaciones);
            pantalla.solicitarSeleccionBodegas(bodegasConActualizaciones);
        } else {
            pantalla.mostrarOpcionFinalizar();
        }
    }

    public void buscarBodegasConActualizaciones() {

        BODEGAS_SIST.stream()
                .filter(bodega -> bodega.hayActualizaciones(LocalDate.now()))
                .map(Bodega::getNombre)
                .forEach(bodegasConActualizaciones::add);
    }

    public void tomarSeleccionBodega(String nombreBodega) { // nombreBodega es ingresado por el usuario para buscar entre las Bodegas existentes

        setBodegaSeleccionada(BODEGAS_SIST
                .stream()
                .filter(bodega -> bodega.esTuNombre(nombreBodega))
                .findFirst()
                .orElse(null));

        buscarActualizaciones();
    }

    public void buscarActualizaciones() {
        try {  //InterfazBodegas retorna un array de dtos
            setVinosImportados(InterfazSistemaDeBodegas.buscarActualizaciones());
            actualizarDatosDeVino();
            pantalla.mostrarActDeVinosActualizadosYcreados(mostrarVinosActualizadosYcreados());
        } catch (Exception e) { //NullPointerException?
            System.out.println(e.getMessage());
        }
    }

    //modificar este metodo: no pasar dtos, no pasar el indice, es mejor utilizar los metodos equals de la clase vino
    public void actualizarDatosDeVino() {
        int index = 0;

        for (VinoDto vinoDto : vinosImportados) {
            Map<String, Object> datosVino = mapToDto(vinoDto);

            if (bodegaSeleccionada.actualizarDatosDeVino(VINOS_SIST.get(index), (int) (datosVino.get("añada")),
                    (double) (datosVino.get("precioARS")), (String) datosVino.get("imagenEtiqueta"),
                    (String) datosVino.get("notaDeCataBodega"))) {
                index++;
            } else {
                buscarVarietal(vinoDto.getVarietal());
                buscarTipoUva(vinoDto.getTipoDeUva());
                buscarMaridaje(vinoDto.getMaridaje());

                Vino nuevo = crearVino((int) (datosVino.get("añada")),
                        (String) datosVino.get("imagenEtiqueta"),
                        (String) datosVino.get("nombre"),
                        (String) datosVino.get("notaDeCataBodega"),
                        (double) (datosVino.get("precioARS")),
                        (String) (datosVino.get("varietal"))
                );
                VINOS_SIST.add(nuevo);
            }
        }
        bodegaSeleccionada.setFechaUltimaActualizacion(LocalDate.now());

    }

    /*Metodo que sirve para construir el string necesario para que la pantalla cree la tabla y muestre los vinos actualizados*/
    public String mostrarVinosActualizadosYcreados() {
        StringBuilder sb = new StringBuilder("SE ACTUALIZARON LOS VINOS DE: " + bodegaSeleccionada.getNombre() + "!!!\n");

        VINOS_SIST.forEach(vino -> sb.append(vino.toString()).append("\n"));

        return sb.toString();
    }

    public void buscarTipoUva(String tipoUva) {

        setTipoUva(tipoUvaSist
                .stream()
                .filter(uva -> uva.sosTipoUva(tipoUva))
                .findFirst()
                .orElse(null));

    }

    public void buscarVarietal(String descripcion) {

        setVarietal(varietalSist
                .stream()
                .filter(var -> var.buscarVarietal(descripcion))
                .findFirst()
                .orElse(null));

    }

    public void buscarMaridaje(String nombreMaridaje) {

        setMaridaje(maridajesSist
                .stream()
                .filter(m -> m.sosMaridaje(nombreMaridaje))
                .findFirst()
                .orElse(null));

    }

    //corregir este metodo, no pedir datos al dto
    public Vino crearVino(int aniada, String imagenEtiqueta, String nombre, String notaDeCataBodega,
                          double precioARS, String varietalVino) {

        if (varietal == null) {  //constructor con creacion de varietal
            return new Vino(aniada, bodegaSeleccionada, imagenEtiqueta,
                    nombre, notaDeCataBodega, precioARS,
                    varietalVino,
                    tipoUva, maridaje);
        } else {
            return new Vino(aniada, bodegaSeleccionada, imagenEtiqueta,
                    nombre,
                    notaDeCataBodega,
                    precioARS,
                    varietal,   //varietal existente
                    maridaje);
        }
    }

    //ver este metodo: Chequear si es correcto este casting
    public void buscarSeguidores() {
        setUsuarios((ENOFILOS_SIST
                .stream()
                .filter(e -> e.seguisBodega(bodegaSeleccionada))
                .map(e -> e.getUsuario().getNombre())
                .toList()));
        /*pantalla.mostrarOpcionFinalizar();*/
    }

    public void tomarOpcionFinalizar() {
        finDelCU();
    }

    public void finDelCU() {
        System.exit(0);
    }


    public Map<String, Object> mapToDto(VinoDto vinoDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("añada", vinoDto.getAniada());
        map.put("nombre", vinoDto.getNombre());
        map.put("precioARS", vinoDto.getPrecioARS());
        map.put("imagenEtiqueta", vinoDto.getImagenEtiqueta());
        map.put("notaDeCataBodega", vinoDto.getNotaDeCataBodega());
        map.put("varietal", vinoDto.getVarietal());
        return map;
    }


}


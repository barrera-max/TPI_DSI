package Control;

import Boundary.APISistemaDeBodega;
import Boundary.InterfazNotUsuario;
import Boundary.InterfazSistemaDeBodegas;
import Boundary.PantallaAdminActualizaciones;
import DAOs.*;
import DTOs.VinoDto;
import Entidades.*;
import Soporte.Init;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import Observer.*;


@AllArgsConstructor
@Data
public class GestorActualizaciones implements ISujeto {

    private ArrayList<String> bodegasConActualizaciones = new ArrayList<>(0);

    private static List<Bodega> BODEGAS_SIST = new ArrayList<>();
    private static List<Vino> VINOS_SIST = new ArrayList<>();
    private static final ArrayList<Enofilo> ENOFILOS_SIST = new ArrayList<>(); //ver si es necesario que sean constantes
    private ArrayList<Varietal> varietalSist = new ArrayList<>();

    private PantallaAdminActualizaciones pantalla;

    private Bodega bodegaSeleccionada;

    private List<VinoDto> vinosImportados;

    private List<String> usuarios;

    private Varietal varietal;

    private TipoUva tipoUva;

    private Maridaje maridaje;

    private InterfazSistemaDeBodegas interfazSistemaDeBodegas;

    private List<IObserverNotiActualizacion> observers = new ArrayList<>();

    public GestorActualizaciones(PantallaAdminActualizaciones pantalla) {
        this.pantalla = pantalla;
        this.interfazSistemaDeBodegas = null;


        VINOS_SIST = new VinoDAO().findAll();
        BODEGAS_SIST = new BodegaDAO().findAll();
        //Init.init(BODEGAS_SIST, VINOS_SIST, ENOFILOS_SIST, maridajesSist, varietalSist, tipoUvaSist);
    }

    public List<String> obtenerListaBodegas() {
        DAO bodegaDAO = new BodegaDAO();

        List<Bodega> bodegasSist = bodegaDAO.findAll();

        return bodegasSist.stream()
                .map(bodega -> bodega.getNombre()
                        + "--" + bodega.getDescripcion() + "--"
                        + bodega.getFechaUltimaActualizacion()
                        + "--" + bodega.getPeriodoActualizacion() + "--")
                .collect(Collectors.toList());
    }

    public void opcionImportarActDeVinoDeBodega() {
        buscarBodegasConActualizaciones();
    }

    //en la secuencia no debe recibir parametros ya que lo hace con el atributo del gestor
    public void buscarBodegasConActualizaciones() {
        BODEGAS_SIST.stream()
                .filter(bodega -> bodega.hayActualizaciones(LocalDate.now()))
                .map(Bodega::getNombre)
                .forEach(bodegasConActualizaciones::add);


        if (!bodegasConActualizaciones.isEmpty()) {
            pantalla.mostrarBodega(bodegasConActualizaciones);
            pantalla.solicitarSeleccionBodegas(bodegasConActualizaciones);
        } else {
            //si no hay bodegas A1
            pantalla.mostrarOpcionFinalizar();
        }
    }

    //si se modela A2 tiene que recibir un List<String>
    public void tomarSeleccionBodega(String nombreBodega) { // nombreBodega es ingresado por el usuario para buscar entre las Bodegas existentes

        //genera un array de bodegas encontradas y las setea
        setBodegaSeleccionada(BODEGAS_SIST
                .stream()
                .filter(bodega -> bodega.esTuNombre(nombreBodega))
                .findFirst()
                .orElse(null));

        buscarActualizaciones();
        actualizarDatosDeVino();
        buscarSeguidores();
        pantalla.mostrarOpcionFinalizar();
    }

    public void buscarActualizaciones() {
        try {  //InterfazBodegas retorna un array de dtos

            InterfazSistemaDeBodegas api = new APISistemaDeBodega();

            setVinosImportados(api.buscarActualizaciones());


        } catch (Exception e) { //NullPointerException?
            System.out.println(e.getMessage());
        }
    }

    //modificar este metodo: no pasar dtos, no pasar el indice, es mejor utilizar los metodos equals de la clase vino
    public void actualizarDatosDeVino() {
        int index = 0;

        VinoDAO vinos = new VinoDAO();

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
                vinos.create(nuevo);
                /*VINOS_SIST.add(nuevo);*/
            }
        }

        List<Vino> vinosSist = vinos.findAll();
        vinosSist.forEach(System.out::println);

        pantalla.mostrarActDeVinosActualizadosYcreados(mostrarVinosActualizadosYcreados());
        bodegaSeleccionada.setFechaUltimaActualizacion(LocalDate.now());

    }

    /*Metodo que sirve para construir el string necesario para que la pantalla cree la tabla y muestre los vinos actualizados*/
    public String mostrarVinosActualizadosYcreados() {
        StringBuilder sb = new StringBuilder("SE ACTUALIZARON LOS VINOS DE: " + bodegaSeleccionada.getNombre() + "!!!\n");

        List<Vino> vinos = new VinoDAO().findAll();

        vinos.forEach(vino -> sb.append(vino.toString()).append("\n"));

        return sb.toString();
    }

    public void buscarTipoUva(String tipoUva) {

        List<TipoUva> uvas = new TipoUvaDAO().findAll();

        setTipoUva(uvas
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

        List<Maridaje> maridajes = new MaridajeDAO().findAll();

        setMaridaje(maridajes
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
                    varietal,
                    maridaje);
        }
    }

    //ver este metodo: Chequear si es correcto este casting
    public void buscarSeguidores() {
        DAO enofiloDAO = new EnofiloDAO();

        List<Enofilo> enofilos = enofiloDAO.findAll();

        setUsuarios(enofilos
                .stream()
                .filter(e -> e.seguisBodega(bodegaSeleccionada))
                .map(e -> e.getUsuario().getNombre())
                .toList());
        if (usuarios != null) {
            System.out.println(enofilos.get(0).getNombre() + ": sigue a la bodega" + bodegaSeleccionada.getNombre());
        };

        suscribir(new InterfazNotUsuario());
        notificar();
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

    public static void main(String[] args) {
        PantallaAdminActualizaciones pantallaAdminActualizaciones = new PantallaAdminActualizaciones();
        //Inicio CU
        pantallaAdminActualizaciones.opcionImportarActDeVinoDeBodega();
    }

    @Override
    public void suscribir(IObserverNotiActualizacion observer) {
        if (observer == null) {
            throw new NullPointerException("Observer no puede ser nulo");
        } else {
            if (!this.observers.contains(observer)) {
                this.observers.add(observer);
            }
        }
    }

    @Override
    public void desuscribir(IObserverNotiActualizacion observer) {
        if (observer == null) {
            throw new NullPointerException("Observer no puede ser nulo");
        } else {
            this.observers.remove(observer);
        }
    }

    @Override
    public void notificar() {
        observadores.forEach(e -> e.actualizar(VINOS_SIST));
    }
}


package Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Vino")
@NamedQuery(name = "Vino.findAll", query = "SELECT v FROM Vino v")
public class Vino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //probar con IDENTITY o AUTO pero generando la tabla sequence en la BD
    @Column(name = "vid")
    private long id;

    private int aniada;

    @OneToOne
    @JoinColumn(name = "bodegaId")
    private Bodega bodega;
    private String imagenEtiqueta;
    private String nombre;
    private String notaDeCataBodega;
    private double precioARS;

    @OneToOne(cascade = CascadeType.PERSIST)                           //por el momento estas relaciones seran uno a uno
    @JoinColumn(name = "varietalId")    //VER SI ES NECESARIO QUE SEAN UNO A MUCHOS
    private Varietal varietal;

    @OneToOne
    @JoinColumn(name = "maridajeId")
    private Maridaje maridaje;


    public Vino(int aniada, Bodega bodega, String imagenEtiqueta, String nombre, String notaDeCataBodega, double precioARS, Varietal varietal, Maridaje maridaje) {

        this.aniada = aniada;
        this.bodega = bodega;
        this.imagenEtiqueta = imagenEtiqueta;
        this.nombre = nombre;
        this.notaDeCataBodega = notaDeCataBodega;
        this.precioARS = precioARS;
        this.varietal = varietal;
        this.maridaje = maridaje;
    }

    public Vino(int aniada, Bodega bodega, String imagenEtiqueta, String nombre, String notaDeCataBodega, double precioARS,
            /*datos para crear varietal*/ String descripcion, TipoUva tipoUva, Maridaje maridaje) {
        this.aniada = aniada;
        this.bodega = bodega;
        this.imagenEtiqueta = imagenEtiqueta;
        this.nombre = nombre;
        this.notaDeCataBodega = notaDeCataBodega;
        this.precioARS = precioARS;
        this.varietal = crearVarietal(descripcion, 0.0, tipoUva);
        this.maridaje = maridaje;
    }

    public Boolean sosEsteVino(int aniada, String nombre) {
        return this.aniada == aniada &&
                this.nombre.equals(nombre);
    }

    //corregir este metodo
    public Boolean sosVinoActualizable(int aniada, String nombreBodega) {
        return this.aniada == aniada && this.bodega.getNombre().equals(nombreBodega);
    }

    public Varietal crearVarietal(String descripcion, double porcComposicion, TipoUva tipoUva) {
        return new Varietal(descripcion, porcComposicion, tipoUva);
    }


    @Override
    public String toString() {
        return "\nVino " + nombre +
                " - Añada " + aniada +
                ", Bodega" +
                bodega.getNombre() +
                " ,Imagen Etiqueta '" + imagenEtiqueta +
                " , Nota de cata '" + notaDeCataBodega +
                " , Precio(ARS):$" + precioARS +
                '\n' +
                '\t' + varietal +
                '\n' +
                '\t' + maridaje +
                '\n';
    }
}

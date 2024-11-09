package Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Vino {

    @Id
    private long id;

    private int aniada;

    @OneToOne
    @JoinColumn(name = "id")
    private Bodega bodega;
    private String imagenEtiqueta;
    private String nombre;
    private String notaDeCataBodega;
    private double precioARS;

    @ManyToOne
    @JoinColumn(name = "id")
    private Varietal varietal;

    @ManyToOne
    @JoinColumn(name = "id")
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

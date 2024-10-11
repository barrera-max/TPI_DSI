package Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vino {

    private int aniada;
    private Bodega bodega;
    private String imagenEtiqueta;
    private String nombre;
    private String notaDeCataBodega;
    private double precioARS;
    private Varietal varietal;
    private Maridaje maridaje;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vino vino = (Vino) o;
        return aniada == vino.aniada &&
                Objects.equals(nombre, vino.nombre);
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

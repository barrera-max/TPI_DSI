package Entidades;

import DTOs.VinoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Vino {

    private int añada;
    private Bodega bodega;
    private String imagenEtiqueta;
    private String nombre;
    private String notaDeCataBodega;
    private double precioARS;
    private Varietal varietal;
    private Maridaje maridaje;


    public void calcularRanking() {
    }

    public void compararEtiqueta() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vino vino = (Vino) o;
        return añada == vino.añada &&
                Double.compare(vino.precioARS, precioARS) == 0 &&
                Objects.equals(bodega, vino.bodega) &&
                Objects.equals(imagenEtiqueta, vino.imagenEtiqueta) &&
                Objects.equals(nombre, vino.nombre) &&
                Objects.equals(notaDeCataBodega, vino.notaDeCataBodega) &&
                Objects.equals(varietal, vino.varietal) &&
                Objects.equals(maridaje, vino.maridaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(añada, bodega, imagenEtiqueta, nombre, notaDeCataBodega, precioARS, varietal, maridaje);
    }

    public Boolean esDeBodega(Bodega bodegaValidar) {
        if (bodegaValidar.equals(this.bodega)) {
            return true;
        }
        return false;
    }

    public Boolean sosEsteVino(int añada, String nombre) {
        return this.añada == añada &&
                this.nombre.equals(nombre);
    }

    public Boolean sosVinoActualizable(int añada, String nombreBodega) {
            return  this.añada == añada && this.bodega.getNombre().equals(nombreBodega);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vino ");
        sb.append(nombre);
        sb.append(" - Añada ").append(añada);
        sb.append(", ");sb.append(bodega);
        sb.append(" ,Imagen Etiqueta '").append(imagenEtiqueta);
        sb.append(" , Nota de cata '").append(notaDeCataBodega);
        sb.append(" , Precio(ARS):$").append(precioARS);
        sb.append('\n');sb.append(varietal);
        sb.append('\n');sb.append(maridaje);
        sb.append('\n');
        return sb.toString();
    }
}

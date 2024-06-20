package Entidades;

import DTOs.VinoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

    public boolean sosEsteVino(VinoDto vinoDto) {
        if (vinoDto == null) {
            return false;
        }
        return this.añada == vinoDto.getAñada() &&
                this.bodega.getNombre().equals(vinoDto.getBodega());
    }

    public int sosVinoActualizable(List<VinoDto> vinosActualizables) {
        for (int i = 0; i < vinosActualizables.size(); i++) {
            VinoDto vinoActualizable = vinosActualizables.get(i);
            if (vinoActualizable.getAñada() == this.añada &&
                    vinoActualizable.getBodega().equals(this.bodega.getNombre())) {
                return i;
            }
        }
        return -1;
    }


    public void esDeRegionVitivinicola() {
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Vino{");
        sb.append("añada=").append(añada);
        sb.append(", bodega=").append(bodega);
        sb.append(", imagenEtiqueta='").append(imagenEtiqueta).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", notaDeCataBodega='").append(notaDeCataBodega).append('\'');
        sb.append(", precioARS=").append(precioARS);
        sb.append(", varietal=").append(varietal);
        sb.append(", maridaje=").append(maridaje);
        sb.append('}');
        return sb.toString();
    }
}

package Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Varietal {

    private String descripcion;

    private double porcentajeComposicion;

    private TipoUva tipoDeUva;

    public Varietal(String desc, double porcentajeComposicion){
        this.descripcion = desc;
        this.porcentajeComposicion = porcentajeComposicion;
    }

    public Boolean buscarVarietal(String descripcion) {
        return this.descripcion.equals(descripcion);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Varietal ");
        sb.append("Descripcion='").append(descripcion).append('\'');
        sb.append("PorcentajeComposicion=").append(porcentajeComposicion);
        sb.append(tipoDeUva);
        sb.append('\'');
        return sb.toString();
    }
}

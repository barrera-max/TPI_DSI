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


    public boolean buscarVarietal(String nombreUva) {
        return tipoDeUva.sosVarietal(nombreUva);
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

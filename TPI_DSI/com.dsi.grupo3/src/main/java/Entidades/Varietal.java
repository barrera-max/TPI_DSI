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


    public Boolean buscarVarietal(String descripcion) {
        return this.descripcion.equals(descripcion);
    }

    @Override
    public String toString() {
        return "Varietal " + "Descripcion='" + descripcion + '\'' +
                "PorcentajeComposicion=" + porcentajeComposicion +
                tipoDeUva +
                '\'';
    }
}

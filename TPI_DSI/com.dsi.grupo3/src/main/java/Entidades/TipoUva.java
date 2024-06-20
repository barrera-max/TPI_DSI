package Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoUva {
    private String descripcion;
    private String nombre;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TipoUva{");
        sb.append("descripcion='").append(descripcion).append('\'');
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

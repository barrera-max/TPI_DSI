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
        final StringBuilder sb = new StringBuilder("\nTipoUva");
        sb.append(" Descripcion ").append(descripcion);
        sb.append(" Nombre='").append(nombre);
        return sb.toString();
    }
}

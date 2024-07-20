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


    public boolean sosTipoUva(String tipoUva) {
        return this.getNombre().equals(tipoUva);
    }

    @Override
    public String toString() {
        return "\n\tTipoUva" + " Descripcion " + descripcion +
                " Nombre='" + nombre;
    }

}

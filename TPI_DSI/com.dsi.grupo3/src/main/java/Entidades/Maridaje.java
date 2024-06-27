package Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Maridaje {

    private String descripcion;
    private String nombre;


    public boolean sosMaridaje(String nombreMaridaje) {
        return nombre.equals(nombreMaridaje);
    }
}

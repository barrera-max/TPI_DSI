package Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Enofilo {

    private Usuario usuario;
    private List<Siguiendo> seguido;
    private String nombre;
    private String apellido;
    private String imagenPerfil;

    public Boolean seguisBodega(Bodega bodega) {
        return seguido
                .stream()
                .anyMatch(siguiendo ->
                        siguiendo.sosDeBodega(bodega));
    }


}

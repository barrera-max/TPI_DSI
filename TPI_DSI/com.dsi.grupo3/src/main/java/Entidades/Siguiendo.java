package Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Siguiendo {

    private List<Bodega> bodega;
    private List<Enofilo> amigo;
    private String fechaInicio;
    private String fechaFin;


    public Boolean sosDeBodega(Bodega bodegaBuscada) {
        for (Bodega b : this.bodega) {
            if (b.equals(bodegaBuscada)) {
                return true;
            }
        }
        return false;
    }
}

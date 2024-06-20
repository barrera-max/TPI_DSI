package DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VinoDto {

    private int añada;

    private String bodega;

    private String imagenEtiqueta;

    private String nombre;

    private String notaDeCataBodega;

    private double precioARS;

    private String varietal;

    private String maridaje;

//    public Vino mapperVino(){
//
//    }


    @Override
    public String toString() {
        return "\n VinoDto{" +
                "añada=" + añada +
                ", bodega='" + bodega + '\'' +
                ", imagenEtiqueta='" + imagenEtiqueta + '\'' +
                ", nombre='" + nombre + '\'' +
                ", notaDeCataBodega='" + notaDeCataBodega + '\'' +
                ", precioARS=" + precioARS +
                ", varietal='" + varietal + '\'' +
                ", maridaje='" + maridaje + '\'' +
                '}';
    }
}

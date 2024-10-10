package DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class VinoDto {

    private int aniada;

    private String imagenEtiqueta;

    private String nombre;

    private String notaDeCataBodega;

    private double precioARS;

    private String varietal;

    private String tipoDeUva;

    private String maridaje;


}

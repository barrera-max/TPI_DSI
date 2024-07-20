package DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VinoDto {

    private int aniada;

    private String imagenEtiqueta;

    private String nombre;

    private String notaDeCataBodega;

    private double precioARS;

    private VarietalDto varietal;

    private String maridaje;

    @Override
    public String toString() {
        return "Vino " + nombre +
                " Añada " + aniada + ' ' +
                ", " +
                " ,Imagen Etiqueta '" + imagenEtiqueta +
                " ,Nota de cata '" + notaDeCataBodega +
                " ,Precio(ARS):$" + precioARS +
                '\n' +
                varietal +
                '\n' +
                maridaje +
                '\n';
    }
}

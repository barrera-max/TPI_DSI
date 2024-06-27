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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vino ");
        sb.append(nombre);
        sb.append(" Añada ").append(añada).append(' ');
        sb.append(", ");
        sb.append(bodega);
        sb.append(" ,Imagen Etiqueta '").append(imagenEtiqueta);
        sb.append(" ,Nota de cata '").append(notaDeCataBodega);
        sb.append(" ,Precio(ARS):$").append(precioARS);
        sb.append('\n');
        sb.append(varietal);
        sb.append('\n');
        sb.append(maridaje);
        sb.append('\n');
        return sb.toString();
    }
}

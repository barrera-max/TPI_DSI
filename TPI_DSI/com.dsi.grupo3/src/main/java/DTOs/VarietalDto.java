package DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VarietalDto {

    private String descripcion;

    private double porcentajeComposicion;

    private String tipoDeUva;
}

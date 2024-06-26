package Entidades;

import DTOs.VinoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Varietal {

    private String descripcion;

    private double porcentajeComposicion;

    private TipoUva tipoDeUva;


    public void buscarVarietal(List<Varietal> varietalSistema){

    }

    public boolean sosVarietal(VinoDto vinoDto){
        return this.getTipoDeUva().getNombre().equals(vinoDto.getVarietal());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Varietal ");
        sb.append("Descripcion='").append(descripcion).append('\'');
        sb.append("PorcentajeComposicion=").append(porcentajeComposicion);
        sb.append(tipoDeUva);
        sb.append('\'');
        return sb.toString();
    }
}

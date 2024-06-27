package Entidades;


import DTOs.VinoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bodega {

    private int coordenadasUbicacion;
    private String descripcion;
    private String historia;
    private String nombre;
    private int periodoActualizacion;
    private LocalDate fechaUltimaActualizacion;


    public boolean hayActualizaciones(LocalDate fechaActual) {
        return validarFechaUltimaActualizacion(fechaActual);
    }

    public Boolean validarFechaUltimaActualizacion(LocalDate fechaActual) {
        long mesesPasados = ChronoUnit.MONTHS.between(this.fechaUltimaActualizacion, fechaActual);
        return mesesPasados >= this.periodoActualizacion;
    }

    public boolean tenesEsteVino(VinoDto vinodto, List<Vino> vinosSistema) {
        for (Vino vino : vinosSistema) {
            return vino.sosEsteVino(vinodto);
        }
        return false;
    }

    public void actualizarDatosDeVino(List<Vino> vinosSistema, List<VinoDto> vinosActualizables) {
        int index;
        for (Vino vino : vinosSistema) {
            index = vino.sosVinoActualizable(vinosActualizables);
            if (index != -1) {
                vino.setPrecioARS(vinosActualizables.get(index).getPrecioARS());
                vino.setImagenEtiqueta(vinosActualizables.get(index).getImagenEtiqueta());
                vino.setNotaDeCataBodega(vinosActualizables.get(index).getNotaDeCataBodega());
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n\tBodega=");
        sb.append(nombre).append('\'');
        sb.append(" Coordenadas:").append(coordenadasUbicacion);
        sb.append(", Descripcion:'").append(descripcion).append('\'');
        sb.append(", \nHistoria:'").append(historia).append('\'');
        sb.append(", \nPeriodo actualizacion:").append(periodoActualizacion);
        sb.append(", Ultima actualizacion:").append(fechaUltimaActualizacion);
        sb.append('\n');
        return sb.toString();
    }
}

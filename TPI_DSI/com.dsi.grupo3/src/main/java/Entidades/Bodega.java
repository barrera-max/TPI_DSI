package Entidades;


import DTOs.VinoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

}

package Entidades;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Boolean actualizarDatosDeVino(Vino vino, int aniada, String nombreBodega, double precio, String imagenEtiqueta, String notaDeCata) {
        if (vino.sosVinoActualizable(aniada, nombreBodega)) {
            vino.setPrecioARS(precio);
            vino.setImagenEtiqueta(imagenEtiqueta);
            vino.setNotaDeCataBodega(notaDeCata);
            return true;
        } else {
            return false;
        }
    }

    public Boolean esTuNombre(String nombreBodega) {
        return nombre.equals(nombreBodega);
    }

    @Override
    public String toString() {
        return "\n\tBodega=" + nombre + '\'' +
                " Coordenadas:" + coordenadasUbicacion +
                ", Descripcion:'" + descripcion + '\'' +
                ", \nHistoria:" + historia + '\'' +
                ", \nPeriodo actualizacion:" + periodoActualizacion +
                ", Ultima actualizacion:" + fechaUltimaActualizacion +
                '\n';
    }
}

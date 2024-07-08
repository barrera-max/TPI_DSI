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

    public Boolean tenesEsteVino(int añada, String nombre, Vino vinoSistema) {
        return vinoSistema.sosEsteVino(añada, nombre);
    }

    public void actualizarDatosDeVino(Vino vino, int añada, String nombreBodega, double precio, String imagenEtiqueta, String notaDeCata) {
        if (vino.sosVinoActualizable(añada, nombreBodega)) {

            vino.setPrecioARS(precio);
            vino.setImagenEtiqueta(imagenEtiqueta);
            vino.setNotaDeCataBodega(notaDeCata);
        }

    }

    public boolean esTuNombre(String nombreBodega) {
        return nombre.equals(nombreBodega);
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

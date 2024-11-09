package Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Varietal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    private double porcentajeComposicion;

    @OneToOne
    @JoinColumn(name= "tipoUvaId")
    private TipoUva tipoDeUva;

    public Varietal(String descripcion, double porcentajeComposicion, TipoUva tipoDeUva) {
        this.descripcion = descripcion;
        this.porcentajeComposicion = porcentajeComposicion;
        this.tipoDeUva = tipoDeUva;
    }

    public Boolean buscarVarietal(String descripcion) {
        return this.descripcion.equals(descripcion);
    }

    @Override
    public String toString() {
        return "Varietal " + "Descripcion='" + descripcion + '\'' +
                "PorcentajeComposicion=" + porcentajeComposicion +
                tipoDeUva +
                '\'';
    }
}

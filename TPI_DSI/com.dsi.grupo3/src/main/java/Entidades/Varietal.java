package Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Varietal")
@NamedQuery(name = "Varietal.findAll", query = "SELECT va FROM Varietal va")
public class Varietal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaid")
    private Long id;

    private String descripcion;

    private double porcentajeComposicion;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tipoUvaId")
    //el nombre de la join column debe ser el mismo de la tabla en la que estoy mapeando las columnas
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

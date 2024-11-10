package Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Maridaje")
@NamedQuery(name = "Maridaje.findAll", query = "SELECT m FROM Maridaje m")
public class Maridaje {

    @Id
    @Column(name = "mid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String descripcion;
    private String nombre;

    public Maridaje(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public boolean sosMaridaje(String nombreMaridaje) {
        return nombre.equals(nombreMaridaje);
    }
}

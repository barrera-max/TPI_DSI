package Entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TipoUva")
@NamedQuery(name= "TipoUva.findAll", query = "SELECT t FROM TipoUva t")
public class TipoUva {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tid")
    private Long idTipoUva;

    private String descripcion;
    private String nombre;

    public TipoUva(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public boolean sosTipoUva(String tipoUva) {
        return this.getNombre().equals(tipoUva);
    }

    @Override
    public String toString() {
        return "\n\tTipoUva" + " Descripcion " + descripcion +
                " Nombre='" + nombre;
    }

}

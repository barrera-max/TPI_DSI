package Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Siguiendo")
public class Siguiendo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    private long siguiendoId;

    @ManyToOne
    @JoinColumn(name = "enofilo_id", referencedColumnName = "eid")
    @ToString.Exclude
    private Enofilo enofilo;//relacion inversa con enofilos


    @ManyToOne
    @JoinColumn(name = "bodega_id", referencedColumnName = "bid")
    @ToString.Exclude
    private Bodega bodega; //bodega que sigue

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;


    /*public Boolean sosDeBodega(Bodega bodegaBuscada) {
        for (Bodega b : this.bodega) {
            if (b.equals(bodegaBuscada)) {
                return true;
            }
        }
        return false;
    }*/

    @Override
    public String toString() {
        return "Siguiendo{" +
                "siguiendoId=" + siguiendoId +
                ", enofilo=" + enofilo +
                ", bodega=" + bodega +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}

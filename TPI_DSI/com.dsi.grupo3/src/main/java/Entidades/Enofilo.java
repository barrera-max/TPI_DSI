package Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name= "Enofilo")
public class Enofilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="eid")
    private long enofiloId;

    @OneToOne
    @JoinColumn(name="usuario_Id", referencedColumnName = "uid")
    @ToString.Exclude
    private Usuario usuario;

    @OneToMany(mappedBy = "enofilo", cascade = CascadeType.ALL, orphanRemoval = true,
    fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Siguiendo> seguido;//relacion bidireccional con Siguiendo
    private String nombre;
    private String apellido;
    private String imagenPerfil;


    public Boolean seguisBodega(Bodega bodega) {
        for(Siguiendo siguiendo : seguido) {
            if(siguiendo.getBodega().getNombre().equals(bodega.getNombre())){
                return true;
            }
        };
        return false;
    }

    @Override
    public String toString() {
        return "Enofilo{" +
                "enofiloId=" + enofiloId +
                ", usuario=" + usuario +
                ", seguido=" + seguido +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", imagenPerfil='" + imagenPerfil + '\'' +
                '}';
    }
}

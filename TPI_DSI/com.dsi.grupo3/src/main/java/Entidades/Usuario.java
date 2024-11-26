package Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name= "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="uid")
    private long usuarioId;

    private String nombre;
    private String contraseña;
    private Boolean premium;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + usuarioId +
                ", nombre='" + nombre + '\'' +
                ", premium=" + premium +
                '}';
    }
}






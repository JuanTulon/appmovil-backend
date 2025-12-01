package ProyectoLimpiFreshRest.LimpiFresh.Modelo;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "rol")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    private String nombreRol;

}
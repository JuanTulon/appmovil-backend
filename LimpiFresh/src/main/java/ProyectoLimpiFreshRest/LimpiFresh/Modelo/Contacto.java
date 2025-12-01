package ProyectoLimpiFreshRest.LimpiFresh.Modelo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacto")
public class Contacto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    private String nombre;
    private String email;
    private String asunto;
    @Column(length = 2000)
    private String mensaje;

}
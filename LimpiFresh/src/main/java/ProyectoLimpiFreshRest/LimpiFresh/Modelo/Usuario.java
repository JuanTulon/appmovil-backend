package ProyectoLimpiFreshRest.LimpiFresh.Modelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    private String nombre;
    private String email;
    private String password;
    private String rut;
    private String region;
    private String comuna;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id")
    private Rol rol;

    // Métodos explícitos para evitar problemas con Lombok en compilación
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}

package ProyectoLimpiFreshRest.LimpiFresh.Modelo;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "boleta")
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    @Column(name = "numero_boleta", unique = true)
    private String numeroBoleta;
    private LocalDateTime fechaBoleta = LocalDateTime.now();
    private String nombreCliente;
    private String direccion;
    private String ciudad;
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private Integer subtotal;
    private Integer iva;
    private Integer total;
    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoletaItem> items;
}

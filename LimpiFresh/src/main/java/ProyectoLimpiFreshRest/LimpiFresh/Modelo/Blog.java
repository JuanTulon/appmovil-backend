package ProyectoLimpiFreshRest.LimpiFresh.Modelo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    private String titulo;
    @Column(length = 4000)
    private String contenido;
    /**
     * URL o ruta de la imagen asociada al blog.
     * El frontend (admin) puede enviar aquí una URL pública o una ruta servida por el backend.
     */
    @Column(name = "imagen_url", length = 1000)
    private String imagenUrl;

    // Setter explícito para evitar problemas con Lombok al compilar
    public void setId(Integer id) {
        this.id = id;
    }
}
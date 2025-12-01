package ProyectoLimpiFreshRest.LimpiFresh.Modelo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    private String nombre;
    @Column(name = "descripcion_corta")
    private String descripcionCorta;
    @Column(name = "descripcion_larga", length = 2000)
    private String descripcionLarga;
    private Integer precio;
    private Boolean oferta = false;
    private Integer precioOferta;
    private String categoria;
    private String img;
    private Integer stock;
    private Integer iva;

    // Setter explícito para evitar problemas con Lombok al compilar
    public void setId(Integer id) {
        this.id = id;
    }
}


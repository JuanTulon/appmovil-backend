package ProyectoLimpiFreshRest.LimpiFresh.Modelo;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carrito_item")
public class CarritoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    @ManyToOne
    @JoinColumn(name = "carrito_id")
    private Carrito carrito;
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    private Integer cantidad;
    private Integer precioUnitario;
    private Boolean ofertaAplicada;
    private Integer precioOfertaUnitario;
}
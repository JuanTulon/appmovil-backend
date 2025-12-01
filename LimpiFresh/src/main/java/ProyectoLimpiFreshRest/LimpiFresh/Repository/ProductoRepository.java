package ProyectoLimpiFreshRest.LimpiFresh.Repository;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByOfertaTrue();
}
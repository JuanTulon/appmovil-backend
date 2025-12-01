package ProyectoLimpiFreshRest.LimpiFresh.Repository;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Boleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoletaRepository extends JpaRepository<Boleta, Integer> {
    Optional<Boleta> findByNumeroBoleta(String numeroBoleta);
}

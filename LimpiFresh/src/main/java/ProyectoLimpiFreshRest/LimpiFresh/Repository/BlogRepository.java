package ProyectoLimpiFreshRest.LimpiFresh.Repository;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
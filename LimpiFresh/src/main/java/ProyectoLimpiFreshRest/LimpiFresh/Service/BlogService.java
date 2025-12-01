package ProyectoLimpiFreshRest.LimpiFresh.Service;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Blog;
import ProyectoLimpiFreshRest.LimpiFresh.Repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> listarTodos() {
        return blogRepository.findAll();
    }

    public Optional<Blog> buscarPorId(int id) {
        return blogRepository.findById(id);
    }

    public Blog guardar(Blog blog) {
        return blogRepository.save(blog);
    }

    public void eliminar(int id) {
        blogRepository.deleteById(id);
    }
}
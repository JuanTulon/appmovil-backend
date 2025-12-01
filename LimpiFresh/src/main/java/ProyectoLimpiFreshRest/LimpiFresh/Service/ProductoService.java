package ProyectoLimpiFreshRest.LimpiFresh.Service;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Producto;
import ProyectoLimpiFreshRest.LimpiFresh.Repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public List<Producto> listarOfertas() {
        return productoRepository.findByOfertaTrue();
    }

    public Optional<Producto> buscarPorId(int id) {
        return productoRepository.findById(id);
    }

    public Producto guardar(Producto p) {
        return productoRepository.save(p);
    }

    public void eliminar(int id) {
        productoRepository.deleteById(id);
    }
}
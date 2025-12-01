package ProyectoLimpiFreshRest.LimpiFresh.Service;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Rol;
import ProyectoLimpiFreshRest.LimpiFresh.Repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Rol crearRol(String nombreRol) {
        if (rolRepository.findByNombreRol(nombreRol).isPresent()) {
            throw new RuntimeException("El rol ya existe");
        }

        Rol rol = new Rol();
        rol.setNombreRol(nombreRol.toUpperCase() );
        return rolRepository.save(rol);
    }

    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    public Optional<Rol> buscarPorId(Integer id) {
        return rolRepository.findById(id);
    }

    public Optional<Rol> buscarPorNombre(String nombreRol) {
        return rolRepository.findByNombreRol(nombreRol.toUpperCase());
    }

    public void eliminarRol(Integer id) {
        if (!rolRepository.existsById(id)) {
            throw new RuntimeException("El rol no existe");
        }
        rolRepository.deleteById(id);
    }
}

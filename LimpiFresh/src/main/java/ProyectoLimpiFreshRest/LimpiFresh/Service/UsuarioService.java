package ProyectoLimpiFreshRest.LimpiFresh.Service;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Rol;
import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Usuario;
import ProyectoLimpiFreshRest.LimpiFresh.Repository.RolRepository;
import ProyectoLimpiFreshRest.LimpiFresh.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    public Usuario registrarCliente(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }
        Rol rolCliente = rolRepository.findByNombreRol("CLIENTE").orElseThrow(() -> new RuntimeException("Rol CLIENTE no existe"));
        usuario.setRol(rolCliente);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario registrarAdmin(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }
        Rol rolAdmin = rolRepository.findByNombreRol("ADMIN")
                .orElseThrow(() -> new RuntimeException("Rol ADMIN no existe"));
        usuario.setRol(rolAdmin);
        return usuarioRepository.save(usuario);
    }
}
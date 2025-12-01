package ProyectoLimpiFreshRest.LimpiFresh.Controller;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Rol;
import ProyectoLimpiFreshRest.LimpiFresh.Service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    // ---------------------- Crear ----------------------
    @Operation(summary = "Crear un rol (CLIENTE, ADMIN, etc.)")
    @PostMapping
    public ResponseEntity<?> crearRol(@RequestBody Rol rol) {
        try {
            Rol nuevo = rolService.crearRol(rol.getNombreRol());
            return ResponseEntity.ok(nuevo);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // ---------------------- Listar ----------------------
    @Operation(summary = "Listar todos los roles")
    @GetMapping
    public List<Rol> listarRoles() {
        return rolService.listarRoles();
    }

    // ---------------------- Eliminar ----------------------
    @Operation(summary = "Eliminar rol por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable Integer id) {
        try {
            rolService.eliminarRol(id);
            return ResponseEntity.ok("Rol eliminado con éxito");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
package ProyectoLimpiFreshRest.LimpiFresh.Controller;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Usuario;
import ProyectoLimpiFreshRest.LimpiFresh.Service.UsuarioService;
import ProyectoLimpiFreshRest.LimpiFresh.Repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @Operation(summary = "Registrar nuevo usuario (rol CLIENTE)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario registrado correctamente",
                    content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Error de validación o correo ya registrado")
    })
    @PostMapping("/registro")
    public ResponseEntity<?> registrar(
            @RequestBody(
                    description = "Datos del usuario a registrar. El rol CLIENTE se asigna automáticamente.",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Usuario.class))
            )
            @org.springframework.web.bind.annotation.RequestBody Usuario usuario) {
        try {
            Usuario creado = usuarioService.registrarCliente(usuario);
            return ResponseEntity.ok(creado);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Operation(summary = "Iniciar sesión")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login correcto. Devuelve el usuario con su rol.",
                    content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "401", description = "Contraseña incorrecta"),
            @ApiResponse(responseCode = "404", description = "Usuario no registrado, debe crear una cuenta")
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody(
                    description = "Credenciales de acceso (email y password).",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Usuario.class))
            )
            @org.springframework.web.bind.annotation.RequestBody Usuario loginRequest) {
        return usuarioService.buscarPorEmail(loginRequest.getEmail())
                .<ResponseEntity<?>>map(usuario -> {
                    if (!usuario.getPassword().equals(loginRequest.getPassword())) {
                        return ResponseEntity.status(401).body("Contraseña incorrecta");
                    }
                    // Aquí el frontend puede revisar usuario.getRol().getNombreRol()
                    // para decidir si muestra la opción de ADMIN o no.
                    return ResponseEntity.ok(usuario);
                })
                .orElse(ResponseEntity.status(404).body("Usuario no registrado, debe crear una cuenta"));
    }

    @PostMapping("/crear-admin")
    public ResponseEntity<?> crearAdmin(
            @org.springframework.web.bind.annotation.RequestBody Usuario usuario) {
        try {
            Usuario creado = usuarioService.registrarAdmin(usuario);
            creado.setPassword(null); // no devolver password
            return ResponseEntity.ok(creado);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
package ProyectoLimpiFreshRest.LimpiFresh.Controller;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Contacto;
import ProyectoLimpiFreshRest.LimpiFresh.Service.ContactoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacto")
public class ContactoController {

    private final ContactoService contactoService;

    public ContactoController(ContactoService service) {
        this.contactoService = service;
    }

    @Operation(summary = "Enviar mensaje de contacto (público)")
    @ApiResponse(responseCode = "200", description = "Mensaje guardado correctamente",
            content = @Content(schema = @Schema(implementation = Contacto.class)))
    @PostMapping
    public ResponseEntity<Contacto> enviarMensaje(
            @RequestBody(
                    description = "Datos del mensaje de contacto (nombre, email, asunto, mensaje).",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Contacto.class))
            )
            @org.springframework.web.bind.annotation.RequestBody Contacto contacto) {
        Contacto guardado = contactoService.guardarMensaje(contacto);
        return ResponseEntity.ok(guardado);
    }

    @Operation(summary = "Listar todos los mensajes de contacto (admin)")
    @ApiResponse(responseCode = "200", description = "Listado de mensajes")
    @GetMapping
    public List<Contacto> listarMensajes() {
        return contactoService.listarTodos();
    }

    @Operation(summary = "Obtener un mensaje de contacto por ID (admin)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mensaje encontrado",
                    content = @Content(schema = @Schema(implementation = Contacto.class))),
            @ApiResponse(responseCode = "404", description = "Mensaje no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Contacto> obtenerMensaje(@PathVariable int id) {
        return contactoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMensaje(@PathVariable int id) {
        if (contactoService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        contactoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
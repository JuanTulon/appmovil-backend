package ProyectoLimpiFreshRest.LimpiFresh.Controller;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Producto;
import ProyectoLimpiFreshRest.LimpiFresh.Service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService service) {
        this.productoService = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // 1. Definir dónde guardar (debe coincidir con WebConfig)
            String uploadDir = "/home/ubuntu/uploads/"; 
            
            // 2. Generar nombre único para evitar colisiones
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            
            // 3. Guardar el archivo físico
            Path path = Paths.get(uploadDir + fileName);
            Files.createDirectories(path.getParent()); // Crea la carpeta si no existe
            Files.write(path, file.getBytes());
            
            // 4. Devolver la URL relativa que Android guardará en la BD
            // Ojo: Solo devolvemos la parte final de la URL
            String urlParaBD = "images/" + fileName; 
            
            return ResponseEntity.ok(urlParaBD);
            
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error al subir imagen: " + e.getMessage());
        }
    }

    @Operation(summary = "Listar todos los productos")
    @ApiResponse(responseCode = "200", description = "Listado de productos")
    @GetMapping
    public List<Producto> getTodos() {
        return productoService.listarTodos();
    }

    @Operation(summary = "Listar productos con oferta activa")
    @ApiResponse(responseCode = "200", description = "Productos en oferta")
    @GetMapping("/ofertas")
    public List<Producto> getOfertas() {
        return productoService.listarOfertas();
    }

    @Operation(summary = "Obtener producto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getPorId(
            @Parameter(description = "ID del producto", example = "1")
            @PathVariable int id) {
        return productoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo producto")
    @ApiResponse(responseCode = "200", description = "Producto creado")
    @PostMapping
    public ResponseEntity<Producto> crear(
            @RequestBody(
                    description = "Datos del producto a registrar",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Producto.class))
            ) @org.springframework.web.bind.annotation.RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.guardar(producto));
    }

    @Operation(summary = "Actualizar un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "ID no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(
            @Parameter(description = "ID del producto a modificar")
            @PathVariable int id, @org.springframework.web.bind.annotation.RequestBody Producto producto) {
        if (productoService.buscarPorId(id).isEmpty()) return ResponseEntity.notFound().build();
        producto.setId(id);
        return ResponseEntity.ok(productoService.guardar(producto));
    }

    @Operation(summary = "Eliminar un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "No existe el ID")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del producto", example = "3") @PathVariable int id) {
        if (productoService.buscarPorId(id).isEmpty()) return ResponseEntity.notFound().build();
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

package ProyectoLimpiFreshRest.LimpiFresh.Controller;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Blog;
import ProyectoLimpiFreshRest.LimpiFresh.Service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @Operation(summary = "Listar todos los blogs (público)")
    @ApiResponse(responseCode = "200", description = "Listado de blogs")
    @GetMapping
    public List<Blog> listarTodos() {
        return blogService.listarTodos();
    }

    @Operation(summary = "Obtener un blog por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Blog encontrado"),
            @ApiResponse(responseCode = "404", description = "No existe el blog")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Blog> obtenerPorId(@PathVariable int id) {
        return blogService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo blog (admin)")
    @ApiResponse(responseCode = "200", description = "Blog creado correctamente")
    @PostMapping
    public ResponseEntity<Blog> crear(@RequestBody Blog blog) {
        Blog creado = blogService.guardar(blog);
        return ResponseEntity.ok(creado);
    }

    @Operation(summary = "Actualizar un blog (admin)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Blog actualizado"),
            @ApiResponse(responseCode = "404", description = "Blog no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Blog> actualizar(@PathVariable int id, @RequestBody Blog blog) {
        if (blogService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        blog.setId(id);
        Blog actualizado = blogService.guardar(blog);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar un blog (admin)")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Blog eliminado"),
            @ApiResponse(responseCode = "404", description = "Blog no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        if (blogService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        blogService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}



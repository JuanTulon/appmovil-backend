package ProyectoLimpiFreshRest.LimpiFresh.Controller;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Boleta;
import ProyectoLimpiFreshRest.LimpiFresh.Service.BoletaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    private final BoletaService boletaService;

    public BoletaController(BoletaService service) {
        this.boletaService = service;
    }

    @PostMapping
    public ResponseEntity<Boleta> crear(@RequestBody Boleta boleta) {
        Boleta creada = boletaService.guardar(boleta);
        return ResponseEntity.ok(creada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boleta> getPorId(@PathVariable int id) {
        return boletaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<Boleta> getPorNumero(@PathVariable String numero) {
        Optional<Boleta> boleta = boletaService.buscarPorNumero(numero);
        return boleta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}

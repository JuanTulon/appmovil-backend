package ProyectoLimpiFreshRest.LimpiFresh.Service;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Boleta;
import ProyectoLimpiFreshRest.LimpiFresh.Repository.BoletaRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class BoletaService {

    private final BoletaRepository boletaRepository;

    public BoletaService(BoletaRepository boletaRepository) {
        this.boletaRepository = boletaRepository;
    }

    public Boleta guardar(Boleta boleta) {
        return boletaRepository.save(boleta);
    }

    public Optional<Boleta> buscarPorId(int id) {
        return boletaRepository.findById(id);
    }

    public Optional<Boleta> buscarPorNumero(String numero) {
        return boletaRepository.findByNumeroBoleta(numero);
    }
}
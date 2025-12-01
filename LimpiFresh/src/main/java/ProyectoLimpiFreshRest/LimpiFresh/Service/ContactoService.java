package ProyectoLimpiFreshRest.LimpiFresh.Service;

import ProyectoLimpiFreshRest.LimpiFresh.Modelo.Contacto;
import ProyectoLimpiFreshRest.LimpiFresh.Repository.ContactoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactoService {

    private final ContactoRepository contactoRepository;

    public ContactoService(ContactoRepository repo) {
        this.contactoRepository = repo;
    }

    public Contacto guardarMensaje(Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    public List<Contacto> listarTodos() {
        return contactoRepository.findAll();
    }

    public Optional<Contacto> buscarPorId(int id) {
        return contactoRepository.findById(id);
    }

    public void eliminar(int id) {contactoRepository.deleteById(id);
    }
}

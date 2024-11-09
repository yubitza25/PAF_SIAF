package sistemaEmpleados.services.imple;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import sistemaEmpleados.model.Permiso;
import sistemaEmpleados.repositories.PermisoRepository;
import sistemaEmpleados.services.PermisoService;

@Service
public class PermisoServiceImpl implements PermisoService {

    private final PermisoRepository permisoRepository;

    public PermisoServiceImpl(PermisoRepository permisoRepository) {
        this.permisoRepository = permisoRepository;
    }

    @Override
    public List<Permiso> findAll() {
        return permisoRepository.findAll();
    }

    @Override
    public Optional<Permiso> findById(Long id) {
        return permisoRepository.findById(id);
    }

    @Override
    public Optional<Permiso> findByFicha(String ficha) {
        return permisoRepository.findByFicha(ficha);
    }

    @Override
    public List<Permiso> findByFichaContaining(String ficha) {
        return permisoRepository.findByFichaContaining(ficha);
    }

    @Override
    public Permiso save(Permiso permiso) {
        return permisoRepository.save(permiso);
    }

    @Override
    public void delete(Long id) {
        if (permisoRepository.existsById(id)) {
            permisoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Permiso no encontrado con id: " + id);
        }
    }
}

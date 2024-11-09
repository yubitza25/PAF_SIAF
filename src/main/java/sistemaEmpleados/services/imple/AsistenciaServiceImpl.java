package sistemaEmpleados.services.imple;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sistemaEmpleados.model.Asistencia;
import sistemaEmpleados.repositories.AsistenciaRepository;
import sistemaEmpleados.services.AsistenciaService;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    public AsistenciaServiceImpl(AsistenciaRepository asistenciaRepository) {
        this.asistenciaRepository = asistenciaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Asistencia> findAll() {
        return asistenciaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Asistencia> findById(Long id) {
        return asistenciaRepository.findById(id);
    }

    @Override
    @Transactional
    public Asistencia save(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        asistenciaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Asistencia> findByIdWithEmpleado(Long id) {
        return asistenciaRepository.findByIdWithEmpleado(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Asistencia> findAllWithEmpleado() {
        return asistenciaRepository.findAllWithEmpleado();
    }
}

package sistemaEmpleados.services.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sistemaEmpleados.model.Empleado;
import sistemaEmpleados.repositories.EmpleadoRepository;
import sistemaEmpleados.services.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado findById(Long id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        return empleado.orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Empleado> findByDocumento(String documento) {
        return empleadoRepository.findByDocumento(documento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findByNombreContaining(String nombre) {
        return empleadoRepository.findByNombreContaining(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Empleado> findByAreaId(Long areaId) {
        return empleadoRepository.findByAreaId(areaId);
    }

    @Override
    @Transactional
    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        empleadoRepository.deleteById(id);
    }
}

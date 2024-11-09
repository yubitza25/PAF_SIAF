package sistemaEmpleados.services.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sistemaEmpleados.model.Area;
import sistemaEmpleados.repositories.AreaRepository;
import sistemaEmpleados.services.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
    
    private final AreaRepository areaRepository;

    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Area> findAll() {
        return areaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Area> findById(Long id) {
        return areaRepository.findById(id);  
    }

    // Cambiar el retorno para devolver un Optional
    @Override
    @Transactional(readOnly = true)
    public Optional<Area> findByNombre(String nombre) {
        return Optional.ofNullable(areaRepository.findByNombre(nombre));  
    }

    @Override
    @Transactional(readOnly = true)
    public List<Area> findByNombreContaining(String nombre) {
        return areaRepository.findByNombreContaining(nombre);
    }

    @Override
    @Transactional
    public Area save(Area area) {
        return areaRepository.save(area);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        areaRepository.deleteById(id);
    }
}

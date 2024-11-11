package sistemaEmpleados.services;

import java.util.List;
import java.util.Optional;

import sistemaEmpleados.model.Area;

public interface AreaService {
    List<Area> findAll();
    Optional<Area> findById(Long id);  
    Optional<Area> findByNombre(String nombre); 
    List<Area> findByNombreContaining(String nombre);  
    Area save(Area area);
    void delete(Long id);
}
package sistemaEmpleados.services;

import java.util.List;
import java.util.Optional;

import sistemaEmpleados.model.Asistencia;

public interface AsistenciaService {
    List<Asistencia> findAll();
    Optional<Asistencia> findById(Long id);
    Asistencia save(Asistencia asistencia);
    void delete(Long id);

    // Métodos específicos de Asistencia
    Optional<Asistencia> findByIdWithEmpleado(Long id); 
    List<Asistencia> findAllWithEmpleado(); 
}

package sistemaEmpleados.services;

import java.util.List;
import java.util.Optional;
import sistemaEmpleados.model.Empleado;

public interface EmpleadoService {
    List<Empleado> findAll();
    Empleado findById(Long id);
    Optional<Empleado> findByDocumento(String documento);
    List<Empleado> findByNombreContaining(String nombre);
    List<Empleado> findByAreaId(Long areaId);
    Empleado save(Empleado empleado);
    void delete(Long id);
}

package sistemaEmpleados.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import sistemaEmpleados.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    // Buscar un empleado por su documento (suponiendo que es único)
    Optional<Empleado> findByDocumento(String documento);

    // Buscar empleados por nombre 
    List<Empleado> findByNombreContaining(String nombre);

    // Buscar empleados por área 
    List<Empleado> findByAreaId(Long areaId);
}

package sistemaEmpleados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sistemaEmpleados.model.Asistencia;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    // Consulta para obtener una asistencia específica junto con el empleado asociado
    @Query("SELECT a FROM Asistencia a JOIN FETCH a.empleado WHERE a.id = :id")
    Optional<Asistencia> findByIdWithEmpleado(@Param("id") Long id);

    // Consulta para obtener todas las asistencias junto con los empleados asociados
    @Query("SELECT a FROM Asistencia a JOIN FETCH a.empleado")
    List<Asistencia> findAllWithEmpleado();
}

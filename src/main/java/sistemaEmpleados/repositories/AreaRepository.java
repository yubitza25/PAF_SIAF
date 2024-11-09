package sistemaEmpleados.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sistemaEmpleados.model.Area;

public interface AreaRepository extends JpaRepository<Area, Long>{
	Area findByNombre(String nombre);
    List<Area> findByNombreContaining(String nombre);
}


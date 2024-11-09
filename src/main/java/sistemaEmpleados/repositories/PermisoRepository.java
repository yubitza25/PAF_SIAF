package sistemaEmpleados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sistemaEmpleados.model.Permiso;
import java.util.List;
import java.util.Optional;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {
    Optional<Permiso> findByFicha(String ficha);
    List<Permiso> findByFichaContaining(String ficha);
}

package sistemaEmpleados.services;

import java.util.List;
import java.util.Optional;

import sistemaEmpleados.model.Permiso;

public interface PermisoService {
    
    List<Permiso> findAll();

    Optional<Permiso> findById(Long id);

    Optional<Permiso> findByFicha(String ficha);

    List<Permiso> findByFichaContaining(String ficha);

    Permiso save(Permiso permiso);

    void delete(Long id);
}

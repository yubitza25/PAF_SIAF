package sistemaEmpleados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistemaEmpleados.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsuario(String usuario);
    List<Usuario> findAll();
}
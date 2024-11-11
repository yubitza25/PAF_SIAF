package sistemaEmpleados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sistemaEmpleados.model.Usuario;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByUsuarioContaining(String usuario ,Pageable pageable);
    Optional<Usuario> findByUsuario(String usuario);
}
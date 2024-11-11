package sistemaEmpleados.services;

import org.springframework.data.domain.Pageable;
import sistemaEmpleados.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll(Pageable page);
    List<Usuario> findAll();
    List<Usuario> findByNombre(String usuario, Pageable page);
    Usuario findById(Long id);
    Usuario save(Usuario usuario);
    void delete(Long id);
}
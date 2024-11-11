package sistemaEmpleados.services.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sistemaEmpleados.exception.GeneralException;
import sistemaEmpleados.exception.NoDataFoundException;
import sistemaEmpleados.exception.ValidateException;
import sistemaEmpleados.model.Usuario;
import sistemaEmpleados.repositories.UsuarioRepository;
import sistemaEmpleados.services.UsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll(Pageable page) {
        try {
            List<Usuario> registros = repository.findAll(page).toList();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    public List<Usuario> findAll() {
        try {
            List<Usuario> registros = repository.findAll();
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findByNombre(String usuario, Pageable page) {
        try {
            List<Usuario> registros = repository.findByUsuarioContaining(usuario, page);
            return registros;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    public Usuario findById(Long id) {
        try {
            Usuario registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro con ese id"));
            return registro;
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        try {
            if (usuario.getId() == 0) {
                return repository.save(usuario); // Guardar nuevo usuario
            } else {
                Usuario registro = repository.findById(usuario.getId())
                        .orElseThrow(() -> new NoDataFoundException("No existe un registro con ese id"));
                registro.setUsuario(usuario.getUsuario());
                registro.setPassword(usuario.getPassword());
                registro.setTipoUsuario(usuario.getTipoUsuario());
                return repository.save(registro); // Guardar usuario actualizado
            }
        } catch (ValidateException | NoDataFoundException e) {
            throw e; // Re-lanzar excepciones de validación
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Usuario registro = repository.findById(id)
                    .orElseThrow(() -> new NoDataFoundException("No existe un registro con ese id"));
            repository.delete(registro);
        } catch (ValidateException | NoDataFoundException e) {
            throw e;
        } catch (GeneralException e) {
            throw new GeneralException("Error del servidor");
        }
    }
}
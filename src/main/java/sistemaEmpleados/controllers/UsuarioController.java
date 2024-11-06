package sistemaEmpleados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sistemaEmpleados.model.Usuario;
import sistemaEmpleados.repositories.UsuarioRepository;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private static final Logger logger = Logger.getLogger(UsuarioController.class.getName());

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<?> listarUsuarios() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            logger.warning("Intento de acceso no autorizado");
            return ResponseEntity.status(401).body("No autorizado: El token falta o es inválido");
        }
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            logger.info("No se encontraron usuarios");
            return ResponseEntity.status(404).body("No se encontraron usuarios");
        }
        logger.info("Usuarios encontrados: " + usuarios.size());
        return ResponseEntity.ok(usuarios);
    }
}
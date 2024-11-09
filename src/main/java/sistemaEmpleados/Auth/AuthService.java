package sistemaEmpleados.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;

import lombok.RequiredArgsConstructor;
import sistemaEmpleados.jwt.JwtService;
import sistemaEmpleados.model.Usuario;
import sistemaEmpleados.repositories.UsuarioRepository;

import java.security.SecureRandom;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        try {
            // 1. Buscar usuario
            Usuario user = userRepository.findByUsuario(request.getUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // 2. Verificar contraseña directamente
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return AuthResponse.builder()
                        .message("Contraseña incorrecta")
                        .token(null)
                        .build();
            }

            // 3. Generar token
            String token = jwtService.getToken(user);

            return AuthResponse.builder()
                    .message("Login exitoso")
                    .token(token)
                    .build();

        } catch (Exception e) {
            return AuthResponse.builder()
                    .message("Error en la autenticación: " + e.getMessage())
                    .token(null)
                    .build();
        }
    }

    public AuthResponse register(RegisterRequest request) {
        try {
            // Verificar si el usuario ya existe
            if (userRepository.findByUsuario(request.getUsuario()).isPresent()) {
                return AuthResponse.builder()
                        .message("El usuario ya existe")
                        .token(null)
                        .build();
            }

            // Hashear la contraseña directamente sin sal adicional
            String hashedPassword = passwordEncoder.encode(request.getPassword());

            Usuario user = Usuario.builder()
                    .usuario(request.getUsuario())
                    .password(hashedPassword)
                    .salt("salt")
                    .tipoUsuario(Usuario.TipoUsuario.valueOf(request.getTipoUsuario().toUpperCase()))
                    .build();

            userRepository.save(user);

            String token = jwtService.getToken(user);
            return AuthResponse.builder()
                    .message("Registro exitoso")
                    .token(token)
                    .build();
        } catch (Exception e) {
            return AuthResponse.builder()
                    .message("Error en el registro: " + e.getMessage())
                    .token(null)
                    .build();
        }
    }
}
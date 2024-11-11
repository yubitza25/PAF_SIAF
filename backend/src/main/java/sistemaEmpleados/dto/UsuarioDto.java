package sistemaEmpleados.dto;
import lombok.*;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class UsuarioDto {
    private Long id;
    private String usuario;
    private String password;
    private String tipoUsuario;

}

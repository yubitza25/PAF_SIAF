// RegisterRequest.java
package sistemaEmpleados.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String usuario;
    private String password;
    private String tipoUsuario;
}
package sistemaEmpleados.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String message;
    private String token;

    public AuthResponse(String message) {
        this.message = message;
    }
}
package sistemaEmpleados.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermisoDto {
    private Long id;
    private String ficha;
    private Date fechaInicio;
    private Date fechaFin;
    private String motivo;
    private Long empleadoId;       
    private String empleadoNombre; 
}

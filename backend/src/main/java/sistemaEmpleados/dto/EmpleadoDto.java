package sistemaEmpleados.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoDto {
    private Long id;
    private String nombre;
    private String documento;
    private String telefono;
    private String direccion;
    private Long areaId; 
    private String areaNombre;
}

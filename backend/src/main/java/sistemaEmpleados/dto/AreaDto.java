package sistemaEmpleados.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AreaDto {
    private Long id;
    private String nombre;
    private String descripcion;
}

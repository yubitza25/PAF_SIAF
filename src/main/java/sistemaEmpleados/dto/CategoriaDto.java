package sistemaEmpleados.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaDto {
    private Long id;
    private String nombre;
    private Boolean activo;  
}

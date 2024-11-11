package sistemaEmpleados.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AsistenciaDto {
    private Long id;
    private Date fechaHoraEntrada;
    private Date fechaHoraSalida;
    private String observacion;
    private Long empleadoId; 
    private String empleadoNombre; 
}

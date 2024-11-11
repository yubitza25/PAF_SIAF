package sistemaEmpleados.converter;

import org.springframework.stereotype.Component;
import sistemaEmpleados.dto.PermisoDto;
import sistemaEmpleados.model.Permiso;
import sistemaEmpleados.model.Empleado;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermisoConverter extends AbstractConverter<Permiso, PermisoDto> {

    @Override
    public PermisoDto fromEntity(Permiso permiso) {
        if (permiso == null) {
            return null;
        }

        return PermisoDto.builder()
                .id(permiso.getId())
                .ficha(permiso.getFicha())
                .fechaInicio(permiso.getFechaInicio())
                .fechaFin(permiso.getFechaFin())
                .motivo(permiso.getMotivo())
                .empleadoId(permiso.getEmpleado().getId())
                .empleadoNombre(permiso.getEmpleado().getNombre())
                .build();
    }

    @Override
    public Permiso fromDTO(PermisoDto permisoDto) {
        if (permisoDto == null) {
            return null;
        }

        Permiso permiso = new Permiso();
        permiso.setFicha(permisoDto.getFicha());
        permiso.setFechaInicio(permisoDto.getFechaInicio());
        permiso.setFechaFin(permisoDto.getFechaFin());
        permiso.setMotivo(permisoDto.getMotivo());

        if (permisoDto.getEmpleadoId() != null) {
            Empleado empleado = new Empleado();
            empleado.setId(permisoDto.getEmpleadoId());
            permiso.setEmpleado(empleado);
        }
        return permiso;
    }

    @Override
    public List<PermisoDto> fromEntities(List<Permiso> permisos) {
        return permisos.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

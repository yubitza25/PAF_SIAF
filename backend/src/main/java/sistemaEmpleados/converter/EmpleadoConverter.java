package sistemaEmpleados.converter;

import org.springframework.stereotype.Component;
import sistemaEmpleados.dto.EmpleadoDto;
import sistemaEmpleados.model.Area;
import sistemaEmpleados.model.Empleado;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmpleadoConverter extends AbstractConverter<Empleado, EmpleadoDto> {

    @Override
    public EmpleadoDto fromEntity(Empleado empleado) {
        if (empleado == null) {
            return null;
        }

        return EmpleadoDto.builder()
                .id(empleado.getId())
                .nombre(empleado.getNombre())
                .documento(empleado.getDocumento())
                .telefono(empleado.getTelefono())
                .direccion(empleado.getDireccion())
                .areaId(empleado.getArea().getId())
                .areaNombre(empleado.getArea().getNombre())
                .build();
    }

    @Override
    public Empleado fromDTO(EmpleadoDto empleadoDto) {
        if (empleadoDto == null) {
            return null;
        }

        Empleado empleado = new Empleado();
        empleado.setNombre(empleadoDto.getNombre());
        empleado.setDocumento(empleadoDto.getDocumento());
        empleado.setTelefono(empleadoDto.getTelefono());
        empleado.setDireccion(empleadoDto.getDireccion());

        if (empleadoDto.getAreaId() != null) {
            Area area = new Area();
            area.setId(empleadoDto.getAreaId());
            empleado.setArea(area);
        }
        return empleado;
    }

    @Override
    public List<EmpleadoDto> fromEntities(List<Empleado> empleados) {
        return empleados.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

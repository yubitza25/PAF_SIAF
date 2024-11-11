package sistemaEmpleados.converter;

import org.springframework.stereotype.Component;
import sistemaEmpleados.dto.AsistenciaDto;
import sistemaEmpleados.model.Asistencia;
import sistemaEmpleados.model.Empleado;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AsistenciaConverter extends AbstractConverter<Asistencia, AsistenciaDto> {

    @Override
    public AsistenciaDto fromEntity(Asistencia asistencia) {
        if (asistencia == null) {
            return null;
        }

        return AsistenciaDto.builder()
                .id(asistencia.getId())
                .fechaHoraEntrada(asistencia.getFechaHoraEntrada())
                .fechaHoraSalida(asistencia.getFechaHoraSalida())
                .observacion(asistencia.getObservacion())
                .empleadoId(asistencia.getEmpleado().getId())
                .empleadoNombre(asistencia.getEmpleado().getNombre())
                .build();
    }

    @Override
    public Asistencia fromDTO(AsistenciaDto asistenciaDto) {
        if (asistenciaDto == null) {
            return null;
        }

        Asistencia asistencia = new Asistencia();
        asistencia.setFechaHoraEntrada(asistenciaDto.getFechaHoraEntrada());
        asistencia.setFechaHoraSalida(asistenciaDto.getFechaHoraSalida());
        asistencia.setObservacion(asistenciaDto.getObservacion());

        if (asistenciaDto.getEmpleadoId() != null) {
            Empleado empleado = new Empleado();
            empleado.setId(asistenciaDto.getEmpleadoId());
            asistencia.setEmpleado(empleado);
        }
        return asistencia;
    }

    @Override
    public List<AsistenciaDto> fromEntities(List<Asistencia> asistencias) {
        return asistencias.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}

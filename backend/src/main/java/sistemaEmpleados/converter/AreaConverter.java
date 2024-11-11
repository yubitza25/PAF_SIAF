package sistemaEmpleados.converter;

import org.springframework.stereotype.Component;
import sistemaEmpleados.dto.AreaDto;
import sistemaEmpleados.model.Area;

@Component
public class AreaConverter extends AbstractConverter<Area, AreaDto> {

    @Override
    public AreaDto fromEntity(Area area) {
        if (area == null) {
            return null;
        }

        return AreaDto.builder()
                .id(area.getId())
                .nombre(area.getNombre())
                .descripcion(area.getDescripcion())
                .build();
    }

    @Override
    public Area fromDTO(AreaDto areaDto) {
        if (areaDto == null) {
            return null;
        }

        Area area = new Area();
        area.setId(areaDto.getId());
        area.setNombre(areaDto.getNombre());
        area.setDescripcion(areaDto.getDescripcion());

        return area;
    }
}

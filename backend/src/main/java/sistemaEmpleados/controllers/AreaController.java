package sistemaEmpleados.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemaEmpleados.converter.AreaConverter;
import sistemaEmpleados.dto.AreaDto;
import sistemaEmpleados.model.Area;
import sistemaEmpleados.services.AreaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/areas")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @Autowired
    private AreaConverter areaConverter;

    @GetMapping
    public ResponseEntity<List<AreaDto>> findAll() {
        List<AreaDto> areas = areaConverter.fromEntities(areaService.findAll());
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaDto> findById(@PathVariable Long id) {
        Optional<Area> areaOpt = areaService.findById(id);
        if (areaOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        AreaDto areaDto = areaConverter.fromEntity(areaOpt.get());
        return new ResponseEntity<>(areaDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AreaDto> create(@RequestBody AreaDto areaDto) {
        Area area = areaConverter.fromDTO(areaDto);
        Area savedArea = areaService.save(area);
        return new ResponseEntity<>(areaConverter.fromEntity(savedArea), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaDto> update(@PathVariable Long id, @RequestBody AreaDto areaDto) {
        Optional<Area> areaOpt = areaService.findById(id);
        if (areaOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Area area = areaOpt.get();
        area.setNombre(areaDto.getNombre());
        area.setDescripcion(areaDto.getDescripcion());
        Area updatedArea = areaService.save(area);
        return new ResponseEntity<>(areaConverter.fromEntity(updatedArea), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (areaService.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        areaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

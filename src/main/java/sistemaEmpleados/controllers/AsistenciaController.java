package sistemaEmpleados.controllers;

import sistemaEmpleados.dto.AsistenciaDto;
import sistemaEmpleados.services.*;
import sistemaEmpleados.converter.AsistenciaConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/asistencias")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;
    private final AsistenciaConverter asistenciaConverter;

    public AsistenciaController(AsistenciaService asistenciaService, AsistenciaConverter asistenciaConverter) {
        this.asistenciaService = asistenciaService;
        this.asistenciaConverter = asistenciaConverter;
    }

    // Obtener todas las asistencias
    @GetMapping
    public ResponseEntity<List<AsistenciaDto>> getAllAsistencias() {
        List<AsistenciaDto> asistencias = asistenciaConverter.fromEntities(asistenciaService.findAll());
        return ResponseEntity.ok(asistencias);
    }

    // Obtener una asistencia por ID
    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaDto> getAsistenciaById(@PathVariable Long id) {
        return asistenciaService.findById(id)
                .map(asistencia -> ResponseEntity.ok(asistenciaConverter.fromEntity(asistencia)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva asistencia
    @PostMapping
    public ResponseEntity<AsistenciaDto> createAsistencia(@RequestBody AsistenciaDto asistenciaDto) {
        AsistenciaDto createdAsistenciaDto = asistenciaConverter.fromEntity(
                asistenciaService.save(asistenciaConverter.fromDTO(asistenciaDto))
        );
        return ResponseEntity.ok(createdAsistenciaDto);
    }

    // Actualizar una asistencia existente
    @PutMapping("/{id}")
    public ResponseEntity<AsistenciaDto> updateAsistencia(
            @PathVariable Long id, 
            @RequestBody AsistenciaDto asistenciaDto) {
        
        return asistenciaService.findById(id).map(existingAsistencia -> {
            asistenciaDto.setId(id); // asegura que el ID en el DTO coincide
            AsistenciaDto updatedAsistenciaDto = asistenciaConverter.fromEntity(
                    asistenciaService.save(asistenciaConverter.fromDTO(asistenciaDto))
            );
            return ResponseEntity.ok(updatedAsistenciaDto);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una asistencia por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Long
    		id) {
        if (asistenciaService.findById(id).isPresent()) {
            asistenciaService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

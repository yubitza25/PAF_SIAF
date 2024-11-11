package sistemaEmpleados.controllers;

import sistemaEmpleados.dto.PermisoDto;
import sistemaEmpleados.services.PermisoService;
import sistemaEmpleados.converter.PermisoConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permisos")
public class PermisoController {

    private final PermisoService permisoService;
    private final PermisoConverter permisoConverter;

    public PermisoController(PermisoService permisoService, PermisoConverter permisoConverter) {
        this.permisoService = permisoService;
        this.permisoConverter = permisoConverter;
    }

    // Obtener todos los permisos
    @GetMapping
    public ResponseEntity<List<PermisoDto>> getAllPermisos() {
        List<PermisoDto> permisos = permisoConverter.fromEntities(permisoService.findAll());
        return ResponseEntity.ok(permisos);
    }

    // Obtener un permiso por ID
    @GetMapping("/{id}")
    public ResponseEntity<PermisoDto> getPermisoById(@PathVariable Long id) {
        return permisoService.findById(id)
                .map(permiso -> ResponseEntity.ok(permisoConverter.fromEntity(permiso)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo permiso
    @PostMapping
    public ResponseEntity<PermisoDto> createPermiso(@RequestBody PermisoDto permisoDto) {
        PermisoDto createdPermisoDto = permisoConverter.fromEntity(
                permisoService.save(permisoConverter.fromDTO(permisoDto))
        );
        return ResponseEntity.ok(createdPermisoDto);
    }

    // Actualizar un permiso existente
    @PutMapping("/{id}")
    public ResponseEntity<PermisoDto> updatePermiso(
            @PathVariable Long id,
            @RequestBody PermisoDto permisoDto) {

        return permisoService.findById(id).map(existingPermiso -> {
            permisoDto.setId(id); // asegura que el ID en el DTO coincide
            PermisoDto updatedPermisoDto = permisoConverter.fromEntity(
                    permisoService.save(permisoConverter.fromDTO(permisoDto))
            );
            return ResponseEntity.ok(updatedPermisoDto);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un permiso por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermiso(@PathVariable Long id) {
        if (permisoService.findById(id).isPresent()) {
            permisoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

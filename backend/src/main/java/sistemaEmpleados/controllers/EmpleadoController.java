package sistemaEmpleados.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemaEmpleados.converter.EmpleadoConverter;
import sistemaEmpleados.dto.EmpleadoDto;
import sistemaEmpleados.exception.ValidateException;
import sistemaEmpleados.model.Area;
import sistemaEmpleados.model.Empleado;
import sistemaEmpleados.services.AreaService;
import sistemaEmpleados.services.EmpleadoService;
import sistemaEmpleados.validator.EmpleadoValidator;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;
    private final AreaService areaService;
    private final EmpleadoConverter empleadoConverter;

    public EmpleadoController(EmpleadoService empleadoService, AreaService areaService, EmpleadoConverter empleadoConverter) {
        this.empleadoService = empleadoService;
        this.areaService = areaService;
        this.empleadoConverter = empleadoConverter;
    }

    // Obtener todos los empleados
    @GetMapping
    public ResponseEntity<List<EmpleadoDto>> obtenerTodosLosEmpleados() {
        List<EmpleadoDto> empleados = empleadoConverter.fromEntities(empleadoService.findAll());
        return ResponseEntity.ok(empleados);
    }

    // Obtener un empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDto> obtenerEmpleadoPorId(@PathVariable Long id) {
        Empleado empleado = empleadoService.findById(id);
        if (empleado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleadoConverter.fromEntity(empleado));
    }

    // Crear un empleado
    @PostMapping
    public ResponseEntity<?> crearEmpleado(@RequestBody EmpleadoDto empleadoDto) {
        try {
            EmpleadoValidator.save(empleadoConverter.fromDTO(empleadoDto)); // Validación inicial

            // Validar que el areaId existe
            Optional<Area> area = areaService.findById(empleadoDto.getAreaId());
            if (!area.isPresent()) {
                return ResponseEntity.badRequest().body("El área especificada no existe.");
            }

            Empleado empleado = empleadoConverter.fromDTO(empleadoDto);
            empleado.setArea(area.get()); // Asignar el área encontrada
            Empleado empleadoCreado = empleadoService.save(empleado);

            return ResponseEntity.status(HttpStatus.CREATED).body(empleadoConverter.fromEntity(empleadoCreado));
        } catch (ValidateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

 // Método para actualizar un empleado
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEmpleado(@PathVariable Long id, @RequestBody EmpleadoDto empleadoDto) {
        Empleado empleadoExistente = empleadoService.findById(id);
        if (empleadoExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El empleado especificado no existe.");
        }

        try {
            // Validar los datos de empleadoDto
            EmpleadoValidator.save(empleadoConverter.fromDTO(empleadoDto));

            // Validar que el areaId exista
            Optional<Area> areaOptional = areaService.findById(empleadoDto.getAreaId());
            if (!areaOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El área especificada no existe.");
            }

            // Actualizar los datos del empleado
            Empleado empleadoActualizado = empleadoConverter.fromDTO(empleadoDto);
            empleadoActualizado.setId(id); // Mantener el ID del empleado existente
            empleadoActualizado.setArea(areaOptional.get()); // Asignar el área válida encontrada

            // Guardar el empleado actualizado
            Empleado empleadoGuardado = empleadoService.save(empleadoActualizado);

            return ResponseEntity.ok(empleadoConverter.fromEntity(empleadoGuardado));
        } catch (ValidateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    // Eliminar un empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Long id) {
        Empleado empleadoExistente = empleadoService.findById(id);
        if (empleadoExistente == null) {
            return ResponseEntity.notFound().build();
        }
        empleadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

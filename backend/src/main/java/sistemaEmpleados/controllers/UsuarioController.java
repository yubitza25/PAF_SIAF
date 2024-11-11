package sistemaEmpleados.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistemaEmpleados.converter.UsuarioConverter;
import sistemaEmpleados.dto.UsuarioDto;
import sistemaEmpleados.model.Usuario;
import sistemaEmpleados.services.UsuarioService;
import sistemaEmpleados.util.WrapperResponse;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioConverter converter;
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<UsuarioDto> usuario = converter.fromEntities(service.findAll());
        return new WrapperResponse(true, "success", usuario).createResponse(HttpStatus.OK);
    }




    @PostMapping
    public ResponseEntity<UsuarioDto> create (@RequestBody UsuarioDto usuario) {
        Usuario entity = converter.fromDTO(usuario);
        UsuarioDto dto = converter.fromEntity(service.save(entity));//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> update (@PathVariable("id") int id, @RequestBody UsuarioDto usuario) {
        Usuario entity = converter.fromDTO(usuario);
        UsuarioDto dto = converter.fromEntity(service.save(entity));
//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable("id") Long id) {
        service.delete(id);
//        return ResponseEntity.ok(null);
        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findById (@PathVariable("id") Long id) {
        UsuarioDto dto = converter.fromEntity(service.findById(id));

//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }
}
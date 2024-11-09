package sistemaEmpleados.converter;

import org.springframework.stereotype.Component;
import sistemaEmpleados.dto.UsuarioDto;
import sistemaEmpleados.model.Usuario;

@Component
public class UsuarioConverter extends AbstractConverter<Usuario, UsuarioDto> {

    @Override
    public UsuarioDto fromEntity(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return UsuarioDto.builder()
                .id(usuario.getId())
                .usuario(usuario.getUsuario())
                .password(usuario.getPassword())
                .build();
    }

    @Override
    public Usuario fromDTO(UsuarioDto usuarioDto) {
        if (usuarioDto == null) {
            return null;
        }

        return Usuario.builder()
                .id(usuarioDto.getId())
                .usuario(usuarioDto.getUsuario())
                .password(usuarioDto.getPassword())
                .build();
    }
}
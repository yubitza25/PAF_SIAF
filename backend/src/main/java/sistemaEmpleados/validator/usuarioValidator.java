package sistemaEmpleados.validator;
import sistemaEmpleados.model.Usuario;
import sistemaEmpleados.exception.ValidateException;
public class usuarioValidator {
    public static void save (Usuario registro){
        if(registro.getUsername().isEmpty()){
            throw new ValidateException("El nombre no puede estar vacio");
        }
        if(registro.getPassword().isEmpty()){
            throw new ValidateException("La contraseña no puede estar vacia");
        }
    }
}

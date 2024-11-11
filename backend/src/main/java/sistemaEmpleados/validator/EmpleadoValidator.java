package sistemaEmpleados.validator;

import sistemaEmpleados.model.Empleado;
import sistemaEmpleados.exception.ValidateException;

public class EmpleadoValidator {
    
    public static void save(Empleado empleado) {
        
        if (empleado.getNombre() == null || empleado.getNombre().isEmpty()) {
            throw new ValidateException("El nombre del empleado no puede estar vacío.");
        }

        if (empleado.getNombre().length() > 100) {
            throw new ValidateException("El nombre del empleado no puede tener más de 100 caracteres.");
        }

        if (empleado.getDocumento() == null || empleado.getDocumento().isEmpty()) {
            throw new ValidateException("El documento del empleado no puede estar vacío.");
        }

        if (empleado.getDocumento().length() > 15) {
            throw new ValidateException("El documento del empleado no puede tener más de 15 caracteres.");
        }

        if (empleado.getTelefono() != null && empleado.getTelefono().length() > 15) {
            throw new ValidateException("El teléfono del empleado no puede tener más de 15 caracteres.");
        }

        if (empleado.getDireccion() != null && empleado.getDireccion().length() > 100) {
            throw new ValidateException("La dirección del empleado no puede tener más de 100 caracteres.");
        }

        if (empleado.getArea() == null) {
            throw new ValidateException("El área del empleado no puede ser nula.");
        }
        
    }
}

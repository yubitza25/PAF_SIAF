package sistemaEmpleados.validator;

import sistemaEmpleados.model.Area;
import sistemaEmpleados.exception.ValidateException;

public class AreaValidator {

    // Métodos para validar los campos de area
	
    public static void save(Area area) {
    	
        if (area.getNombre() == null || area.getNombre().isEmpty()) {
            throw new ValidateException("El nombre del área no puede estar vacío.");
        }

        if (area.getDescripcion() == null || area.getDescripcion().isEmpty()) {
            throw new ValidateException("La descripción del área no puede estar vacía.");
        }

        if (area.getNombre().length() > 100) {
            throw new ValidateException("El nombre del área no puede tener más de 100 caracteres.");
        }

        if (area.getDescripcion().length() > 255) {
            throw new ValidateException("La descripción del área no puede tener más de 255 caracteres.");
        }
    }
}

package sistemaEmpleados.validator;

import sistemaEmpleados.model.Permiso;
import sistemaEmpleados.exception.ValidateException;
import java.util.Date;

public class PermisoValidator {

    public static void save(Permiso permiso) {
        
        if (permiso.getFechaInicio() == null) {
            throw new ValidateException("La fecha de inicio del permiso no puede estar vacía.");
        }

        if (permiso.getFechaFin() == null) {
            throw new ValidateException("La fecha de fin del permiso no puede estar vacía.");
        }

        if (permiso.getFechaInicio().after(permiso.getFechaFin())) {
            throw new ValidateException("La fecha de inicio no puede ser posterior a la fecha de fin.");
        }

        if (permiso.getEmpleado() == null) {
            throw new ValidateException("El empleado debe estar asociado al permiso.");
        }

        if (permiso.getEmpleado().getId() == null) {
            throw new ValidateException("El empleado asociado debe tener un ID válido.");
        }

        if (permiso.getMotivo() != null && permiso.getMotivo().length() > 200) {
            throw new ValidateException("El motivo del permiso no puede tener más de 200 caracteres.");
        }

        Date currentDate = new Date();
        if (permiso.getFechaInicio().after(currentDate)) {
            throw new ValidateException("La fecha de inicio no puede ser en el futuro.");
        }

        if (permiso.getFechaFin().after(currentDate)) {
            throw new ValidateException("La fecha de fin no puede ser en el futuro.");
        }

        if (permiso.getCreatedAt() == null) {
            throw new ValidateException("La fecha de creación no puede estar vacía.");
        }

        if (permiso.getUpdatedAt() == null) {
            throw new ValidateException("La fecha de actualización no puede estar vacía.");
        }
    }
}

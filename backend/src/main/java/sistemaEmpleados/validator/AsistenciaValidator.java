package sistemaEmpleados.validator;

import sistemaEmpleados.model.Asistencia;
import sistemaEmpleados.exception.ValidateException;
import java.util.Date;

public class AsistenciaValidator {

    public static void save(Asistencia asistencia) {
        
        if (asistencia.getFechaHoraEntrada() == null) {
            throw new ValidateException("La fecha y hora de entrada no puede estar vacía.");
        }

        if (asistencia.getFechaHoraSalida() == null) {
            throw new ValidateException("La fecha y hora de salida no puede estar vacía.");
        }

        if (asistencia.getFechaHoraEntrada().after(asistencia.getFechaHoraSalida())) {
            throw new ValidateException("La fecha y hora de entrada no puede ser posterior a la fecha y hora de salida.");
        }

        if (asistencia.getEmpleado() == null) {
            throw new ValidateException("El empleado debe estar asociado a la asistencia.");
        }

        if (asistencia.getEmpleado().getId() == null) {
            throw new ValidateException("El empleado asociado debe tener un ID válido.");
        }

        if (asistencia.getObservacion() != null && asistencia.getObservacion().length() > 100) {
            throw new ValidateException("La observación no puede tener más de 100 caracteres.");
        }

        if (asistencia.getCreatedAt() == null) {
            throw new ValidateException("La fecha de creación no puede estar vacía.");
        }

        if (asistencia.getUpdatedAt() == null) {
            throw new ValidateException("La fecha de actualización no puede estar vacía.");
        }

        Date currentDate = new Date();
        if (asistencia.getFechaHoraEntrada().after(currentDate)) {
            throw new ValidateException("La fecha de entrada no puede ser en el futuro.");
        }

        if (asistencia.getFechaHoraSalida().after(currentDate)) {
            throw new ValidateException("La fecha de salida no puede ser en el futuro.");
        }
    }
}

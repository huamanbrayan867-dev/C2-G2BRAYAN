package pe.edu.upeu.asistencia.modelo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Participante {
    private StringProperty Correo;
    private StringProperty nombre;
    private StringProperty apellidos;
    private BooleanProperty estado;



}

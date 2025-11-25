package pe.edu.upeu.GestorOdontologico.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paciente;
    private String odontologo;
    private String fecha;  // formato yyyy-MM-dd
    private String hora;   // formato HH:mm

    public Cita(String paciente, String odontologo, String fecha, String hora) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fecha = fecha;
        this.hora = hora;
    }
}

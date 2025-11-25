package pe.edu.upeu.GestorOdontologico.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    private String telefono;
    private String email;

    @Column(length = 2000)
    private String historialMedico;
}

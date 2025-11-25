package pe.edu.upeu.GestorOdontologico.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(unique = true, nullable = false)
    private String colegiado; // número de colegiado o identificación profesional

    private String especialidad;
    private String telefono;
    private String email;

    @Column(length = 2000)
    private String observaciones;
}

package pe.edu.upeu.GestorOdontologico.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.GestorOdontologico.model.Cita;
import pe.edu.upeu.GestorOdontologico.repository.CitaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;

    // Lista preliminar para pruebas (simula pacientes)
    public List<String> obtenerPacientes() {
        return List.of("Juan Pérez", "Ana Torres", "Carlos Ramos");
    }

    // Lista preliminar de odontólogos
    public List<String> obtenerOdontologos() {
        return List.of("Dr. Luis Molina", "Dra. María Ruiz");
    }

    // Registrar una cita
    public void registrarCita(String paciente, String odontologo, LocalDate fecha, String hora) {
        Cita cita = new Cita(
                paciente,
                odontologo,
                fecha.toString(),
                hora
        );
        citaRepository.save(cita);
    }

    // Listar citas
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }
}

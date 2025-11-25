package pe.edu.upeu.GestorOdontologico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.GestorOdontologico.model.Patient;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByDni(String dni);
}

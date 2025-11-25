package pe.edu.upeu.GestorOdontologico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.GestorOdontologico.model.Dentist;
import java.util.Optional;
import java.util.List;

public interface DentistRepository extends JpaRepository<Dentist, Long> {
    Optional<Dentist> findByColegiado(String colegiado);
    List<Dentist> findByApellidosContainingIgnoreCase(String apellidos);
}

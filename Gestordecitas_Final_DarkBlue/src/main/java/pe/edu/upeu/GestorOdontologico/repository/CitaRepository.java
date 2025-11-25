package pe.edu.upeu.GestorOdontologico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.GestorOdontologico.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

}

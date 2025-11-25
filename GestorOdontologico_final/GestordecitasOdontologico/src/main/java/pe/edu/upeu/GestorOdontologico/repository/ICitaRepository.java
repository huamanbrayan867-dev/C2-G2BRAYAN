package pe.edu.upeu.GestorOdontologico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.model.Cita;

public interface ICitaRepository extends JpaRepository<Cita, Long> {}

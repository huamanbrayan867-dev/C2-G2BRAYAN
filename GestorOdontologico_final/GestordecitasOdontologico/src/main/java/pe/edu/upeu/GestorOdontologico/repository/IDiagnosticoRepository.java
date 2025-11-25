
package pe.edu.upeu.GestorOdontologico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.GestorOdontologico.model.Diagnostico;

public interface IDiagnosticoRepository extends JpaRepository<Diagnostico, Long> {}

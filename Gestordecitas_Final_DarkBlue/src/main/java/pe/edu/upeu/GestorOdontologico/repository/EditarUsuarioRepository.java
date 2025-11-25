package pe.edu.upeu.GestorOdontologico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.GestorOdontologico.model.EditarUsuario;

import java.util.List;

public interface EditarUsuarioRepository extends JpaRepository<EditarUsuario, Long> {

    List<EditarUsuario> findByUserContainingIgnoreCase(String user);

}

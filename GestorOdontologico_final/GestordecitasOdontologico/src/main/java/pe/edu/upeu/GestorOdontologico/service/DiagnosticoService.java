
package pe.edu.upeu.GestorOdontologico.service;

import java.util.List;
import pe.edu.upeu.GestorOdontologico.model.Diagnostico;

public interface DiagnosticoService {
    Diagnostico save(Diagnostico d);
    List<Diagnostico> findAll();
    void deleteById(Long id);
}

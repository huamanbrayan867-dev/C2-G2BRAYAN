
package pe.edu.upeu.GestorOdontologico.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import pe.edu.upeu.GestorOdontologico.model.Diagnostico;
import pe.edu.upeu.GestorOdontologico.repository.IDiagnosticoRepository;
import pe.edu.upeu.GestorOdontologico.service.DiagnosticoService;

@Service
public class DiagnosticoServiceImpl implements DiagnosticoService {

    @Autowired
    private IDiagnosticoRepository repo;

    @Override
    public Diagnostico save(Diagnostico d) {
        return repo.save(d);
    }

    @Override
    public List<Diagnostico> findAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}

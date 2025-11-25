package pe.edu.upeu.GestorOdontologico.service.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import pe.model.Cita;
import pe.repository.ICitaRepository;
import pe.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private ICitaRepository repo;

    @Override
    public Cita save(Cita c) {
        return repo.save(c);
    }

    @Override
    public List<Cita> findAll() {
        return repo.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}

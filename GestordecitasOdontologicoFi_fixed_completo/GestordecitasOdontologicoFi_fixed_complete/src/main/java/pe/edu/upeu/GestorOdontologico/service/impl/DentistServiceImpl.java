package pe.edu.upeu.GestorOdontologico.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.GestorOdontologico.model.Dentist;
import pe.edu.upeu.GestorOdontologico.repository.DentistRepository;
import pe.edu.upeu.GestorOdontologico.service.IDentistService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DentistServiceImpl implements IDentistService {

    private final DentistRepository repository;

    @Override
    public Dentist save(Dentist d) {
        return repository.save(d);
    }

    @Override
    public Dentist update(Dentist d, Long id) {
        Dentist exist = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Odontólogo no encontrado"));
        d.setId(id);
        return repository.save(d);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Dentist findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Dentist> findAll() {
        return repository.findAll();
    }

    @Override
    public Dentist findByColegiado(String colegiado) {
        return repository.findByColegiado(colegiado).orElse(null);
    }

    @Override
    public List<Dentist> searchByLastName(String apellidos) {
        return repository.findByApellidosContainingIgnoreCase(apellidos);
    }
}

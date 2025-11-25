package pe.edu.upeu.GestorOdontologico.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.GestorOdontologico.model.Patient;
import pe.edu.upeu.GestorOdontologico.repository.PatientRepository;
import pe.edu.upeu.GestorOdontologico.service.IPatientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements IPatientService {

    private final PatientRepository repository;

    @Override
    public Patient save(Patient p) {
        return repository.save(p);
    }

    @Override
    public Patient update(Patient p, Long id) {
        Patient exist = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        p.setId(id);
        return repository.save(p);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Patient findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Patient> findAll() {
        return repository.findAll();
    }
}

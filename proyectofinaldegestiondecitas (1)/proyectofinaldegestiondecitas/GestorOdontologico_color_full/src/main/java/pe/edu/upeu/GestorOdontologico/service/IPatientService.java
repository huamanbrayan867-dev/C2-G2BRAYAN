package pe.edu.upeu.GestorOdontologico.service;

import pe.edu.upeu.GestorOdontologico.model.Patient;
import java.util.List;

public interface IPatientService {
    Patient save(Patient p);
    Patient update(Patient p, Long id);
    void delete(Long id);
    Patient findById(Long id);
    List<Patient> findAll();
}

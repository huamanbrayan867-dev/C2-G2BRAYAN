package pe.edu.upeu.GestorOdontologico.service;

import pe.edu.upeu.GestorOdontologico.model.Dentist;
import java.util.List;

public interface IDentistService {
    Dentist save(Dentist d);
    Dentist update(Dentist d, Long id);
    void delete(Long id);
    Dentist findById(Long id);
    List<Dentist> findAll();
    Dentist findByColegiado(String colegiado);
    List<Dentist> searchByLastName(String apellidos);
}

package pe.edu.upeu.GestorOdontologico.service;

import java.util.List;
import pe.model.Cita;

public interface CitaService {
    Cita save(Cita c);
    List<Cita> findAll();
    void deleteById(Long id);
}

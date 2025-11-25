package pe.edu.upeu.GestorOdontologico.service;

import pe.edu.upeu.GestorOdontologico.model.EditarUsuario;
import java.util.List;

public interface IEditarUsuarioService {
    void save(EditarUsuario editarUsuario); //C

    List<EditarUsuario> findAll(); // R

    EditarUsuario update(EditarUsuario editarUsuario);//U

    void delete(Long usuario); //D

    EditarUsuario findById(Long usuario); //Buscar
    List<EditarUsuario> findByUserContainingIgnoreCase(String user);




}

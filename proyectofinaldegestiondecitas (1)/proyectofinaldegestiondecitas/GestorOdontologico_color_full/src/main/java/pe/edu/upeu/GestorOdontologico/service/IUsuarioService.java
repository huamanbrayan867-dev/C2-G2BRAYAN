package pe.edu.upeu.GestorOdontologico.service;

import pe.edu.upeu.GestorOdontologico.model.Usuario;

public interface IUsuarioService extends ICrudGenericoService<Usuario,Long>{
    Usuario loginUsuario(String user, String clave);

}
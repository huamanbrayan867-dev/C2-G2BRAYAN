package pe.edu.upeu.GestorOdontologico.service;

import pe.edu.upeu.GestorOdontologico.dto.MenuMenuItenTO;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public interface IMenuMenuItemDao {
    List<MenuMenuItenTO> listaAccesos(String perfil, Properties idioma);
    Map<String, String[]> accesosAutorizados(List<MenuMenuItenTO> accesos);
}

package pe.edu.upeu.GestorOdontologico.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.GestorOdontologico.model.Perfil;
import pe.edu.upeu.GestorOdontologico.repository.ICrudGenericoRepository;
import pe.edu.upeu.GestorOdontologico.repository.PerfilRepository;
import pe.edu.upeu.GestorOdontologico.service.IPerfilService;

@RequiredArgsConstructor
@Service
public class PerfilServiceImp extends CrudGenericoServiceImp<Perfil, Long> implements IPerfilService {

    private final PerfilRepository perfilRepository;

    @Override
    protected ICrudGenericoRepository<Perfil, Long> getRepo() {
        return perfilRepository;
    }
}


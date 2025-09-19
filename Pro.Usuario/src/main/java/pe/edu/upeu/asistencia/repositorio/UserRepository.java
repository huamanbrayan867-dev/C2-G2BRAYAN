package pe.edu.upeu.asistencia.repositorio;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import pe.edu.upeu.asistencia.modelo.Participante;

import java.util.ArrayList;
import java.util.List;

public abstract class UserRepository {
    public List<Participante> listaParticipantes = new ArrayList<>();

    public List<Participante> findAll() {
        listaParticipantes.add(
                new Participante(
                        new SimpleStringProperty("43631917"),
                        new SimpleStringProperty("Leandro"),
                        new SimpleStringProperty("Gado"),
                        new SimpleBooleanProperty(true)

                )
        );
        return listaParticipantes;

    }
}

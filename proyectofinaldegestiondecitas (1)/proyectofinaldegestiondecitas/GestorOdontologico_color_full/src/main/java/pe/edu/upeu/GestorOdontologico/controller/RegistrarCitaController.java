package pe.edu.upeu.GestorOdontologico.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.GestorOdontologico.service.CitaService;

@Component
public class RegistrarCitaController {

    @FXML private ComboBox<String> pacienteCombo;
    @FXML private ComboBox<String> odontologoCombo;
    @FXML private DatePicker fechaPicker;
    @FXML private TextField horaField;

    @Autowired
    private CitaService citaService;

    @FXML
    public void initialize() {
        // When controller is created by Spring (via FXMLLoader.setControllerFactory)
        // the citaService will already be injected, so it's safe to use here.
        if (citaService != null) {
            pacienteCombo.getItems().addAll(citaService.obtenerPacientes());
            odontologoCombo.getItems().addAll(citaService.obtenerOdontologos());
        }
    }

    @FXML
    public void registrarCita() {
        citaService.registrarCita(
                pacienteCombo.getValue(),
                odontologoCombo.getValue(),
                fechaPicker.getValue(),
                horaField.getText()
        );
        new Alert(Alert.AlertType.INFORMATION, "Cita registrada correctamente").show();
    }
}

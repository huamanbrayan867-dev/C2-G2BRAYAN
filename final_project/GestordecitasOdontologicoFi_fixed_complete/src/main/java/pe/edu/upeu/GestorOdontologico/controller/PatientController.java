package pe.edu.upeu.GestorOdontologico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.upeu.GestorOdontologico.model.Patient;
import pe.edu.upeu.GestorOdontologico.service.IPatientService;

@Component
@RequiredArgsConstructor
public class PatientController {

    private final IPatientService patientService;

    @FXML private TextField txtDni;
    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtEmail;
    @FXML private TextArea txtHistorial;

    @FXML
    public void savePatient() {

    }

    public void openPatientForm(ActionEvent actionEvent) {
    }

    public void openReportes(ActionEvent actionEvent) {
    }
}

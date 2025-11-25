package pe.edu.upeu.GestorOdontologico.utils;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MainController {

    @FXML private Button btnNueva;
    @FXML private Button btnListar;

    @pe.edu.upeu.GestorOdontologico.utils.FXML
    public void initialize() {
        btnNueva.setOnAction(e -> {
            Alert a = new Alert(AlertType.INFORMATION, "Aquí iría el formulario para crear una cita.");
            a.showAndWait();
        });
        btnListar.setOnAction(e -> {
            Alert a = new Alert(AlertType.INFORMATION, "Aquí se listarán las citas guardadas (integración con Spring).");
            a.showAndWait();
        });
    }
}

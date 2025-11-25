package pe.edu.upeu.GestorOdontologico.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import org.springframework.stereotype.Controller;
import javafx.event.ActionEvent;

@Controller
public class CitasController {

    @FXML
    private TextField txtPaciente;

    @FXML
    private ComboBox<String> cbOdontologo;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextField txtHora;

    @FXML
    private TableView<?> tablaCitas;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colPaciente;

    @FXML
    private TableColumn<?, ?> colOdontologo;

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private TableColumn<?, ?> colHora;

    @FXML
    public void initialize() {
        System.out.println("CitasController cargado correctamente.");
    }

    @FXML
    private void registrarCita() {
        System.out.println("Registrando cita...");
    }
}



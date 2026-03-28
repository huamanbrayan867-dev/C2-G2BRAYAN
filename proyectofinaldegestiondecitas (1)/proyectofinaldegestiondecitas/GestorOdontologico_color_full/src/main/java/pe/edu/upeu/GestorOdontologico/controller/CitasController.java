
package pe.edu.upeu.GestorOdontologico.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.GestorOdontologico.model.Cita;
import pe.edu.upeu.GestorOdontologico.service.CitaService;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class CitasController {

    private final CitaService citaService;

    @FXML
    private TextField txtPaciente;

    @FXML
    private ComboBox<String> cbOdontologo;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextField txtHora;

    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    private TableColumn<Cita, Long> colId;

    @FXML
    private TableColumn<Cita, String> colPaciente;

    @FXML
    private TableColumn<Cita, String> colOdontologo;

    @FXML
    private TableColumn<Cita, String> colFecha;

    @FXML
    private TableColumn<Cita, String> colHora;

    private ObservableList<Cita> listaCitas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        System.out.println("CitasController cargado correctamente.");

        // Configurar las columnas de la tabla
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPaciente.setCellValueFactory(new PropertyValueFactory<>("paciente"));
        colOdontologo.setCellValueFactory(new PropertyValueFactory<>("odontologo"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));

        // Cargar odontólogos en el ComboBox
        cbOdontologo.setItems(FXCollections.observableArrayList(citaService.obtenerOdontologos()));

        // Cargar las citas existentes
        cargarCitas();
    }

    @FXML
    private void registrarCita() {
        try {
            // Validar que todos los campos estén llenos
            if (txtPaciente.getText().isEmpty() ||
                    cbOdontologo.getValue() == null ||
                    dpFecha.getValue() == null ||
                    txtHora.getText().isEmpty()) {

                mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
                return;
            }

            // Validar formato de hora (HH:mm)
            String hora = txtHora.getText();
            if (!hora.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
                mostrarAlerta("Error", "Formato de hora inválido. Use HH:mm (ejemplo: 14:30)", Alert.AlertType.ERROR);
                return;
            }

            // Registrar la cita
            citaService.registrarCita(
                    txtPaciente.getText(),
                    cbOdontologo.getValue(),
                    dpFecha.getValue(),
                    hora
            );

            // Mostrar mensaje de éxito
            mostrarAlerta("Éxito", "Cita registrada correctamente", Alert.AlertType.INFORMATION);

            // Limpiar formulario
            limpiarFormulario();

            // Recargar la tabla
            cargarCitas();

        } catch (Exception e) {
            mostrarAlerta("Error", "Error al registrar la cita: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void cargarCitas() {
        try {
            listaCitas.clear();
            listaCitas.addAll(citaService.listarCitas());
            tablaCitas.setItems(listaCitas);
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al cargar las citas: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void limpiarFormulario() {
        txtPaciente.clear();
        cbOdontologo.setValue(null);
        dpFecha.setValue(null);
        txtHora.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

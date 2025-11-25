package pe.edu.upeu.GestorOdontologico.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.GestorOdontologico.model.Cita;
import pe.edu.upeu.GestorOdontologico.service.CitaService;

@Component
public class ListaCitasController {

    @FXML private TableView<Cita> tablaCitas;

    // fx:id names match FXML (colPaciente, colOdontologo, colFecha, colHora)
    @FXML private TableColumn<Cita, String> colPaciente;
    @FXML private TableColumn<Cita, String> colOdontologo;
    @FXML private TableColumn<Cita, String> colFecha;
    @FXML private TableColumn<Cita, String> colHora;

    @Autowired
    private CitaService citaService;

    @FXML
    public void initialize() {
        colPaciente.setCellValueFactory(new PropertyValueFactory<>("paciente"));
        colOdontologo.setCellValueFactory(new PropertyValueFactory<>("odontologo"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));

        if (citaService != null && tablaCitas != null) {
            tablaCitas.getItems().setAll(citaService.listarCitas());
        }
    }
}

package pe.edu.upeu.GestorOdontologico.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.GestorOdontologico.model.Cita;
import pe.edu.upeu.GestorOdontologico.service.CitaService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class CalendarioController {

    @FXML private TableView<CalendarioFila> tablaCalendario;

    @FXML private TableColumn<CalendarioFila, String> colHora;
    @FXML private TableColumn<CalendarioFila, String> colLunes;
    @FXML private TableColumn<CalendarioFila, String> colMartes;
    @FXML private TableColumn<CalendarioFila, String> colMiercoles;
    @FXML private TableColumn<CalendarioFila, String> colJueves;
    @FXML private TableColumn<CalendarioFila, String> colViernes;
    @FXML private TableColumn<CalendarioFila, String> colSabado;

    @FXML private Label lblSemana;

    private LocalDate fechaBase = LocalDate.now();

    @Autowired
    private CitaService CitaService; // Tu servicio real

    @FXML
    public void initialize() {

        colHora.setCellValueFactory(data -> data.getValue().horaProperty());
        colLunes.setCellValueFactory(data -> data.getValue().lunesProperty());
        colMartes.setCellValueFactory(data -> data.getValue().martesProperty());
        colMiercoles.setCellValueFactory(data -> data.getValue().miercolesProperty());
        colJueves.setCellValueFactory(data -> data.getValue().juevesProperty());
        colViernes.setCellValueFactory(data -> data.getValue().viernesProperty());
        colSabado.setCellValueFactory(data -> data.getValue().sabadoProperty());

        cargarSemana();
    }

    private void cargarSemana() {

        LocalDate lunes = fechaBase.with(java.time.DayOfWeek.MONDAY);
        lblSemana.setText("Semana: " + lunes + " - " + lunes.plusDays(6));

        List<Cita> citas = CitaService.listarCitas();

        ObservableList<CalendarioFila> filas = FXCollections.observableArrayList();

        for (int h = 8; h <= 18; h++) {
            CalendarioFila fila = new CalendarioFila(h + ":00");

            for (Cita c : citas) {

                LocalDate fecha = LocalDate.parse(c.getFecha(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                int dia = fecha.getDayOfWeek().getValue(); // 1 = lunes ... 6 = sábado

                if (dia >= 1 && dia <= 6 && c.getHora().startsWith(String.valueOf(h))) {
                    fila.set(dia, c.getPaciente() + " (" + c.getOdontologo() + ")");
                }
            }

            filas.add(fila);
        }

        tablaCalendario.setItems(filas);
    }

    @FXML
    public void semanaAnterior() {
        fechaBase = fechaBase.minusWeeks(1);
        cargarSemana();
    }

    @FXML
    public void semanaSiguiente() {
        fechaBase = fechaBase.plusWeeks(1);
        cargarSemana();
    }
}

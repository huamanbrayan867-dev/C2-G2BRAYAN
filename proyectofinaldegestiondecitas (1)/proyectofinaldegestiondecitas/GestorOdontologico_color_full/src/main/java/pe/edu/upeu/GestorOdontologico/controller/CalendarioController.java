
package pe.edu.upeu.GestorOdontologico.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.GestorOdontologico.model.Cita;
import pe.edu.upeu.GestorOdontologico.service.CitaService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CalendarioController {

    private final CitaService citaService;

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

    @FXML
    public void initialize() {
        System.out.println("CalendarioController inicializado");

        // Configurar las columnas
        colHora.setCellValueFactory(data -> data.getValue().horaProperty());
        colLunes.setCellValueFactory(data -> data.getValue().lunesProperty());
        colMartes.setCellValueFactory(data -> data.getValue().martesProperty());
        colMiercoles.setCellValueFactory(data -> data.getValue().miercolesProperty());
        colJueves.setCellValueFactory(data -> data.getValue().juevesProperty());
        colViernes.setCellValueFactory(data -> data.getValue().viernesProperty());
        colSabado.setCellValueFactory(data -> data.getValue().sabadoProperty());

        // Ajustar anchos de columnas
        colHora.setMinWidth(80);
        colLunes.setMinWidth(120);
        colMartes.setMinWidth(120);
        colMiercoles.setMinWidth(120);
        colJueves.setMinWidth(120);
        colViernes.setMinWidth(120);
        colSabado.setMinWidth(120);

        cargarSemana();
    }

    private void cargarSemana() {
        try {
            // Calcular el lunes de la semana actual
            LocalDate lunes = fechaBase.with(java.time.DayOfWeek.MONDAY);
            LocalDate domingo = lunes.plusDays(6);

            // Formatear fechas para mostrar
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            lblSemana.setText("Semana del " + lunes.format(formatter) + " al " + domingo.format(formatter));

            // Obtener todas las citas
            List<Cita> todasLasCitas = citaService.listarCitas();
            System.out.println("Total de citas encontradas: " + todasLasCitas.size());

            // Filtrar citas de la semana actual
            LocalDate finalLunes = lunes;
            LocalDate finalDomingo = domingo;
            List<Cita> citasSemana = todasLasCitas.stream()
                    .filter(cita -> {
                        try {
                            LocalDate fechaCita = LocalDate.parse(cita.getFecha());
                            return !fechaCita.isBefore(finalLunes) && !fechaCita.isAfter(finalDomingo);
                        } catch (DateTimeParseException e) {
                            System.err.println("Error al parsear fecha: " + cita.getFecha());
                            return false;
                        }
                    })
                    .toList();

            System.out.println("Citas en esta semana: " + citasSemana.size());

            // Crear filas del calendario (8:00 - 18:00)
            ObservableList<CalendarioFila> filas = FXCollections.observableArrayList();

            for (int hora = 8; hora <= 18; hora++) {
                String horaStr = String.format("%02d:00", hora);
                CalendarioFila fila = new CalendarioFila(horaStr);

                // Buscar citas para esta hora
                for (Cita cita : citasSemana) {
                    try {
                        LocalDate fechaCita = LocalDate.parse(cita.getFecha());
                        int diaSemana = fechaCita.getDayOfWeek().getValue(); // 1=Lunes, 7=Domingo

                        // Solo mostrar de lunes a sábado
                        if (diaSemana >= 1 && diaSemana <= 6) {
                            // Verificar si la hora de la cita coincide con esta fila
                            String horaCita = cita.getHora();
                            if (horaCita != null && horaCita.startsWith(String.format("%02d:", hora))) {
                                String texto = cita.getPaciente() + "\n" + cita.getOdontologo();
                                fila.set(diaSemana, texto);
                                System.out.println("Cita agregada: " + texto + " - Día: " + diaSemana + " - Hora: " + horaCita);
                            }
                        }
                    } catch (DateTimeParseException e) {
                        System.err.println("Error al procesar cita: " + e.getMessage());
                    }
                }

                filas.add(fila);
            }

            tablaCalendario.setItems(filas);
            System.out.println("Calendario cargado con " + filas.size() + " filas");

        } catch (Exception e) {
            System.err.println("Error al cargar semana: " + e.getMessage());
            e.printStackTrace();
        }
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

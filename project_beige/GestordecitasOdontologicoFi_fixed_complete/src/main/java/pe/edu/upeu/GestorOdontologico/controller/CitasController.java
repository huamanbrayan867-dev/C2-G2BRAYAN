package pe.edu.upeu.GestorOdontologico.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class CitasController {

    @FXML
    private TableView<?> tablaCitas;  // Puedes cambiar el tipo luego

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnActualizar;

    @FXML
    public void initialize() {
        System.out.println("CitasController cargado correctamente.");
        // Aquí podrás cargar la lista de citas después
    }

    @FXML
    private void agregarCita() {
        // Lógica para agregar cita
        System.out.println("Agregar cita...");
    }

    @FXML
    private void editarCita() {
        // Lógica para editar cita
        System.out.println("Editar cita...");
    }

    @FXML
    private void eliminarCita() {
        // Lógica para eliminar cita
        System.out.println("Eliminar cita...");
    }

    @FXML
    private void actualizarTabla() {
        // Lógica para refrescar la tabla de citas
        System.out.println("Actualizar lista...");
    }
    @FXML
    private void registrarCita() {
        System.out.println("Registrando cita...");
        // Aquí pondrás la lógica real después
    }



}

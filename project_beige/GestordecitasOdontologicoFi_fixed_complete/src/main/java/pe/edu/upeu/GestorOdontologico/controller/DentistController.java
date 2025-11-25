package pe.edu.upeu.GestorOdontologico.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.upeu.GestorOdontologico.model.Dentist;
import pe.edu.upeu.GestorOdontologico.service.IDentistService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DentistController {

    private final IDentistService dentistService;

    // Form fields (dentist-form.fxml)
    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtColegiado;
    @FXML private TextField txtEspecialidad;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtEmail;
    @FXML private TextArea txtObservaciones;
    @FXML private Button btnGuardar;

    // List fields (dentist-list.fxml)
    @FXML private TableView<Dentist> tblDentists;
    @FXML private TableColumn<Dentist, Long> colId;
    @FXML private TableColumn<Dentist, String> colNombres;
    @FXML private TableColumn<Dentist, String> colApellidos;
    @FXML private TableColumn<Dentist, String> colColegiado;
    @FXML private TableColumn<Dentist, String> colEspecialidad;
    @FXML private TableColumn<Dentist, String> colTelefono;
    @FXML private TableColumn<Dentist, String> colEmail;
    @FXML private TextField txtSearch;

    private ObservableList<Dentist> dentistList = FXCollections.observableArrayList();

    // ---------- Form actions ----------
    @FXML
    public void initialize() {
        // If the TableView exists in the loaded FXML, initialize columns
        try {
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
            colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
            colColegiado.setCellValueFactory(new PropertyValueFactory<>("colegiado"));
            colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
            colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

            loadDentists();
        } catch (Exception ignored) { /* cuando el form es cargado solo, algunas FX nodes no están presentes */ }
    }

    public void loadDentists() {
        List<Dentist> list = dentistService.findAll();
        dentistList.setAll(list);
        if (tblDentists != null) tblDentists.setItems(dentistList);
    }

    @FXML
    public void saveDentist() {
        try {
            Dentist d = new Dentist();
            d.setNombres(txtNombres.getText());
            d.setApellidos(txtApellidos.getText());
            d.setColegiado(txtColegiado.getText());
            d.setEspecialidad(txtEspecialidad.getText());
            d.setTelefono(txtTelefono.getText());
            d.setEmail(txtEmail.getText());
            d.setObservaciones(txtObservaciones.getText());

            dentistService.save(d);
            loadDentists();

            Alert a = new Alert(Alert.AlertType.INFORMATION, "Odontólogo guardado correctamente", ButtonType.OK);
            a.showAndWait();
            clearForm();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error al guardar: " + e.getMessage(), ButtonType.OK).show();
        }
    }

    @FXML
    public void clearForm() {
        if (txtNombres != null) txtNombres.clear();
        if (txtApellidos != null) txtApellidos.clear();
        if (txtColegiado != null) txtColegiado.clear();
        if (txtEspecialidad != null) txtEspecialidad.clear();
        if (txtTelefono != null) txtTelefono.clear();
        if (txtEmail != null) txtEmail.clear();
        if (txtObservaciones != null) txtObservaciones.clear();
    }

    // ---------- List actions ----------
    @FXML
    public void searchByLastName() {
        if (txtSearch == null) return;
        String q = txtSearch.getText();
        if (q == null || q.isEmpty()) {
            loadDentists();
        } else {
            List<Dentist> found = dentistService.searchByLastName(q);
            dentistList.setAll(found);
            tblDentists.setItems(dentistList);
        }
    }

    @FXML
    public void deleteSelected() {
        Dentist selected = tblDentists.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Seleccione un odontólogo para eliminar", ButtonType.OK).show();
            return;
        }
        dentistService.delete(selected.getId());
        loadDentists();
    }

    @FXML
    public void openEdit() {
        Dentist sel = tblDentists.getSelectionModel().getSelectedItem();
        if (sel == null) {
            new Alert(Alert.AlertType.WARNING, "Seleccione un odontólogo para editar", ButtonType.OK).show();
            return;
        }
        // llenar el formulario con los datos seleccionados
        txtNombres.setText(sel.getNombres());
        txtApellidos.setText(sel.getApellidos());
        txtColegiado.setText(sel.getColegiado());
        txtEspecialidad.setText(sel.getEspecialidad());
        txtTelefono.setText(sel.getTelefono());
        txtEmail.setText(sel.getEmail());
        txtObservaciones.setText(sel.getObservaciones());

        // opcional: si quieres actualizar en lugar de crear, podrías guardar el id en una variable de clase
    }

    public void openDentistMenu(ActionEvent actionEvent) {
    }

    public void openDentistForm(ActionEvent actionEvent) {
    }

    public void openDentistList(ActionEvent actionEvent) {

    }
}

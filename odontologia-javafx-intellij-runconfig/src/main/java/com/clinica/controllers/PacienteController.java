package com.clinica.controllers;

import com.clinica.dao.PacienteDAO;
import com.clinica.models.Paciente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PacienteController {
    @FXML private TextField txtNombre, txtApellido, txtDni, txtTelefono;
    @FXML private TableView<Paciente> table;
    @FXML private TableColumn<Paciente, Integer> colId;
    @FXML private TableColumn<Paciente, String> colNombre, colApellido, colDni, colTelefono;

    @FXML public void initialize() {
        colId.setCellValueFactory(cd -> new javafx.beans.property.SimpleIntegerProperty(cd.getValue().getId()).asObject());
        colNombre.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getNombre()));
        colApellido.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getApellido()));
        colDni.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getDni()));
        colTelefono.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getTelefono()));
        refresh();
    }
    @FXML private void add() { PacienteDAO.insert(txtNombre.getText(), txtApellido.getText(), txtDni.getText(), txtTelefono.getText()); refresh(); clear(); }
    @FXML private void delete() { Paciente sel = table.getSelectionModel().getSelectedItem(); if (sel!=null) { PacienteDAO.delete(sel.getId()); refresh(); } }
    private void refresh() { table.setItems(FXCollections.observableArrayList(PacienteDAO.all())); }
    private void clear(){ txtNombre.clear(); txtApellido.clear(); txtDni.clear(); txtTelefono.clear(); }
}

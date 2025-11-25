package com.clinica.controllers;

import com.clinica.dao.OdontologoDAO;
import com.clinica.models.Odontologo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class OdontologoController {
    @FXML private TextField txtNombre, txtApellido, txtEspecialidad, txtTelefono;
    @FXML private TableView<Odontologo> table;
    @FXML private TableColumn<Odontologo, Integer> colId;
    @FXML private TableColumn<Odontologo, String> colNombre, colApellido, colEspecialidad, colTelefono;

    @FXML public void initialize() {
        colId.setCellValueFactory(cd -> new javafx.beans.property.SimpleIntegerProperty(cd.getValue().getId()).asObject());
        colNombre.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getNombre()));
        colApellido.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getApellido()));
        colEspecialidad.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getEspecialidad()));
        colTelefono.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getTelefono()));
        refresh();
    }
    @FXML private void add() { OdontologoDAO.insert(txtNombre.getText(), txtApellido.getText(), txtEspecialidad.getText(), txtTelefono.getText()); refresh(); clear(); }
    @FXML private void delete() { Odontologo sel = table.getSelectionModel().getSelectedItem(); if (sel!=null) { OdontologoDAO.delete(sel.getId()); refresh(); } }
    private void refresh() { table.setItems(FXCollections.observableArrayList(OdontologoDAO.all())); }
    private void clear(){ txtNombre.clear(); txtApellido.clear(); txtEspecialidad.clear(); txtTelefono.clear(); }
}

package com.clinica.controllers;

import com.clinica.dao.CitaDAO;
import com.clinica.dao.OdontologoDAO;
import com.clinica.dao.PacienteDAO;
import com.clinica.models.Cita;
import com.clinica.models.Odontologo;
import com.clinica.models.Paciente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class CitaController {
    @FXML private TextField txtFecha, txtHora, txtMotivo;
    @FXML private ComboBox<Odontologo> cbOdont;
    @FXML private ComboBox<Paciente> cbPac;
    @FXML private TableView<Cita> table;
    @FXML private TableColumn<Cita, Integer> colId;
    @FXML private TableColumn<Cita, String> colFecha, colHora, colMotivo, colOd, colPacCol;


    @FXML public void initialize() {
        colId.setCellValueFactory(cd -> new javafx.beans.property.SimpleIntegerProperty(cd.getValue().getId()).asObject());
        colFecha.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getFecha()));
        colHora.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getHora()));
        colMotivo.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getMotivo()));
        colOd.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cd.getValue().getOdontologoId())));
        colPacCol.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cd.getValue().getPacienteId())));
        refresh();
    }
    @FXML private void add() {
        Odontologo o = cbOdont.getValue(); Paciente p = cbPac.getValue();
        if (o!=null && p!=null) { CitaDAO.insert(txtFecha.getText(), txtHora.getText(), txtMotivo.getText(), o.getId(), p.getId()); refresh(); clear(); } else { new Alert(Alert.AlertType.WARNING, "Seleccione odontólogo y paciente").show(); }
    }
    @FXML private void delete() { Cita sel = table.getSelectionModel().getSelectedItem(); if (sel!=null) { CitaDAO.delete(sel.getId()); refresh(); } }
    private void refresh() { List<Odontologo> od = OdontologoDAO.all(); List<Paciente> pa = PacienteDAO.all(); cbOdont.getItems().setAll(od); cbPac.getItems().setAll(pa); table.setItems(FXCollections.observableArrayList(CitaDAO.all())); }
    private void clear(){ txtFecha.clear(); txtHora.clear(); txtMotivo.clear();}
}

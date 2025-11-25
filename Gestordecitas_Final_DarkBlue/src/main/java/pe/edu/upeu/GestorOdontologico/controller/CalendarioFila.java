package pe.edu.upeu.GestorOdontologico.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CalendarioFila {

    private final StringProperty hora = new SimpleStringProperty();
    private final StringProperty lunes = new SimpleStringProperty();
    private final StringProperty martes = new SimpleStringProperty();
    private final StringProperty miercoles = new SimpleStringProperty();
    private final StringProperty jueves = new SimpleStringProperty();
    private final StringProperty viernes = new SimpleStringProperty();
    private final StringProperty sabado = new SimpleStringProperty();

    public CalendarioFila(String hora) {
        this.hora.set(hora);
    }

    public void set(int diaSemana, String valor) {
        switch (diaSemana) {
            case 1 -> lunes.set(valor);
            case 2 -> martes.set(valor);
            case 3 -> miercoles.set(valor);
            case 4 -> jueves.set(valor);
            case 5 -> viernes.set(valor);
            case 6 -> sabado.set(valor);
        }
    }

    public StringProperty horaProperty() { return hora; }
    public StringProperty lunesProperty() { return lunes; }
    public StringProperty martesProperty() { return martes; }
    public StringProperty miercolesProperty() { return miercoles; }
    public StringProperty juevesProperty() { return jueves; }
    public StringProperty viernesProperty() { return viernes; }
    public StringProperty sabadoProperty() { return sabado; }
}

package com.clinica.models;

public class Cita {
    private int id; private String fecha; private String hora; private String motivo; private int odontologoId; private int pacienteId;
    public Cita(int id, String fecha, String hora, String motivo, int odontologoId, int pacienteId) { this.id=id; this.fecha=fecha; this.hora=hora; this.motivo=motivo; this.odontologoId=odontologoId; this.pacienteId=pacienteId; }
    public int getId(){return id;} public String getFecha(){return fecha;} public String getHora(){return hora;} public String getMotivo(){return motivo;} public int getOdontologoId(){return odontologoId;} public int getPacienteId(){return pacienteId;}
}

package com.clinica.models;

public class Paciente {
    private int id; private String nombre; private String apellido; private String dni; private String telefono;
    public Paciente(int id, String nombre, String apellido, String dni, String telefono) { this.id=id; this.nombre=nombre; this.apellido=apellido; this.dni=dni; this.telefono=telefono; }
    public int getId(){return id;} public String getNombre(){return nombre;} public String getApellido(){return apellido;} public String getDni(){return dni;} public String getTelefono(){return telefono;}
}

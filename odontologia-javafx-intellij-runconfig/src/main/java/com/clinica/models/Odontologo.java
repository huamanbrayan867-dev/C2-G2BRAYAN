package com.clinica.models;

public class Odontologo {
    private int id; private String nombre; private String apellido; private String especialidad; private String telefono;
    public Odontologo(int id, String nombre, String apellido, String especialidad, String telefono) { this.id=id; this.nombre=nombre; this.apellido=apellido; this.especialidad=especialidad; this.telefono=telefono; }
    public int getId(){return id;} public String getNombre(){return nombre;} public String getApellido(){return apellido;} public String getEspecialidad(){return especialidad;} public String getTelefono(){return telefono;}
}

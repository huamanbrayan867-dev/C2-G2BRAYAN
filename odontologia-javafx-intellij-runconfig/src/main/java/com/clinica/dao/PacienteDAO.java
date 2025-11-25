package com.clinica.dao;

import com.clinica.models.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {
    public static List<Paciente> all() {
        List<Paciente> list = new ArrayList<>();
        try (Connection c = Database.getConnection(); Statement s = c.createStatement(); ResultSet rs = s.executeQuery("SELECT * FROM paciente")) {
            while (rs.next()) list.add(new Paciente(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("dni"), rs.getString("telefono")));
        } catch (Exception e){e.printStackTrace();}
        return list;
    }
    public static void insert(String nombre, String apellido, String dni, String telefono) {
        try (Connection c = Database.getConnection(); PreparedStatement p = c.prepareStatement("INSERT INTO paciente(nombre,apellido,dni,telefono) VALUES(?,?,?,?)")) {
            p.setString(1,nombre);p.setString(2,apellido);p.setString(3,dni);p.setString(4,telefono);p.executeUpdate();
        } catch (Exception e){e.printStackTrace();}
    }
    public static void delete(int id) { try (Connection c = Database.getConnection(); PreparedStatement p = c.prepareStatement("DELETE FROM paciente WHERE id=?")) { p.setInt(1,id); p.executeUpdate(); } catch (Exception e){e.printStackTrace();} }
}

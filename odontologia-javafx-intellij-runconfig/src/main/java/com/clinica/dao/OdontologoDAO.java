package com.clinica.dao;

import com.clinica.models.Odontologo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAO {
    public static List<Odontologo> all() {
        List<Odontologo> list = new ArrayList<>();
        try (Connection c = Database.getConnection(); Statement s = c.createStatement(); ResultSet rs = s.executeQuery("SELECT * FROM odontologo")) {
            while (rs.next()) list.add(new Odontologo(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("especialidad"), rs.getString("telefono")));
        } catch (Exception e){e.printStackTrace();}
        return list;
    }
    public static void insert(String nombre, String apellido, String especialidad, String telefono) {
        try (Connection c = Database.getConnection(); PreparedStatement p = c.prepareStatement("INSERT INTO odontologo(nombre,apellido,especialidad,telefono) VALUES(?,?,?,?)")) {
            p.setString(1,nombre);p.setString(2,apellido);p.setString(3,especialidad);p.setString(4,telefono);p.executeUpdate();
        } catch (Exception e){e.printStackTrace();}
    }
    public static void delete(int id) { try (Connection c = Database.getConnection(); PreparedStatement p = c.prepareStatement("DELETE FROM odontologo WHERE id=?")) { p.setInt(1,id); p.executeUpdate(); } catch (Exception e){e.printStackTrace();} }
}

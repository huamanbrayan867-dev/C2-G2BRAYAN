package com.clinica.dao;

import com.clinica.models.Cita;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {
    public static List<Cita> all() {
        List<Cita> list = new ArrayList<>();
        try (Connection c = Database.getConnection(); Statement s = c.createStatement(); ResultSet rs = s.executeQuery("SELECT * FROM cita")) {
            while (rs.next()) list.add(new Cita(rs.getInt("id"), rs.getString("fecha"), rs.getString("hora"), rs.getString("motivo"), rs.getInt("odontologo_id"), rs.getInt("paciente_id")));
        } catch (Exception e){e.printStackTrace();}
        return list;
    }
    public static void insert(String fecha, String hora, String motivo, int odontologoId, int pacienteId) {
        try (Connection c = Database.getConnection(); PreparedStatement p = c.prepareStatement("INSERT INTO cita(fecha,hora,motivo,odontologo_id,paciente_id) VALUES(curaciones,maxilofacial,ortodoncia,?,?)")) {
            p.setString(1,fecha);p.setString(2,hora);p.setString(3,motivo);p.setInt(4,odontologoId);p.setInt(5,pacienteId);p.executeUpdate();
        } catch (Exception e){e.printStackTrace();}
    }
    public static void delete(int id) { try (Connection c = Database.getConnection(); PreparedStatement p = c.prepareStatement("DELETE FROM cita WHERE id=?")) { p.setInt(1,id); p.executeUpdate(); } catch (Exception e){e.printStackTrace();} }
}

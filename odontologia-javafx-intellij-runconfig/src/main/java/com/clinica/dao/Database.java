package com.clinica.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {
    private static final String DB_DIR = "db";
    private static final String URL = "jdbc:sqlite:db/odontologia.db";
    public static void init() {
        try { Files.createDirectories(Paths.get(DB_DIR)); } catch (Exception e) { e.printStackTrace(); }
        try (Connection c = getConnection(); Statement s = c.createStatement()) {
            s.execute("PRAGMA foreign_keys = ON;");
            s.execute("CREATE TABLE IF NOT EXISTS odontologo (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, apellido TEXT NOT NULL, especialidad TEXT, telefono TEXT);");
            s.execute("CREATE TABLE IF NOT EXISTS paciente (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, apellido TEXT NOT NULL, dni TEXT UNIQUE, telefono TEXT);");
            s.execute("CREATE TABLE IF NOT EXISTS cita (id INTEGER PRIMARY KEY AUTOINCREMENT, fecha TEXT NOT NULL, hora TEXT NOT NULL, motivo TEXT, odontologo_id INTEGER, paciente_id INTEGER, FOREIGN KEY (odontologo_id) REFERENCES odontologo(id) ON DELETE SET NULL, FOREIGN KEY (paciente_id) REFERENCES paciente(id) ON DELETE SET NULL);");
        } catch (Exception ex) { ex.printStackTrace(); }
    }
    public static Connection getConnection() throws Exception { return DriverManager.getConnection(URL); }
}

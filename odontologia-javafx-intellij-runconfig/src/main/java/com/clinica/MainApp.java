package com.clinica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        com.clinica.dao.Database.init();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(root, 1000, 650);
        stage.setTitle("Clínica Dental Sonrisa Saludable");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(); }
}

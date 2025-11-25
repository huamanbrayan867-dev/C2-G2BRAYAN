package pe.edu.upeu.GestorOdontologico.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MainGuiController implements Initializable {

    @FXML
    private BorderPane bp;

    @FXML
    private MenuBar menuBarFx;

    @FXML
    private TabPane tabPaneFx;

    @Autowired
    private ApplicationContext context;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MenuPrincipal();
    }

    /** ---------------------- MENÚ PRINCIPAL ---------------------- **/
    private void MenuPrincipal() {
        Menu menuPrincipal = new Menu("Principal");

        // ITEM: Gestión de Usuarios
        MenuItem gestionUsuarios = new MenuItem("Gestión de Usuarios");
        gestionUsuarios.setOnAction(e -> abrirPestaña(
                "usuarios",
                "/view/main_editar.fxml",
                "Gestión de Usuarios"
        ));

        // ITEM: Registrar Odontólogo
        MenuItem registrarOdontologo = new MenuItem("Registrar Odontólogo");
        registrarOdontologo.setOnAction(e -> abrirPestaña(
                "registrar_odontologo",
                "/view/dentist-form.fxml",
                "Registrar Odontólogo"
        ));

        // ITEM: Lista de Odontólogos
        MenuItem listaOdontologos = new MenuItem("Lista de Odontólogos");
        listaOdontologos.setOnAction(e -> abrirPestaña(
                "lista_odontologos",
                "/view/dentist-list.fxml",
                "Lista de Odontólogos"
        ));

        menuPrincipal.getItems().addAll(
                gestionUsuarios,
                registrarOdontologo,
                listaOdontologos
        );

        menuBarFx.getMenus().add(menuPrincipal);
    }


    /** ---------------------- ABRIR PESTAÑAS ---------------------- **/
    private void abrirPestaña(String id, String rutaFXML, String titulo) {
        // Si la pestaña ya está abierta → seleccionarla
        for (Tab tab : tabPaneFx.getTabs()) {
            if (id.equals(tab.getId())) {
                tabPaneFx.getSelectionModel().select(tab);
                return;
            }
        }

        Tab nueva = new Tab(titulo);
        nueva.setId(id);
        nueva.setClosable(true);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            loader.setControllerFactory(context::getBean);
            nueva.setContent(loader.load());
        } catch (IOException ex) {
            nueva.setContent(new StackPane(new Label(" Error al cargar: " + rutaFXML)));
            ex.printStackTrace();
        }

        tabPaneFx.getTabs().add(nueva);
        tabPaneFx.getSelectionModel().select(nueva);
    }


    /** ---------------------- SESIÓN ---------------------- **/
    @FXML
    private void cerrarSesion() {
        try {
            Stage stage = (Stage) bp.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
            loader.setControllerFactory(context::getBean);

            Scene nuevaEscena = new Scene(loader.load());
            stage.setScene(nuevaEscena);

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error al regresar al login").showAndWait();
        }
    }

    @FXML
    private void salir() {
        Stage stage = (Stage) bp.getScene().getWindow();
        stage.close();
        javafx.application.Platform.exit();
        System.exit(0);
    }
    @FXML
    private void abrirUsuario() {
    }

    /** ---------------------- MÉTODOS DE ABRIR VISTAS ---------------------- **/

    @FXML
    public void openDentistForm() {
        abrirPestaña("registrar_odontologo", "/view/dentist-form.fxml", "Registrar Odontólogo");
    }
    @FXML
    private void abrirGestionUsuarios() {
        abrirPestaña("usuarios", "/view/main_editar.fxml", "Gestión de Usuarios");
    }

    @FXML
    public void openDentistList() {
        abrirPestaña("lista_odontologos", "/view/dentist-list.fxml", "Lista de Odontólogos");
    }
    @FXML
    public void openPatientForm() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/patient-form.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    private void abrirCalendarioCitas() {
        abrirPestaña("calendario", "/view/CalendarioView.fxml", "Agenda de Citas");
    }


    @FXML
    private void abrirGestionCitas() {
        abrirPestaña("citas", "/view/CitasView.fxml", "Gestión de Citas");
    }



    public void abrirRegistrarCita() {
        cargarTab("Registrar Cita", "/view/registrar-cita.fxml");
    }

    public void abrirListaCitas() {
        cargarTab("Lista de Citas", "/view/lista-citas.fxml");
    }


    /** ---------------------- CARGAR TABS GENERALES ---------------------- **/
    private void cargarTab(String titulo, String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
 

            Parent root = loader.load();

            // Si ya existe → seleccionar
            for (Tab t : tabPaneFx.getTabs()) {
                if (t.getText().equals(titulo)) {
                    tabPaneFx.getSelectionModel().select(t);
                    return;
                }
            }

            Tab tab = new Tab(titulo);
            tab.setContent(root);
            tabPaneFx.getTabs().add(tab);
            tabPaneFx.getSelectionModel().select(tab);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR al cargar el tab: " + fxml);
        }
    }
}

package pe.edu.upeu.GestorOdontologico.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import pe.edu.upeu.GestorOdontologico.utils.ThemeManager;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import pe.edu.upeu.GestorOdontologico.components.StageManager;
import pe.edu.upeu.GestorOdontologico.components.Toast;
import pe.edu.upeu.GestorOdontologico.dto.SessionManager;
import pe.edu.upeu.GestorOdontologico.model.Usuario;
import pe.edu.upeu.GestorOdontologico.service.IUsuarioService;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private ApplicationContext context;
    @Autowired
    IUsuarioService us;
    @FXML
    TextField txtUsuario;
    @FXML
    PasswordField txtClave;
    @FXML
    Button btnIngresar;

    @FXML
    public void cerrar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        System.out.println(" Botón Ingresar presionado");
        System.out.println("Servicio usuarioService = " + us);


        try {
            Usuario usu=us.loginUsuario(txtUsuario.getText(), new String(txtClave.getText()));
            if (usu!=null) {

                SessionManager.getInstance().setUserId(usu.getIdUsuario());
                SessionManager.getInstance().setUserName(usu.getUser());
                SessionManager.getInstance().setUserPerfil(usu.getIdPerfil().getNombre());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/maingui.fxml"));
                loader.setControllerFactory(context::getBean);
                Parent mainRoot = loader.load();

                Screen screen = Screen.getPrimary();
                Rectangle2D bounds = screen.getBounds();

                Scene mainScene = new Scene(mainRoot,bounds.getWidth(),bounds.getHeight()-30);
                // Aplicar tema actual al iniciar la vista principal
                ThemeManager.applyTheme(mainScene);
                Stage stage = (Stage) ((Node)  event.getSource()).getScene().getWindow();
                //stage.getIcons().add(new Image(getClass().getResource("/img/store.png").toExternalForm()));

                stage.setScene(mainScene);
                stage.setTitle("Gestor Odontologico - Principal");
                stage.setX(bounds.getMinX());
                stage.setY(bounds.getMinY());
                stage.setResizable(true);
                StageManager.setPrimaryStage(stage);
                stage.setWidth(bounds.getWidth());
                stage.setHeight(bounds.getHeight());
                stage.show();
            } else {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                double with=stage.getWidth()*2;
                double h=stage.getHeight()/2;
                System.out.println(with + " h:"+h);
                Toast.showToast(stage, "Credenciales invalidos!! intente  nuevamente", 2000, with, h);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

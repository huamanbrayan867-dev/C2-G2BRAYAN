package pe.edu.upeu.GestorOdontologico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GestorOdontologicoApp extends Application {

    private ConfigurableApplicationContext context;
    private Parent parent;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {

        SpringApplicationBuilder builder = new SpringApplicationBuilder(GestorOdontologicoApp.class);
        builder.application().setWebApplicationType(WebApplicationType.NONE);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
        loader.setControllerFactory(context::getBean);
        parent = loader.load();
    }

    @Override
    public void start(Stage stage) {

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Gestor Odontologico- Login");
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() {

        context.close();
    }
}

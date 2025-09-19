package pe.edu.upeu.asistencia.control;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pe.edu.upeu.asistencia.modelo.Participante;
import pe.edu.upeu.asistencia.servicio.ParticipanteServicioI;

@Controller
public class RegistroController {

    @FXML
    private TextField txtNombres, txtCorreo, txtApellidos;

    @FXML
    private TableView<Participante> tableView;
    ObservableList<Participante> listaParticipantes;
    @FXML
    private TableColumn<Participante, String> CorreoColum, nombreColum, apellidoColum;
    private TableColumn<Participante, Void> opcColum;

    @Autowired
    ParticipanteServicioI ps;
    int indexE=-1;

    @FXML
    public void initialize(){

        definirColumnas();
        listarParticipantes();
    }

    public void limpiarFormulario(){
        txtNombres.setText("");
        txtCorreo.setText("");
        txtApellidos.setText("");

    }

    @FXML
    public void registrarParticipante(){
        Participante p = new Participante();
        p.setCorreo(new SimpleStringProperty(txtCorreo.getText()));
        p.setNombre(new SimpleStringProperty(txtNombres.getText()));
        p.setApellidos(new SimpleStringProperty(txtApellidos.getText()));

        if(indexE==-1){
            ps.save(p);
        }else{
            ps.update(p,  indexE);
            indexE=-1;
        }
        limpiarFormulario();
        listarParticipantes();
    }

    public void definirColumnas(){
        CorreoColum=new TableColumn("Correo");
        nombreColum=new TableColumn("Nombres");
        apellidoColum=new TableColumn("Apellidos");


        opcColum=new TableColumn("Opciones");
        opcColum.setPrefWidth(200);
        tableView.getColumns().addAll(CorreoColum, nombreColum, apellidoColum, opcColum);
    }

    public void agregarAccionBotones(){
        Callback<TableColumn<Participante, Void> , TableCell<Participante, Void> > cellFactory =
                param->new  TableCell<>(){
                private final Button editarBtn = new Button("Editar");
                private final Button eliminarBtn = new Button("Eliminar");
                    {
                        editarBtn.setOnAction(event -> {
                            Participante p=getTableView().getItems().get(getIndex());
                            editarDatos(p, getIndex());
                        });
                        eliminarBtn.setOnAction(event -> {
                            eliminarParticipante(getIndex());
                        });
                    }
                @Override
                public void updateItem(Void item, boolean empty){
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);
                    }else {
                        HBox hbox = new HBox(editarBtn, eliminarBtn);
                        hbox.setSpacing(10);
                        setGraphic(hbox);
                    }
                }
                };
            opcColum.setCellFactory(cellFactory);
    }
    public void listarParticipantes(){
        CorreoColum.setCellValueFactory(cellData->cellData.getValue().getCorreo());
        nombreColum.setCellValueFactory(cellData->cellData.getValue().getNombre());
        apellidoColum.setCellValueFactory(cellData->cellData.getValue().getApellidos());
        agregarAccionBotones();
        listaParticipantes=FXCollections.observableArrayList(ps.findAll());
        tableView.setItems(listaParticipantes);
    }
    public void eliminarParticipante(int index){
        ps.delete(index);
        listarParticipantes();
    }

    public void editarDatos(Participante p, int index){
        txtCorreo.setText(p.getCorreo().getValue());
        txtNombres.setText(p.getNombre().getValue());
        txtApellidos.setText(p.getApellidos().getValue());

        indexE=index;
    }




}

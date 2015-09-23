/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiMapper.view;

import com.sun.deploy.panel.TextFieldProperty;
import guiMapper.GuiMapperInit;
import guiMapper.model.Captura;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javafx.scene.control.ButtonType;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.script.ScreenImage;

/**
 *
 * @author UserQV
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<Captura> tableObjects = new TableView();
    @FXML
    private TableColumn<Captura, String> ObjectColumn;
    @FXML
    private TableColumn<Captura, String> commandColumn;
    @FXML
    private TableColumn<Captura, String> ImageColumn;
    @FXML
    private TableColumn<Captura, Number> XColumn;
    @FXML
    private TableColumn<Captura, Number> YColumn;

    @FXML
    private TextField objectField;
    @FXML
    private ComboBox commandField;
    @FXML
    private ImageView thumbImagen;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDelete;

    private GuiMapperInit mainApp;
    public static String nombObj;
    public static String accion;
    private ObservableList<Captura> items;

    private ObservableList<Captura> capturaData = FXCollections.observableArrayList();

    public ObservableList<Captura> getCapturaData() {
        return capturaData;
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setGuiMapperInit(GuiMapperInit mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        //tablaObjetos.setItems(mainApp.getCapturaData());
    }

    @FXML
    private void handleCapture(ActionEvent event) {
        grabar();
        //guardar();
    }

    @FXML
    private void handleEdit(ActionEvent event) {

        Captura selectedCaptura = tableObjects.getSelectionModel().getSelectedItem();

        selectedCaptura.setObjeto(objectField.getText());
        selectedCaptura.setAccion(commandField.getValue().toString());
    }

    @FXML
    private void handleDelete(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete capture");
        alert.setHeaderText("Delete capture selected");
        alert.setContentText("Are you sure to delete this capture?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            int selectedIndex = tableObjects.getSelectionModel().getSelectedIndex();
            tableObjects.getItems().remove(selectedIndex);
            disableItems(true);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    public void grabar() {
        Screen screen = new Screen();
        Region region = screen.selectRegion("Select a picture");
        ScreenImage si = screen.capture(region);
        BufferedImage img = si.getImage();
        GuiMapperInit init = new GuiMapperInit();
        init.showSave();
        int x = region.x;
        int y = region.y;
        Captura captura = new Captura();
        captura.setAccion(accion);
        captura.setObjeto(nombObj);
        captura.setX(x);
        captura.setY(y);
        captura.setImagen("xxx");
        //GuiMapperInit main = new GuiMapperInit();
        getCapturaData().add(captura);
        tableObjects.setItems(getCapturaData());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.commandField.setItems(GuiMapperInit.options);

        ObjectColumn.setCellValueFactory(
                cellData -> cellData.getValue().objetoProperty());

        commandColumn.setCellValueFactory(
                cellData -> cellData.getValue().accionProperty());

        ImageColumn.setCellValueFactory(
                cellData -> cellData.getValue().imagenProperty());

        XColumn.setCellValueFactory(
                cellData -> cellData.getValue().xProperty());

        YColumn.setCellValueFactory(
                cellData -> cellData.getValue().yProperty());

        tableObjects.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> detallesObjeto(newValue));

        disableItems(true);
    }

    private void detallesObjeto(Captura captura) {
        if (captura != null) {
            // Fill the labels with info from the person object.
            objectField.setText(captura.getObjeto());
            commandField.setPromptText(captura.getAccion());
            disableItems(false);

        } else {
            // Person is null, remove all the text.
            objectField.setText("");
            commandField.setPromptText("");
        }
    }

    public void guardarImagen(String ruta, String nombre, BufferedImage img) {
        File file = new File(ruta, nombre + ".png");
        try {
            boolean i = ImageIO.write(img, "png", file);
            if (i) {
                System.out.println("true");
            }
        } catch (IOException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error al guardar la imágen");
            alert.setHeaderText("Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }

    }

    public void disableItems(boolean option) {
        objectField.setDisable(option);
        commandField.setDisable(option);
        btnEdit.setDisable(option);
        btnDelete.setDisable(option);
    }

}

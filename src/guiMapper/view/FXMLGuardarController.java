/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiMapper.view;

import guiMapper.GuiMapperInit;
import guiMapper.model.Captura;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author UserQV
 */
public class FXMLGuardarController implements Initializable {
    
    @FXML
    private TextField objectField;
    @FXML
    private ComboBox commandField;
   private Stage dialogStage;
    private boolean guardar  = false;
    private Captura captura;
    
//    ObservableList<String> opciones = FXCollections.observableArrayList(
//            "Click",
//            "ClickDerecho",
//            "DobleClick",
//            "Escribir" 
//    );
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.commandField.setItems(GuiMapperInit.options);
    }
    
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
    @FXML
    private void handlerSave(){
        FXMLDocumentController.nombObj = objectField.getText();
        FXMLDocumentController.accion = commandField.getValue().toString();
        this.guardar = true;
        this.dialogStage.close();
    }
    
    public boolean clickGuardar(){
        return this.guardar;
    }
}

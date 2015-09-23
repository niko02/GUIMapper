/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiMapper;

import guiMapper.model.Captura;
import guiMapper.view.FXMLGuardarController;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author UserQV
 */
public class GuiMapperInit extends Application {

    private Stage primaryStage;

    public static ObservableList<String> options = FXCollections.observableArrayList(
            "Click",
            "ClickDerecho",
            "DobleClick",
            "Escribir"
    );
//    private ObservableList<Captura> capturaData = FXCollections.observableArrayList();
//    
//     public ObservableList<Captura> getCapturaData() {
//        return capturaData;
//    }

    public GuiMapperInit() {
        //capturaData.add(new Captura("jhvjg","hgvchfd","jhgv",0,0));
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("GUIMapper");

        // Set the application icon.
        //this.primaryStage.getIcons().add(new Image("file:resources/images/1441657744_Folder.png"));
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public boolean showSave() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GuiMapperInit.class.getResource("view/FXMLGuardar.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New capture");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            FXMLGuardarController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.clickGuardar();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}

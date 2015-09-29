/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiMapper;

import guiMapper.model.Captura;
import guiMapper.model.CaptureListWrapper;
import guiMapper.view.FXMLDocumentController;
import guiMapper.view.FXMLGuardarController;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javafx.scene.image.Image;

/**
 *
 * @author UserQV
 */
public class GuiMapperInit extends Application {

    private Stage primaryStage = new Stage();
    private AnchorPane rootLayout;

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
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("GUIMapper");

        initGUI();
        // Set the application icon.
        this.primaryStage.getIcons().add(new Image("file:resources/images/thunder_gui.png"));

    }

    public void initGUI() {
        try {
//            FXMLLoader loader = new FXMLLoader();
//            Parent root;
//            root = loader.load(getClass().getResource("view/FXMLDocument.fxml"));
//
//            Scene scene = new Scene(root);
//
//            primaryStage.setScene(scene);
//
//            FXMLDocumentController controller = loader.getController();
//            controller.setGuiMapperInit(this);
//
//            primaryStage.show();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GuiMapperInit.class
                    .getResource("view/FXMLDocument.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            FXMLDocumentController controller = loader.getController();
            controller.setGuiMapperInit(this);

            primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(GuiMapperInit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
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

    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(GuiMapperInit.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setCaptureFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(GuiMapperInit.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("GuiMapper - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }

    public void saveCaptureDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CaptureListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our capture data.
            CaptureListWrapper wrapper = new CaptureListWrapper();
            wrapper.setCaptures(FXMLDocumentController.capturaData);
           // wrapper.setCaptures(capturaData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setCaptureFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo guardar la informacion en:\n" + file.getPath());
            alert.setContentText("error " + e);
            alert.showAndWait();
        }
    }

    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(CaptureListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            CaptureListWrapper wrapper = (CaptureListWrapper) um.unmarshal(file);

            FXMLDocumentController.capturaData.clear();
            FXMLDocumentController.capturaData.addAll(wrapper.getCaptures());

            // Save the file path to the registry.
            setCaptureFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo leer la informacion de:\n" + file.getPath());
            alert.setContentText("error " + e);
            alert.showAndWait();
        }
    }

}

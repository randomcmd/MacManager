package MacUserInterface;

import ConnectToDatabase.ConnectToDatabase;
import MacManager.MacManager;
import Settings.Settings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import java.io.File;
import java.io.IOException;

import java.util.List;

//Controller of GUI lul
public class Controller {

    @FXML
    private TextField tfManualEntry;

    @FXML
    private Button bImport;
    @FXML
    private Button bValidate;
    @FXML
    private Button bExport;
    @FXML
    private Label lImported;
    @FXML
    private Label lSucsessful;
    @FXML
    private Label lFailed;
    @FXML
    private Button bSettings;
    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    private javafx.scene.control.Button minimizeButton;
    @FXML
    private javafx.scene.control.Button databaseButton;

    MacManager macManager;
    Settings settings;
    ConnectToDatabase database;

    File file;
    Label fileLabel = new Label();

    int stage = 0;

    public void initialize() {
        macManager = new MacManager();
        settings = new Settings();
        database = new ConnectToDatabase();
        //database.list = macManager.csvLinkedListString;
        updateButtons();
    }

    public void selectFile() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Open CSV file (Selecting multiple files supported)");

        // Show open file dialog
        List<File> files = fileChooser.showOpenMultipleDialog(Main.primaryStage);

        if (files != null && !files.isEmpty()) {
            for (File file : files) {
                if (file != null) {
                    fileLabel.setText(file.getPath());
                    macManager.importFile(file.getPath());
                    stage = 1;
                    System.out.println(">> Opening " + file.getPath());
                }
            }
        }

        updateButtons();
    }

    public void validateFile() {
        if (stage == 1) {
            macManager.validateFile();
            stage = 2;
        }
        updateButtons();
    }

    public void exportFile() {

        if (stage != 2) return;

        //MACSSUCSESS
        FileChooser fileChooser = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "(*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Save sucsessfully imported MACs as .txt");

        //MACSFAIL
        //Show save file dialog
        File file = fileChooser.showSaveDialog(Main.primaryStage);

        FileChooser fileChooserfail = new FileChooser();

        //Set extension filter for text files
        FileChooser.ExtensionFilter extFilterfail = new FileChooser.ExtensionFilter("TXT files (*.txt)", "(*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Save falied imported MACs as .txt");

        //Show save file dialog
        File filefail = fileChooser.showSaveDialog(Main.primaryStage);

        if (file != null && filefail != null) {
            macManager.exportFile(file.getPath(), filefail.getPath());
            System.out.println(">> Saving " + file.getPath());
            System.out.println(">> Saving " + filefail.getPath());
        }

        updateButtons();
    }

    public void updateButtons() {
        switch (stage) {
            case 0 -> {
                bImport.setDisable(false);
                bValidate.setDisable(true);
                bExport.setDisable(true);
            }
            case 1 -> {
                bImport.setDisable(false);
                bValidate.setDisable(false);
                bExport.setDisable(true);
            }
            default -> {
                bImport.setDisable(false);
                bValidate.setDisable(false);
                bExport.setDisable(false);
            }
        }

        lImported.setText(Integer.toString(macManager.getImportedStat()));
        lSucsessful.setText(Integer.toString(macManager.getSuccessfulStat()));
        lFailed.setText(Integer.toString(macManager.getFailedStat()));
    }

    public void manualEntryTest() {
        if (!tfManualEntry.getText().isEmpty()) macManager.manualEntry(tfManualEntry.getText());
        tfManualEntry.setText("");
        updateButtons();
    }

    static Stage settingsStage;
    private double xOffset = 0;
    private double yOffset = 0;
    public void openSettings() throws IOException {
        Stage settingsStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
        settingsStage.setTitle("Mac Manager");
        settingsStage.setScene(new Scene(root, 420, 124));
        settingsStage.setResizable(false);

        settingsStage.initStyle(StageStyle.UNDECORATED);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                settingsStage.setX(event.getScreenX() - xOffset);
                settingsStage.setY(event.getScreenY() - yOffset);
            }
        });

        settingsStage.show();
    }

    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    public void minimizeButtonAction() {
        Stage stage=(Stage) minimizeButton.getScene().getWindow();
        // is stage minimizable into task bar. (true | false)
        stage.setIconified(true);
    }

    ConnectToDatabase connectToDatabase;

    @FXML
    public void databaseButtonAction(){

        //Update list with correct values
        macManager.updateCompleteDataSet();

        //Create ConnectToDatabase and give it the list to upload
        connectToDatabase = new ConnectToDatabase();
        connectToDatabase.setListReference(macManager.csvLinkedListString);
        connectToDatabase.run();
    }


    public void openHelp() {
        Settings.openFile(Settings.helpPath);
    }

    /*
    //Thank you dude on stackoverflow for providing this great code, nah forget it it doesnt fucking work
    //@Override
    public void handle(KeyEvent event) {
        if (event.getCode().equals(KeyCode.TAB)) {
            Node node = (Node) event.getSource();
            if (node instanceof TextField) {
                TextFieldSkin skin = (TextFieldSkin) ((TextField)node).getSkin();
                if (event.isShiftDown()) {
                    skin.getBehavior().traversePrevious();
                }
                else {
                    skin.getBehavior().traverseNext();
                }
            }
            else if (node instanceof TextArea) {
                TextAreaSkin skin = (TextAreaSkin) ((TextArea)node).getSkin();
                if (event.isShiftDown()) {
                    skin.getBehavior().traversePrevious();
                }
                else {
                    skin.getBehavior().traverseNext();
                }
            }

            event.consume();
        }

     */
}


package MacUserInterface;

import MacManager.MacManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

//Controller of GUI lul
public class Controller {

    @FXML
    private TextField tfManualEntry;

    MacManager macManager;

    File file;
    Label fileLabel = new Label();

    int stage = 0;

    public void initialize() {
        macManager = new MacManager();
    }

    public void selectFile() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(Main.primaryStage);

        if (file != null) {
            fileLabel.setText(file.getPath());
            macManager.importFile(file.getPath());
            System.out.println(">> Opening " + file.getPath());
        }

        stage = 1;
    }

    public void validateFile() {
        if (stage == 1) {
            macManager.validateFile();
            stage = 2;
        }
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
    }

    public void manualEntryTest() {
        if (!tfManualEntry.getText().isEmpty()) macManager.manualEntry(tfManualEntry.getText());
    }
}

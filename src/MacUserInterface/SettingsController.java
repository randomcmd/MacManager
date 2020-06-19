package MacUserInterface;

import Debug.*;
import Settings.Settings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsController {

    Settings settings = new Settings();

    @FXML
    Button bApplySettings;

    @FXML
    TextField tfCSVFieldSeperator;

    @FXML
    TextField tfMacColumn;

    public void initialize() {
        tfCSVFieldSeperator.setText(Settings.csvFieldSeparator);
        tfMacColumn.setText(String.valueOf(Settings.macColumn));
    }

    public void updateButton() {
        if (validateInput()) {
            bApplySettings.setDisable(false);
        } else {
            bApplySettings.setDisable(true);
        }
    }

    public boolean validateInput() {
        try {
            int num = Integer.parseInt(tfMacColumn.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void applySettings() {
        settings.setProperty("csvFieldSeperator", tfCSVFieldSeperator.getText());
        settings.setProperty("macColumn", tfMacColumn.getText());
        settings.saveSettings();
        settings.importSettings();
        Debug.Log("Settings applied", 0, DEBUGTYPE.SUCCESS);
        closeButtonAction();
    }

    private void closeButtonAction() {
        // get a handle to the stage
        Stage stage = (Stage) bApplySettings.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}

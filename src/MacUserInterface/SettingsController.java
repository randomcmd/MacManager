package MacUserInterface;

import Debug.*;
import Settings.Settings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsController{

    Settings settings = new Settings();

    @FXML
    Button bApplySettings;

    @FXML
    Button closeButton2;

    @FXML
    TextField tfCSVFieldSeparator;

    @FXML
    TextField tfMacColumn;
    @FXML
    TextField tfDBUsername;
    @FXML
    TextField tfDBPassword;
    @FXML
    TextField tfDBDatabasename;
    @FXML
    TextField tfDBTablename;


    public void initialize()
    {
        tfCSVFieldSeparator.setText(Settings.csvFieldSeperator);
        tfMacColumn.setText(String.valueOf(Settings.macColumn));
        tfDBUsername.setText(String.valueOf(Settings.dbUsername));
        tfDBPassword.setText(String.valueOf(Settings.dbPassword));
        tfDBTablename.setText(String.valueOf(Settings.dbTablename));
        tfDBDatabasename.setText(String.valueOf(Settings.dbDatabasename));

    }

    public void updateButton()
    {
        if(validateInput()){bApplySettings.setDisable(false);}else{bApplySettings.setDisable(true);}
    }

    public boolean validateInput()
    {
        try{
            int num = Integer.parseInt(tfMacColumn.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void applySettings()
    {
        settings.setProperty("csvFieldSeperator", tfCSVFieldSeparator.getText());
        settings.setProperty("macColumn",tfMacColumn.getText());
        if (tfDBUsername.getText() != null) settings.setProperty("dbUsername", tfDBUsername.getText());
        if (tfDBPassword.getText() != null) settings.setProperty("dbPassword", tfDBPassword.getText());
        if (tfDBDatabasename.getText() != null) settings.setProperty("dbDatabasename", tfDBDatabasename.getText());
        if (tfDBTablename.getText() != null) settings.setProperty("dbTablename", tfDBTablename.getText());
        settings.saveSettings();
        settings.importSettings();
        Debug.Log("Settings applied",0, DEBUGTYPE.SUCCESS);
        Debug.Log("csvFieldSeperator = " + Settings.csvFieldSeperator,1,DEBUGTYPE.DETAIL);
        Debug.Log("macColumn = " + Settings.macColumn,1,DEBUGTYPE.DETAIL);
        Debug.Log("DBUsername = " + Settings.dbUsername, 1, DEBUGTYPE.DETAIL);
        Debug.Log("DBPassword = " + Settings.dbPassword, 1, DEBUGTYPE.DETAIL);
        Debug.Log("DBDatabasename = " + Settings.dbDatabasename, 1, DEBUGTYPE.DETAIL);
        Debug.Log("DBTablename = " + Settings.dbTablename, 1, DEBUGTYPE.DETAIL);
    }

    @FXML
    private void closeButtonAction2(){
        //get a handle to the stage
        Stage stage = (Stage) closeButton2.getScene().getWindow();
    //do what you have to do
        stage.close();
    }

}

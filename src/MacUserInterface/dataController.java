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


public class dataController {
    @FXML
    private javafx.scene.control.Button closeButton3;
    @FXML
    private javafx.scene.control.Button databaseButton;
    @FXML
    private Label dataLabel;

    ConnectToDatabase database;
    MacManager macManager;
    ConnectToDatabase connectToDatabase;

    public void initialize() {
        macManager = new MacManager();
        database = new ConnectToDatabase();
    }

    @FXML
    public void databaseButtonAction(){

        //Update list with correct values
        macManager.updateCompleteDataSet();

        //Create ConnectToDatabase and give it the list to upload
        connectToDatabase = new ConnectToDatabase();
        connectToDatabase.insert(macManager.csvLinkedListString);
        dataLabel.setText("Erfolgreich");
        /*if (connectToDatabase.hatgeklappt){

            // setze lable auf ja oder so
        }
        else {
            //setze lable auf nein oder so
        }*/



    }
//test
    @FXML
    private void closeButtonAction3(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton3.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}

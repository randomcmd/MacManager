package MacUserInterface;


import ConnectToDatabase.ConnectToDatabase;
import Debug.Debug;
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


    ConnectToDatabase connectToDatabase;

    public void initialize() {
        connectToDatabase= new ConnectToDatabase();
    }

    @FXML
    public void databaseButtonAction(){
        Debug.Log("Opening Database");
        //Update list with correct values

        //Create ConnectToDatabase and give it the list to upload
        connectToDatabase = new ConnectToDatabase();
        connectToDatabase.insert(MacManager.csvLinkedListString);
        if (connectToDatabase.hatgeklappt){

            dataLabel.setText("Erfolgreich");
        }
        else {
            dataLabel.setText("You MAD!");
        }



    }
    //test 23
    @FXML
    private void closeButtonAction3(){
        // get a handle to the stage
        Stage stage = (Stage) closeButton3.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}

package MacUserInterface;

import MacManager.MacManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    MacManager macManager;

    public static void main(String[] args) {
        //launch(args);
        Main main = new Main();
        main.run();
    }

    public void run() {
        //MacManager is used to handle the import, validation and export
        macManager = new MacManager();

        //Import, Validate, Export
        macManager.importFile("sample.csv");
        macManager.validateFile();
        macManager.exportFile("macs.txt", "macsFAIL.txt");
    }

    /**
     * Loads GUI
     */
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Mac Manager");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

}

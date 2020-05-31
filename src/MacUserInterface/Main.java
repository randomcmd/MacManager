package MacUserInterface;

import MacExport.MacExport;
import MacImport.MacImport;
import MacValidation.MacValidation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.LinkedList;

public class Main extends Application {

    MacValidation macValidation;
    MacImport macImport;
    MacExport macExport;

    /*  IMPORT:
        - Import CSV and convert to string list
        - Send string list to validation

        VALIDATION:
        - Take string list and process values
        - Convert string list into MAC list of validated MACS
        - Convert MAC list into string list and send to export

        EXPORT:
        - Save string list to text file
    */

    public static void main(String[] args) {
        launch(args);
        Main main = new Main();
        main.run();
    }

    public void run() {
        macValidation = new MacValidation();
        macImport = new MacImport();
        macExport = new MacExport();

        Object[][] csvArray = macImport.CSVToArray("sample.csv");
        String[][] csvArrayString = Arrays.copyOf(csvArray, csvArray.length, String[][].class);
        LinkedList<String> csvListMACParsed = macImport.parseMACfromArray(csvArrayString);

        Object[] finalizedStringArray = macValidation.validateListString(csvListMACParsed).toArray();
        System.out.println(Arrays.deepToString(finalizedStringArray));

        macExport.saveStringArrayToFile(finalizedStringArray, "macs.txt");
        macExport.saveStringArrayToFile(macValidation.getListError().toArray(), "macsFAIL.txt");

    }


    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Mac Manager");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

}

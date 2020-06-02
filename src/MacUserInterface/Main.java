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
        //launch(args);
        Main main = new Main();
        main.run();
    }

    public void run() {

        String csvFile = "sample.csv";
        String saveFile = "macs.txt";
        String saveFailFile = "macsFAIL.txt";

        System.out.println("Importing " + csvFile);

        macValidation = new MacValidation();
        macImport = new MacImport();
        macExport = new MacExport();

        Object[][] csvArray = macImport.CSVToArray(csvFile);
        String[][] csvArrayString = Arrays.copyOf(csvArray, csvArray.length, String[][].class);
        LinkedList<String> csvListMACParsed = macImport.parseMACfromArray(csvArrayString);

        System.out.println("Validating " + csvFile);
        Object[] finalizedStringArray = macValidation.validateListString(csvListMACParsed).toArray();
        System.out.println(Arrays.deepToString(finalizedStringArray));

        System.out.println("Exporting MACs from " + csvFile + " to " + saveFile + " and " + saveFailFile);
        MacExport.saveStringArrayToFile(finalizedStringArray, saveFile);
        MacExport.saveStringArrayToFile(macValidation.getListError().toArray(), saveFailFile);
        System.out.println("Finished with all the work");
    }


    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Mac Manager");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

}

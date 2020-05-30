package MacUserInterface;

import MacExport.MacExport;
import MacImport.MacImport;
import MacValidation.MacValidation;

import java.util.Arrays;

public class Main /*extends Application*/ {

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
        macValidation = new MacValidation();
        macImport = new MacImport();
        macExport = new MacExport();

        Object[] finalizedStringArray = macValidation.validateListString(macValidation.testStringList()).toArray();
        macExport.saveStringArrayToFile(finalizedStringArray);
        System.out.println(Arrays.deepToString(finalizedStringArray));
    }

    /*@Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }*/

}

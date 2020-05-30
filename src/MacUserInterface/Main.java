package MacUserInterface;

import MacExport.MacExport;
import MacImport.MacImport;
import MacValidation.MacValidation;

import java.util.Arrays;
import java.util.LinkedList;

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

        Object[][] csvArray = macImport.CSVToArray("sample.csv");
        String[][] csvArrayString = Arrays.copyOf(csvArray, csvArray.length, String[][].class);
        LinkedList<String> csvListMACParsed = macImport.parseMACfromArray(csvArrayString);

        Object[] finalizedStringArray = macValidation.validateListString(csvListMACParsed).toArray();
        System.out.println(Arrays.deepToString(finalizedStringArray));

        macExport.saveStringArrayToFile(finalizedStringArray, "macs.txt");
        macExport.saveStringArrayToFile(macValidation.getListError().toArray(), "macsFAIL.txt");

        //System.out.println(Arrays.deepToString(csvArray));
        //System.out.println(Arrays.deepToString(csvArrayMACParsed));

        //Object[] finalizedStringArray = macValidation.validateListString(macValidation.testStringList()).;
        //macExport.saveStringArrayToFile(csvArrayMACParsed, "macs");
        //macExport.saveStringArrayToFile(macValidation.getListError().toArray(), "macsFAIL");
        //System.out.println(Arrays.deepToString(csvArrayMACParsed));
    }

    /*@Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }*/

}

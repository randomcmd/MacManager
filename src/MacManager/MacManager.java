package MacManager;

import MacExport.MacExport;
import MacImport.MacImport;
import MacValidation.MacValidation;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MacManager {

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

    MacImport macImport;
    MacValidation macValidation;
    MacExport macExport;

    public Object[][] csvArray;
    public String[][] csvArrayString;
    public LinkedList<String> csvListMACParsed;
    public Object[] finalizedStringArray;

    /**
     * Constructor for MacManager
     */
    public MacManager() {

        macImport = new MacImport();
        macValidation = new MacValidation();
        macExport = new MacExport();
        finalizedStringArray = new Object[0];
    }

    /**
     * importFile() imports a csv file from a filepath
     */
    public void importFile(@NotNull String localFilename) {
        System.out.println(">> Importing file");
        csvArray = macImport.CSVToArray(localFilename);
        csvArrayString = Arrays.copyOf(csvArray, csvArray.length, String[][].class);
        csvListMACParsed = macImport.parseMACfromArray(csvArrayString);
    }

    /**
     * validateFile() validates MACs from file previously imported by importFiles()
     */
    public void validateFile() {
        System.out.println(">> Validating file");
        finalizedStringArray = macValidation.validateListString(csvListMACParsed).toArray();
        System.out.println(">> Following MACs have been parsed: " + Arrays.deepToString(finalizedStringArray));
    }

    /**
     * exportFile() exports validated MACs previously validated by validateFile()
     */
    public void exportFile(@NotNull String localFilenameSuccess, @NotNull String localFilenameFail) {
        System.out.println(">> Exporting MACs");
        MacExport.saveStringArrayToFile(finalizedStringArray, localFilenameSuccess);
        MacExport.saveStringArrayToFile(macValidation.getListError().toArray(), localFilenameFail);
        System.out.println(">> Finished exporting");
    }

    public void manualEntry(@NotNull String localString) {
        System.out.println(">> Manually importing " + localString);
        List<Object> localistObject = Arrays.asList(finalizedStringArray);
        List<String> localistString = localistObject.stream()
                .map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());

        LinkedList<String> localListStringTemp = new LinkedList<String>();
        localListStringTemp.add(localString);
        localListStringTemp = macValidation.validateListString(localListStringTemp);
        finalizedStringArray = localListStringTemp.toArray();
    }

}

package MacManager;

import ConnectToDatabase.ConnectToDatabase;
import Debug.Debug;
import MacExport.MacExport;
import MacImport.MacImport;
import Debug.*;

import MacValidation.MacValidation;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
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
        //We use an enom to log DEBUG.SUCSESS
        Debug.Log("Importing file",0, DEBUGTYPE.SUCSESS);
        csvArray = macImport.CSVToArray(localFilename);
        csvArrayString = Arrays.copyOf(csvArray, csvArray.length, String[][].class);
        csvListMACParsed = macImport.parseMACfromArray(csvArrayString);
    }

    /**
     * validateFile() validates MACs from file previously imported by importFiles()
     */
    public void validateFile() {
        Debug.Log("Validating Files",0,DEBUGTYPE.SUCSESS);
        finalizedStringArray = macValidation.validateListString(csvListMACParsed).toArray();
        Debug.Log("Following MACs have been parsed: " + Arrays.deepToString(finalizedStringArray),0,DEBUGTYPE.SUCSESS);
    }

    /**
     * exportFile() exports validated MACs previously validated by validateFile()
     */
    public void exportFile(@NotNull String localFilenameSuccess, @NotNull String localFilenameFail) {
        Debug.Log("Exporting MACs",0,DEBUGTYPE.SUCSESS);
        MacExport.saveStringArrayToFile(finalizedStringArray, localFilenameSuccess);
        MacExport.saveStringArrayToFile(macValidation.getListError().toArray(), localFilenameFail);
        Debug.Log("Finished exporting",0,DEBUGTYPE.SUCSESS);
    }

    public void manualEntry(@NotNull String localString) {
        Debug.Log("Manually importing " + localString,0,DEBUGTYPE.SUCSESS);
        List<Object> localistObject = Arrays.asList(finalizedStringArray);
        List<String> localistString = localistObject.stream()
                .map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());

        LinkedList<String> localListStringTemp = new LinkedList<>();
        localListStringTemp.add(localString);
        localListStringTemp = macValidation.validateListString(localListStringTemp);
        finalizedStringArray = localListStringTemp.toArray();
    }

    public int getImportedStat() {
        if (csvListMACParsed != null) return csvListMACParsed.size();
        return 0;
    }

    public int getSuccessfulStat() {
        if (finalizedStringArray != null) return finalizedStringArray.length;
        return 0;
    }

    public int getFailedStat()
    {
        if (macValidation.getListError() != null)  return macValidation.getListError().size();
        return 0;
    }

}

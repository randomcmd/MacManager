package MacManager;

import Debug.Debug;
import MacExport.MacExport;
import MacImport.MacImport;
import Debug.*;

import MacValidation.MacValidation;
import Settings.Settings;
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

    public LinkedList<LinkedList<String>> csvLinkedListString;
    public LinkedList<String> csvLinkedListMACParsed;
    public LinkedList<String> finalizedStringLinkedList;

    /**
     * Constructor for MacManager
     */
    public MacManager() {

        macImport = new MacImport();
        macValidation = new MacValidation();
        macExport = new MacExport();

        csvLinkedListString = new LinkedList<LinkedList<String>>();
        finalizedStringLinkedList = new LinkedList<String>();
        csvLinkedListMACParsed = new LinkedList<String>();
    }

    /**
     * importFile() imports a csv file from a filepath
     */
    public void importFile(@NotNull String localFilename) {
        //We use an enom to log DEBUG.SUCSESS
        Debug.Log("Importing file", 0, DEBUGTYPE.SUCCESS);

        csvLinkedListString.addAll(macImport.CSVToLinkedList(localFilename));
        csvLinkedListMACParsed.addAll(macImport.parseMACfromLinkedList(macImport.CSVToLinkedList(localFilename)));

        //Debug.Log(Arrays.deepToString(csvLinkedListMACParsed.toArray()));
    }

    /**
     * validateFile() validates MACs from file previously imported by importFiles()
     */
    public void validateFile() {
        Debug.Log("Validating Files", 0, DEBUGTYPE.SUCCESS);

        finalizedStringLinkedList = macValidation.validateListString(csvLinkedListMACParsed);

        updateCompleteDataSet();
        debugPrintCompleteDataSet();
        Debug.Log("Following MACs have been parsed: " + Arrays.deepToString(finalizedStringLinkedList.toArray()), 0, DEBUGTYPE.SUCCESS);
    }

    /**
     * exportFile() exports validated MACs previously validated by validateFile()
     */
    public void exportFile(@NotNull String localFilenameSuccess, @NotNull String localFilenameFail) {
        Debug.Log("Exporting MACs", 0, DEBUGTYPE.SUCCESS);
        MacExport.saveStringLinkedListToFile(finalizedStringLinkedList, localFilenameSuccess);
        MacExport.saveStringLinkedListToFile(macValidation.getListError(), localFilenameFail);
        Debug.Log("Finished exporting", 0, DEBUGTYPE.SUCCESS);
    }

    public void manualEntry(@NotNull String localString) {
        Debug.Log("Manually importing " + localString, 0, DEBUGTYPE.SUCCESS);

        LinkedList<String> localistObject = finalizedStringLinkedList;
        List<String> localistString = localistObject.stream()
                .map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());

        LinkedList<String> localListStringTemp = new LinkedList<>();
        localListStringTemp.add(localString);
        localListStringTemp = macValidation.validateListString(localListStringTemp);
        finalizedStringLinkedList = localListStringTemp;
    }

    //It is 23:36 14/06/2020 and im about to write the dumbest code ever
    //Insdead of updating only the important elements im gonna yeet fuck this shit and brute force update the wohle list fuck it time pressure
    //Basically it matches the complete list with the list list that stores the entire data
    //Update it is 23:40 AND I FEEL ASHAMED OF THIS CODE
    //Just found the perfect code to export 2d array to csv but its for python fml
    public void updateCompleteDataSet() //updates csvlinkedliststring with listcomplete
    {
        int i = 0;
        for (LinkedList locallistListString : csvLinkedListString)
        {
            locallistListString.set(Settings.macColumn, macValidation.getListComplete().get(i));
            i++;
        }
    }

    public void debugPrintCompleteDataSet()
    {
        Debug.Log(Arrays.deepToString(csvLinkedListString.toArray()));
    }

    public int getImportedStat() {
        if (csvLinkedListMACParsed != null) return csvLinkedListMACParsed.size();
        return 0;
    }

    public int getSuccessfulStat() {
        if (finalizedStringLinkedList != null) return finalizedStringLinkedList.size();
        return 0;
    }

    public int getFailedStat() {
        if (macValidation.getListError() != null) return macValidation.getListError().size();
        return 0;
    }

}

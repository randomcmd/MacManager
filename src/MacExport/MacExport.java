package MacExport;

import Debug.*;
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.util.LinkedList;

public class MacExport {

    /**
     * saveStringArrayToFile() saves an Object[] consisting of Strings to a filepath
     */
    public static void saveStringArrayToFile(@NotNull Object[] localarrayString, @NotNull String filename) {
        try {
            PrintWriter pr = new PrintWriter(filename);

            for (Object o : localarrayString) pr.println(o);
            pr.close();
        } catch (Exception e) {
            e.printStackTrace();
            Debug.Log("No such file exists.", 1, DEBUGTYPE.ERROR);
        }
    }

    /**
     * saveStringLinkedListToFile() saves a LinkedList<String> to a file
     */
    public static void saveStringLinkedListToFile(@NotNull LinkedList<String> locallistString, @NotNull String filename) {
        try {
            //PrintWriter go brbrbrbrbbrbrbrbr
            PrintWriter pr = new PrintWriter(filename);

            //Loop through each object and save it to the file
            for (Object o : locallistString) pr.println(o);
            pr.close();
            //Close the printer
        } catch (Exception e) {
            e.printStackTrace();
            Debug.Log("No such file exists.",1, DEBUGTYPE.ERROR);
        }
    }
}

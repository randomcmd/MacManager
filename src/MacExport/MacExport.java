package MacExport;

import Debug.*;
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;

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
            Debug.Log("No such file exists.",1, DEBUGTYPE.ERROR);
        }
    }
}

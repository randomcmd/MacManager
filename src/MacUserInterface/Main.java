package MacUserInterface;

import MacValidation.MacValidation;

public class Main {

    MacValidation macValidation;

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
        Main main = new Main();
        main.run();
    }

    public void run()
    {
        macValidation = new MacValidation();
        macValidation.test();
    }

}

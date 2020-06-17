package MacValidation;


import Debug.*;
import inet.ipaddr.MACAddressString;
import inet.ipaddr.mac.MACAddress;

import java.util.LinkedList;

public class MacValidation {

    //Two lists are created that store MAC Addresses and the MAC Adresses that could not be parsed
    LinkedList<MACAddress> listMac;
    LinkedList<String> listError;

    public MacValidation() {
        listError = new LinkedList<>();
        listMac = new LinkedList<>();
    }

    /**
     * Validates String List
     */
    public LinkedList<String> validateListString(LinkedList<String> locallistString) {
        return listMacToString(listStringToMac(locallistString));
    }

    /**
     * gentestStringList() generates a random String List of Arrays for testing
     */
    public LinkedList<String> gentestStringList() {
        LinkedList<String> exampleList = new LinkedList<>();
        exampleList.add("00-14-22-01-23-45 "); //ADDED SPACE
        exampleList.add("1O-14-22-0I-23-45 "); //ADDED SPACE AND CHANGED 1 TO I
        exampleList.add("20-14-22-01-23-45S"); //ADDED LETTER
        exampleList.add("30-14-22-01-23-45S"); //ADDED LETTER
        exampleList.add("40-14-22-01-23-45");
        exampleList.add("50-14-22-01-23-45");
        exampleList.add("60-14-22-01-23-45");

        return exampleList;
    }

    /**
     * listStringToMac() converts a String List to a Mac List and validates MAC format and validity
     */
    private LinkedList<MACAddress> listStringToMac(LinkedList<String> locallistString) {

        MACAddressString localMACString;
        listError = new LinkedList<String>();
        listMac = new LinkedList<MACAddress>();
        //if (!listError.isEmpty()) listError.clear();

        //Goes through all the elements of the local string list
        for (String s : locallistString) {

            //Creating MACAdressString
            localMACString = new MACAddressString(s);

            try {
                //Try to convert MACAddressString to MACAddress
                listMac.add(localMACString.toAddress());
                Debug.Log("Parsed: " + localMACString,1, DEBUGTYPE.DETAIL);
            } catch (Exception e) {
                try {
                    //If MacAdressString could not be converted, try autocorrection and try it again
                    localMACString = new MACAddressString(autoCorrectMacAdress(s));
                    listMac.add(localMACString.toAddress());
                    Debug.Log("Parsed after correction: " + autoCorrectMacAdress(s),1, DEBUGTYPE.DETAIL);
                } catch (Exception f) {
                    //Error message
                    listError.add(s);
                    Debug.Log("Mac does not exist: " + s + " OR " + autoCorrectMacAdress(s),1, DEBUGTYPE.DETAIL);
                }

            }

        }

        return listMac;
    }

    /**
     * getListError() is used to get the listError
     */
    public LinkedList<String> getListError() {
        return listError;
    }

    /**
     * listMacToString() converts a Mac List to a String List
     */
    private LinkedList<String> listMacToString(LinkedList<MACAddress> locallistMac) {
        LinkedList<String> locallistString;
        locallistString = new LinkedList<>();

        for (MACAddress macAddress : locallistMac) locallistString.add(macAddress.toString());

        return locallistString;
    }

    //Replace letters that should be numbers and remove spaces
    private String autoCorrectMacAdress(String localString) {
        localString = localString.replace("O", "0");
        localString = localString.replace("I", "1");
        localString = localString.replace(" ", "");
        //System.out.println("ATTENTION TO THIS FIX: " + localString);

        return localString;
    }
}

//class dualList(LinkedList<MACAddress> firstList, LinkedList<String> secondList)
//{

//}
package MacValidation;


import inet.ipaddr.MACAddressString;
import inet.ipaddr.mac.MACAddress;

import java.util.LinkedList;

public class MacValidation {

    //Two lists are created that store MAC Addresses and the MAC Adresses that could not be parsed
    LinkedList<MACAddress> listMac;
    LinkedList<String> listError;

    public MacValidation() {

    }

    public void test() {
        //System.out.println(Arrays.deepToString(listMacToString(listStringToMac(testStringList())).toArray()));
        //MacExport.saveStringArrayToFile(listMacToString(listStringToMac(testStringList())).toArray());
    }

    public LinkedList<String> validateListString(LinkedList<String> locallistString) {
        return listMacToString(listStringToMac(locallistString));
    }

    //Create a list of strings for testing;
    public LinkedList<String> gentestStringList() {
        LinkedList<String> exampleList = new LinkedList<String>();
        exampleList.add("00-14-22-01-23-45 "); //ADDED SPACE
        exampleList.add("1O-14-22-0I-23-45 "); //ADDED SPACE AND CHANGED 1 TO I
        exampleList.add("20-14-22-01-23-45S"); //ADDED LETTER
        exampleList.add("30-14-22-01-23-45S"); //ADDED LETTER
        exampleList.add("40-14-22-01-23-45");
        exampleList.add("50-14-22-01-23-45");
        exampleList.add("60-14-22-01-23-45");

        return exampleList;
    }

    //Convert string list to mac list
    private LinkedList<MACAddress> listStringToMac(LinkedList<String> locallistString) {

        LinkedList<MACAddress> locallistMac = new LinkedList<MACAddress>();
        MACAddressString localMACString;
        listError = new LinkedList<String>();
        if (!listError.isEmpty()) listError.clear();

        //Goes through all the elements of the local string list
        for (String s : locallistString) {

            //Creating MACAdressString
            localMACString = new MACAddressString(s);

            try {
                //Try to convert MACAddressString to MACAddress
                locallistMac.add(localMACString.toAddress());
                System.out.println("Parsed: " + localMACString);
            } catch (Exception e) {
                try {
                    //If MacAdressString could not be converted, try autocorrection and try it again
                    //System.out.println(autoCorrectMacAdress(locallistString.get(i)));
                    localMACString = new MACAddressString(autoCorrectMacAdress(s));
                    //System.out.println(autoCorrectMacAdress(locallistString.get(i)));
                    locallistMac.add(localMACString.toAddress());
                    System.out.println("Parsed after correction: " + autoCorrectMacAdress(s));
                } catch (Exception f) {
                    //Error message
                    listError.add(s);
                    System.out.println("Failed to parse following MAC Adress or it does not exist: " + s + " OR " + autoCorrectMacAdress(s));
                }

            }

        }

        return locallistMac;
    }

    public LinkedList<String> getListError() {
        return listError;
    }

    //Convert mac list to string list
    private LinkedList<String> listMacToString(LinkedList<MACAddress> locallistMac) {
        LinkedList<String> locallistString;
        locallistString = new LinkedList<String>();

        for (MACAddress macAddress : locallistMac) {
            locallistString.add(macAddress.toString());
        }

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
package MacValidation;


import inet.ipaddr.MACAddressString;
import inet.ipaddr.mac.MACAddress;

import java.util.*;

public class MacValidation {

    //Two lists are created that store MAC Addresses and the MAC Adresses that could not be parsed
    LinkedList<MACAddress> listMac;
    LinkedList<String> listError;

    public MacValidation() {

    }

    public void test() {
        System.out.println(Arrays.deepToString(listMacToString(listStringToMac(testStringList())).toArray()));
    }

    //Create a list of strings for testing;
    public LinkedList<String> testStringList() {
        LinkedList<String> exampleList = new LinkedList<String>();
        exampleList.add("10-14-22-01-23-45");
        exampleList.add("SS00-14-22-0I-23-45");
        exampleList.add("00-14-22-01-23-45");
        exampleList.add("00-14-22-01-23-45");
        exampleList.add("0064-14-2242-01-23-451");

        return exampleList;
    }

    //Convert string list to mac list
    public LinkedList<MACAddress> listStringToMac(LinkedList<String> locallistString) {

        LinkedList<MACAddress> locallistMac = new LinkedList<MACAddress>();
        MACAddressString localMACString;

        //Goes through all the elements of the local string list
        for (int i = 0; i < locallistString.size(); i++) {

            //Creating MACAdressString
            localMACString = new MACAddressString(locallistString.get(i));

            try {
                //Try to convert MACAddressString to MACAddress
                locallistMac.add(localMACString.toAddress());
                System.out.println("Parsed: " + locallistMac.get(i).toString());
            } catch (Exception e) {
                try {
                    //If MacAdressString could not be converted, try autocorrection and try it again
                    localMACString = new MACAddressString(autoCorrectMacAdress(locallistString.get(i)));
                    locallistMac.add(localMACString.toAddress());
                    System.out.println("Parsed after correction: " + locallistMac.get(i).toString());
                } catch (Exception f) {
                    //Error message
                    System.out.println("Failed to parse following MAC Adress: " + locallistString.get(i));
                }

            }

        }

        return locallistMac;
    }

    //Convert mac list to string list
    public LinkedList<String> listMacToString(LinkedList<MACAddress> locallistMac) {
        LinkedList<String> locallistString;
        locallistString = new LinkedList<String>();

        for (int i = 0; i < locallistMac.size(); i++) {
            locallistString.add(locallistMac.get(i).toString());
        }

        return locallistString;
    }

    //Replace letters that should be numbers and remove spaces
    public String autoCorrectMacAdress(String localString) {
        localString = localString.replace("O", "0");
        localString = localString.replace("I", "1");
        localString = localString.replace(" ", "");

        return localString;
    }
}

//class dualList(LinkedList<MACAddress> firstList, LinkedList<String> secondList)
//{

//}
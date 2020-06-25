package Debug;

public class Debug {

    public static String debugLog;

    //Basic Debug Log
    public static void Log(String input)
    {
        System.out.println(input);
        debugLog = debugLog + System.lineSeparator() + input;
    }

    //Fancy Debug Log
    public static void Log(String input, Integer offset, DEBUGTYPE type)
    {
        switch(type)
        {
            case SUCCESS: input = ">> " + input; break;
            case ERROR: input = "!! " + input;
            default: break;
        }

        //OG OFFSET "      "
        input = repeatString("    ",offset) + input;

        System.out.println(input);
        debugLog = debugLog + System.lineSeparator() + input;
    }

    public static String repeatString(String localString, int amount)
    {
        if(amount == 0) return "";

        for(int i = 1; i <= amount; i++)
        {
            localString = localString + localString;
        }

        return localString;
    }
}

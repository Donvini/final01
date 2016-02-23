package final01.Serializer;

import edu.kit.informatik.Terminal;
import final01.Exceptions.FileSyntaxException;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class Serializer {
    /**
     *
     */
    private static final String REGEX_EDGE = "(?:(?:[A-Za-z0-9_-]+)|(?:\\s+))+";
    /**
     *
     */
    private static final String REGEX_CITY = "[A-Za-z-]+";

    public static String[] cities(String[] worldMap){
        String[] cities = new String[worldMap.length];
        int i = 0;
        if (validate(worldMap))
            while (!worldMap[i].equalsIgnoreCase("--") && i < worldMap.length) {
                cities[i] = worldMap[i];
                i++;
        }
        return cities;
    }

    private static boolean validate(String[] worldMap) {
        int i = 0;
        try {
            while (!worldMap[i].equalsIgnoreCase("--") && i < worldMap.length) {
                if (!worldMap[i].matches(REGEX_CITY))
                    throw new FileSyntaxException("Invalid city entry found.");
                i++;
            }
            i++;
            while (i < worldMap.length) {
                if (!worldMap[i].matches(REGEX_EDGE))
                    throw new FileSyntaxException("Invalid edge entry found.");
                i++;
            }
        } catch (FileSyntaxException e) {
            Terminal.printLine("Error, " + e.getMessage());
            System.exit(1);
        }
        return true;
    }

    public static String[] connections(String[] worldMap) {
        String[] connections = new String[worldMap.length];
        int i;
        int j = 0;
        for (i = 0; i < worldMap.length; i++) {
            if (worldMap[i].equals("--")) {
                connections[j] = worldMap[i];
                j++;
            }
        }
        return connections;
    }
}
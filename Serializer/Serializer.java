package final01.Serializer;

import final01.Commandline.Terminal;
import final01.Exceptions.FileSyntaxException;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class Serializer {
    /**
     *
     */
    private static final String REGEX_EDGE = "[A-Za-z-]+;[A-Za-z-]+;[0-9]+;[0-9]+";
    /**
     *
     */
    private static final String REGEX_CITY = "[A-Za-z-]+";

    private static String[] startCities;
    private static String[] destinationCities;
    private static int[] distance;
    private static int[] time;

    private static String[] cities(String[] worldMap) throws FileSyntaxException {
        String[] cities = new String[worldMap.length];
        int i = 0;
        if (validate(worldMap))
            while (!worldMap[i].equalsIgnoreCase("--") && i < worldMap.length) {
                cities[i] = worldMap[i];
                i++;
        }
        return cities;
    }

    //TODO : check whether a string occurs twice
    private static boolean duplicates(final String[] worldMap) {
        Set<String> lump = new HashSet<String>();
        for (String entry : worldMap) {
            if (lump.contains(entry))
                return true;
            lump.add(entry);
        }
        return false;
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
        return !duplicates(worldMap);
    }

    //TODO : split connections in parts, separated by semicolon

    private static void split(String[] connections) {
        int i = 0;
        startCities = new String[connections.length];
        destinationCities = new String[connections.length];
        distance = new int[connections.length];
        time = new int[connections.length];
        String[][] parts = new String[4][connections.length];
        while (i < 4) {
            for (int j = 0; j < connections.length; j = j + 4) {
                parts[i] = connections[j].split(";", 1);
            }
            i++;
        }
        for (int j = 0; j < connections.length; j++) {
            startCities[j] = parts[0][j];
            destinationCities[j] = parts[1][j];
            distance[j] = Integer.parseInt(parts[2][j]);
            time[j] = Integer.parseInt(parts[3][j]);
        }
    }

    private static String[] connections(String[] worldMap) {
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
package final01.serializer;

import final01.commandline.Terminal;
import final01.exceptions.FileSyntaxException;
import final01.graph.MapGraph;
import final01.graph.Vertex;

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
    private Serializer() {

    }



    private static boolean duplicates(final String[] worldMap) {
        Set<String> lump = new HashSet<String>();
        for (String entry : worldMap) {
            if (lump.contains(entry))
                return true;
            lump.add(entry);
        }
        return false;
    }

    // TODO: Mehrfachkanten + Schlingen überprüfen!
    private static boolean checkForMulti(final String[] worldmap) throws FileSyntaxException {
        split(connections(worldmap));
        for(int i = 0; i < worldmap.length; i++) {
            // Schlingen überprüfen
            if (startCities[i].equals(destinationCities[i])) {
                throw new FileSyntaxException("Schlinge gefunden!");
            }
        }
        return false;
    }
    public static boolean validate(String[] worldMap) {
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
            return !duplicates(worldMap) && checkForMulti(worldMap);
        } catch (FileSyntaxException e) {
            Terminal.printLine("Error, " + e.getMessage());
            System.exit(1);
        }
        return false;
    }

    private static void split(String[] connections) {
        startCities = new String[connections.length];
        destinationCities = new String[connections.length];
        distance = new int[connections.length];
        time = new int[connections.length];
        String[][] parts = new String[connections.length][4];
            for (int j = 0; j < connections.length; j++) {
                parts[j] = connections[j].split(";");
            }
        for (int j = 0; j < connections.length; j++) {
            startCities[j] = parts[j][0];
            destinationCities[j] = parts[j][1];
            distance[j] = Integer.parseInt(parts[j][2]);
            time[j] = Integer.parseInt(parts[j][3]);
        }
    }

    private static Vertex[] startVertices(String[] cities) {
        Vertex[] startVertices = new Vertex[cities.length];
        for (int i = 0; i < cities.length; i++) {
            startVertices[i] = new Vertex(cities[i]);
        }
        return startVertices;
    }

    private static Vertex[] destinationVertices(String[] cities) {
        Vertex[] destVertices = new Vertex[cities.length];
        for (int i = 0; i < cities.length; i++) {
            destVertices[i] = new Vertex(cities[i]);
        }
        return destVertices;
    }

    public static MapGraph initializeGraph(String[] worldmap) {
        return new MapGraph(cities(worldmap), startVertices(startCities), destinationVertices(destinationCities), distance, time);
    }

    private static String[] connections(String[] worldMap) {
        int i = cities(worldMap).length + 1;
        int l = i;
        int j = 0;
        while (i < worldMap.length) {
            i++;
            j++;
        }

        String[] connections = new String[j];
        for (int k = 0; k < j; k++) {
            connections[k] = worldMap[l];
            l++;
        }
        return connections;
        }

    private static String[] cities(String[] worldMap) {
        int i = 0;
            while (!worldMap[i].equalsIgnoreCase("--") && i < worldMap.length) {
                i++;
            }
        String[] cities = new String[i];
        System.arraycopy(worldMap, 0, cities, 0, i);
        return cities;
    }
}
package navi.serializer;

import navi.commandline.Terminal;
import navi.exceptions.FileSyntaxException;
import navi.graph.MapGraph;
import navi.graph.Vertex;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public final class Serializer {
    /**
     * Der regex, der beschreibt wie eine Kante auszusehen hat
     */
    private static final String REGEX_EDGE = "[A-Za-z-]+;[A-Za-z-]+;[0-9]+;[0-9]+";
    /**
     * Der Regex, der beschreibt wie ein Knoten aussehen muss.
     */
    private static final String REGEX_CITY = "[A-Za-z-]+";
    /**
     * Kanten werden in ihre Bestandteile zerlegt und Daten einer Kategorie
     * werden in einem separaten Array gespeichert.
     */
    private static String[] startCities;
    private static String[] destinationCities;
    private static int[] distance;
    private static int[] time;
    private static String[] cities;

    /**
     * privater Konstruktor um Instantiierung zu vermeiden
     */
    private Serializer() {

    }
    /**
     * Überprüft, ob eine Kante oder ein Knoten doppelt vorkommt.
     * @param worldMap Die Textdatei mit den Informationen über den Graph
     * @return true, wenn es Duplikate gibt, false, wenn nicht
     */
    private static boolean duplicates(final String[] worldMap) {
        Set<String> lump = new HashSet<String>();
        for (String entry : worldMap) {
            if (lump.contains(entry))
                return true;
            lump.add(entry);
        }
        return false;
    }

    /**
     * Überrüft den Graph auf Schlingen und Mehrfachkanten
     * @param worldmap  Die Textdatei mit den Informationen über den Graph
     * @return true wenn es welche gibt, false, wenn nicht
     */
    private static boolean checkForMulti(final String[] worldmap) {
        split(connections(worldmap));
        for (int i = 0; i < startCities.length; i++) {
            // Schlingen überprüfen
            if (startCities[i].equalsIgnoreCase(destinationCities[i])) {
                return true;
            }
        }
        for (int j = 0; j < startCities.length; j++) {
            for (int k = j + 1; k < startCities.length; k++) {
                if (startCities[j].equalsIgnoreCase(destinationCities[k]))
                    return true;
            }
        }
        /**
         * Wir konkatenieren start und ziel um auf duplikate zu prüfen
         */
        String[] connectedCities = new String[startCities.length];
        for (int l = 0; l < connectedCities.length; l++) {
            connectedCities[l] = startCities[l] + destinationCities[l];
        }
        Set<String> lump = new HashSet<>();
        for (String entry : connectedCities) {
            if (lump.contains(entry))
                return true;
            lump.add(entry);
        }
        return false;
    }

    private boolean checkForCoherent() {

        return true;
    }
    /**
     * Die Methode die genutzt wird, um die Textdatei auf Gültigkeit zu überprüfen
     * @param worldMap Die übergebene Textdatei als String Array nach nutzen von FileInputHelper
     * @return ob die Datei gültig war
     */
    public static boolean validate(String[] worldMap) {
        cities(worldMap);
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
            return !duplicates(worldMap) && !checkForMulti(worldMap);
        } catch (FileSyntaxException e) {
            Terminal.printLine("Error, " + e.getMessage());
            System.exit(1);
        }
        return false;
    }

    /**
     * Diese Methode zerstückelt die Kanten und setzt deren einzelne
     * Werte in die Klassenatribute
     * @param connections die Kanten die wir zerstückeln wollen
     */
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

    /**
     * Hiermit erstellen wir einen Start-Knoten für jede Stadt, wird nur für die
     * Initialisierung des Graphen genutzt
     * @param cities die Städte die wir aus der Textdatei gelesen haben
     * @return die Start-Knoten die wir damit ertstellt haben
     */
    private static Vertex[] startVertices(String[] cities) {
        Vertex[] startVertices = new Vertex[cities.length];
        for (int i = 0; i < cities.length; i++) {
            startVertices[i] = new Vertex(cities[i]);
        }
        return startVertices;
    }
    /**
     * Hiermit erstellen wir einen Ziel-Knoten für jede Stadt, wird nur für die
     * Initialisierung des Graphen genutzt
     * @param cities die Städte die wir aus der Textdatei gelesen haben
     * @return die Ziel-Knoten die wir damit ertstellt haben
     */
    private static Vertex[] destinationVertices(String[] cities) {
        Vertex[] destVertices = new Vertex[cities.length];
        for (int i = 0; i < cities.length; i++) {
            destVertices[i] = new Vertex(cities[i]);
        }
        return destVertices;
    }

    /**
     * Diese Methode wird genutzt um den Graph mithilfe der zerstückelten Infos aus der Textdatei
     * zu generieren
     * @param worldmap Die Textdatei, in der die Infos über den Graphen gespeichert sind
     * @return  Der Graph der mit den Klassenatributen von Serializer gebaut wird
     */
    public static MapGraph initializeGraph(String[] worldmap) {
        return new MapGraph(cities, startVertices(startCities),
                destinationVertices(destinationCities), distance, time);
    }

    /**
     * Mit dieser Methode werden gezielt die Kanten ausgelesen.
     * Wir starten erst nach dem --, dies garantieren wir durch den aufruf der cities methode.
     * @param worldMap Die Textdatei mit den Infos über die Welt
     * @return die Kanten
     */
    private static String[] connections(String[] worldMap) {
        int i = cities.length + 1;
        int l = i;
        int j = 0;
        while (i < worldMap.length) {
            i++;
            j++;
        }
        String[] connections = new String[j];
        System.arraycopy(worldMap, l, connections, 0, j);
        return connections;
    }

    /**
     * Mit dieser Methode werden gezielt die Knoten ausgelesen.
     * @param worldMap Die Textdatei mit den Informationen über den Graph
     * @return die Knoten.
     */
    private static void cities(String[] worldMap) {
        int i = 0;
        while (!worldMap[i].equalsIgnoreCase("--") && i < worldMap.length) {
            i++;
        }
        cities = new String[i];
        System.arraycopy(worldMap, 0, cities, 0, i);
    }
}
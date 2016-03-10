package edu.kit.informatik.serializer;

import edu.kit.informatik.graph.Vertex;
import edu.kit.informatik.exceptions.FileSyntaxException;
import edu.kit.informatik.exceptions.GraphSyntaxException;
import edu.kit.informatik.graph.MapGraph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Der Serializer ist zuständig für das Einlesen der Textdatei und überführen der Daten in die Graphstruktur
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
    private static boolean duplicates(final String[] worldMap) throws FileSyntaxException {
        Set<String> lump = new HashSet<>();
        for (String entry : worldMap) {
            if (lump.contains(entry))
                throw new FileSyntaxException("duplicate found!");
            lump.add(entry.toLowerCase());
        }
        return false;
    }

    /**
     * Überrüft den Graph auf Schlingen und Mehrfachkanten
     * @param worldmap  Die Textdatei mit den Informationen über den Graph
     * @return true wenn es welche gibt, false, wenn nicht
     */
    private static boolean checkForMulti(final String[] worldmap) throws GraphSyntaxException {
        split(connections(worldmap));
        for (int i = 0; i < startCities.length; i++) {
            // Schlingen überprüfen
            if (startCities[i].equalsIgnoreCase(destinationCities[i])) {
                throw new GraphSyntaxException("loop found!");
            }
        }
        // Keine Knoten ohne Kanten!
        Set<String> startNodes = new HashSet<>();
        startNodes.addAll(Arrays.asList(startCities));
        Set<String> endNodes = new HashSet<>();
        endNodes.addAll(Arrays.asList(destinationCities));
        for (String element : cities)
            if (!(startNodes.contains(element) || endNodes.contains(element)))
                throw new GraphSyntaxException("node without edge found!");

        Set<String> nodes = new HashSet<>();
        nodes.addAll(Arrays.asList(cities));
        for (String element : startNodes)
                if (!nodes.contains(element))
                    throw new GraphSyntaxException("edge without node found!");
        for (String element : endNodes)
            if (!nodes.contains(element))
                throw new GraphSyntaxException("edge without node found!");
        /**
         * Wir konkatenieren start und ziel um auf duplikate zu prüfen
         */
        String[] connectedCities1 = new String[startCities.length];
        String[] connectedCities2 = new String[startCities.length];
        for (int l = 0; l < connectedCities1.length; l++) {
            connectedCities1[l] = startCities[l] + destinationCities[l];
            connectedCities2[l] = destinationCities[l] + startCities[l];
        }
        Set<String> lump1 = new HashSet<>();
        for (String entry : connectedCities1) {
            if (lump1.contains(entry))
                throw new GraphSyntaxException("multi edge found!");
            lump1.add(entry);
        }
        Set<String> lump2 = new HashSet<>();
        for (String entry : connectedCities2) {
            if (lump2.contains(entry) || lump1.contains(entry))
                throw new GraphSyntaxException("multi edge found!");
            lump2.add(entry);
        }
        return false;
    }
    /**
     * Die Methode die genutzt wird, um die Textdatei auf Gültigkeit zu überprüfen
     * @param worldMap Die übergebene Textdatei als String Array nach nutzen von FileInputHelper
     * @return ob die Datei gültig war
     * @throws FileSyntaxException Falls die Datei ungültig war wird dies der Main Methode mitgeteilt
     * @throws GraphSyntaxException Falls der Graph nicht gegebenen Bedingungen
     * "ungerichtet, schlingenfrei, eindeutig" erfüllt.
     */
    public static boolean validate(String[] worldMap) throws FileSyntaxException, GraphSyntaxException {
        cities(worldMap);
        int i = 0;
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
     * @throws GraphSyntaxException falls der Graph nicht zusammenhängend ist.
     */
    public static MapGraph initializeGraph(String[] worldmap) throws GraphSyntaxException {
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
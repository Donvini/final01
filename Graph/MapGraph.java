package navi.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Diese klasse stellt die Datenstruktur des Graphen dar.
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class MapGraph extends Graph {

    /**
     * Hashmap, die jedem Knoten seine zugehörigen Kanten zuordnet. Hauptstruktur.
     */
    //TODO: überprüfen ob Graph zusammenhängend ist
    private HashMap<Vertex, ArrayList<Edge>> world = new HashMap<>();
    /**
     * Extra Liste der Knoten um schnellen zugriff zu gewährleisten.
     */
    private List<Vertex> vertices = new ArrayList<>();
    /**
     * Extra Liste der Kanten um Schnellen zugriff zu gewährleisten und einfach zu erweitern.
     */
    private List<Edge> edges = new ArrayList<>();


    /**
     * Konstruktor der Klasse.
     * @param cities Die Namen und Anzahl der Knoten die erstellt werden müssen.
     * @param startCities alle Startknoten für die Kanten
     * @param destinationCities alle Zielknoten für die Kanten
     * @param km alle Distanzen für die Kanten
     * @param time alle Zeitabstände für die Kanten
     */
    public MapGraph(String[] cities, Vertex[] startCities, Vertex[] destinationCities, int[] km, int[] time) {
        for (String city : cities) {
            this.vertices.add(new Vertex(city));
        }
        for (int i = 0; i < km.length; i++) {
            this.edges.add(new Edge(startCities[i], destinationCities[i], km[i], time[i]));
        }
        for (Vertex element : vertices) {
            for (int j = 0; j <= vertices.size(); j++) {
                try {
                    if (startCities[j].getName().equals(element.getName())
                            || destinationCities[j].getName().equals(element.getName())) {
                        element.getEdges().add(new Edge(startCities[j], destinationCities[j],
                                km[j], time[j]));
                        element.getEdges().add(new Edge(destinationCities[j], startCities[j],
                                km[j], time[j]));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }
            this.world.put(element, element.getEdges());
        }
    }

    @Override
    public Edge getEdge(Vertex v, Vertex w) {
        return null;
    }

    @Override
    public Vertex getVertex() {
        return null;
    }

    @Override
    public Vertex[] vertices() {
        return new Vertex[0];
    }

    /**
     *
     * @return die Welt mit allen Städten und Verbindungen.
     */
    public HashMap<Vertex, ArrayList<Edge>> getWorld() {
        return world;
    }

    @Override
    public List<Edge> getEdges(Vertex v) {
        return null;
    }

    @Override
    public List<Vertex> getNeighbours(Vertex v) {
        return null;
    }

    @Override
    public Vertex getVertexByName(String name) {
        return null;
    }

    @Override
    public int getNumEdges() {
        return 0;
    }

    @Override
    public int getNumVertices() {
        return 0;
    }

    @Override
    public int search(Vertex v, Vertex w, String criterion) {
        return 0;
    }

    @Override
    public Vertex[] route(Vertex v, Vertex w) {
        return new Vertex[0];
    }

    @Override
    public void insertEdge(Vertex v, Vertex w, int distance, int time) {

    }

    @Override
    public void removeEdge(Vertex v, Vertex w) {

    }
}
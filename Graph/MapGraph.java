package navi.graph;

import navi.algorithms.DeepSearch;
import navi.commandline.Terminal;
import navi.exceptions.InvalidOperationException;
import navi.exceptions.NoSuchEntryException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Diese klasse stellt die Datenstruktur des Graphen dar.
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class MapGraph {

    /**
     * Hashmap, die jedem Knoten seine zugehörigen Kanten zuordnet. Hauptstruktur.
     */
    //TODO: überprüfen ob Graph zusammenhängend ist
    private HashMap<Vertex, ArrayList<Edge>> world = new HashMap<>();
    /**
     * Extra Liste der Knoten um schnellen zugriff zu gewährleisten.
     */
    private HashSet<Vertex> vertices = new HashSet<>();
    /**
     * Extra Liste der Kanten um Schnellen zugriff zu gewährleisten und einfach zu erweitern.
     */
    private ArrayList<Edge> edges = new ArrayList<>();

    /**
     * Alle besuchten knoten kommen hier rein
     */
    private HashSet<Vertex> visited = new HashSet<>();

    /**
     * Hier werden die wege entlang des Graphen gespeichert.
     */
    private HashMap<Vertex, Vertex> parents = new HashMap<>();


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
            this.edges.add(new Edge(destinationCities[i], startCities[i], km[i], time[i]));
        }
        for (Vertex element : vertices) {
            for (int j = 0; j <= vertices.size(); j++) {
                try {
                    if (startCities[j].getName().equals(element.getName())) {
                        element.getEdges().add(new Edge(startCities[j], destinationCities[j],
                                km[j], time[j]));
                        element.getEdges().add(new Edge(destinationCities[j], startCities[j],
                                km[j], time[j]));
                        element.getNeighbours().add(destinationCities[j]);
                    }
                    else if (destinationCities[j].getName().equals(element.getName())) {
                        element.getEdges().add(new Edge(destinationCities[j], startCities[j],
                                km[j], time[j]));
                        element.getEdges().add(new Edge(startCities[j], destinationCities[j],
                                km[j], time[j]));
                        element.getNeighbours().add(startCities[j]);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }
            this.world.put(element, element.getEdges());
        }

    }

    public void deepSearchRec(String v, String w) throws NoSuchEntryException {
        if (getVertexByName(v) != null && getVertexByName(w) != null) {
            DeepSearch.dfsRec(getVertexByName(v),
                    getVertexByName(w), visited, parents);
            parents.clear();
            visited.clear();

        }
        else throw new NoSuchEntryException("at least one of the nodes does not exist!");
    }
    public boolean isConnected() throws NoSuchEntryException {
        HashSet<Vertex> visited = new HashSet<>();
        HashMap<Vertex, Vertex> parents = new HashMap<>();


        return true;

    }

    public void deepNiko(String v, String w) {
        ArrayList<Vertex> route = new ArrayList<>();
        ArrayList<ArrayList<Vertex>> path = DeepSearch.dfsNiko(route, getVertexByName(v), getVertexByName(w));
        for (ArrayList<Vertex> element : path
             ) {
            for (Vertex pe :
                    element) {
                Terminal.printLine(pe.toString());
            }
        }
    }
    /**
     * methode um alle Knoten auszugeben.
     */
    public void vertices() {
        for (Vertex element : this.vertices) {
            Terminal.printLine(element.toString());
        }
    }

    /**
     * Gibt die Nachbarknoten vom Knoten v aus.
     * @param v der Name des Knoten dessen Nachbarn ausgegeben werden.
     */
    public void nodes(String v) {
        try {
            for (Vertex element : getVertexByName(v).getNeighbours()) {
                Terminal.printLine(element.toString());
            }
        } catch (NullPointerException e) {
            Terminal.printLine("No node with this name found!");
        }
    }

    /**
     * Der Info-Befehl. Gibt der Reihe nach alle Knoten und dann alle Kanten aus.
     */
    public void info() {
        if (this.world.isEmpty())
            Terminal.printLine("");
        else {
            for (Vertex element : this.vertices)
                Terminal.printLine(element.toString());
            Terminal.printLine("--");
            for (int i = 0; i < this.edges.size(); i = i + 2) {
                Terminal.printLine(this.edges.get(i).toString());
            }
        }
    }


    /**
     *  Gibt den Knoten der zu einem Namen gehört aus.
     * @param name Der Name des gesuchten Knoten
     * @return liefert den entsprechenden Knoten
     */
    public Vertex getVertexByName(String name) {
        for (Vertex vertex : this.vertices) {
            if (vertex.getName().equalsIgnoreCase(name))
                return vertex;
        }
        return null;
    }
    private Edge getEdgeByVertices(Vertex v, Vertex w) {
        for (Edge edge : this.edges) {
            if ((edge.getStartNode().equals(v) && edge.getEndNode().equals(w))
                    || (edge.getStartNode().equals(w) && edge.getEndNode().equals(v)))
                return edge;
        }
        return null;
    }

    /**
     *
     * @return die Welt mit allen Städten und Verbindungen.
     */
    public HashMap<Vertex, ArrayList<Edge>> getWorld() {
        return world;
    }


    /**
     * Methode um dem Graphen Kanten hinzuzufügen
     * @param v der Startknoten
     * @param w der Zielknoten
     * @param distance Abstand in km
     * @param time Entfernung in Zeit
     * @throws NoSuchEntryException Falls die beiden übergebenen Knoten nicht existieren.
     * @throws InvalidOperationException Falls es bereits eine Kante gibt.
     */
    public void insertEdge(String v, String w, String distance, String time)
            throws InvalidOperationException, NoSuchEntryException {

        // Wenn es die beiden Knoten gibt
        if (this.vertices.contains(getVertexByName(v))
                && this.vertices.contains(getVertexByName(w))) {
            // Aber noch keine Kante zwischen Knoten 1 und Knoten 2
            if (!this.edges.contains(new Edge(getVertexByName(v), getVertexByName(w),
                    Integer.parseInt(distance), Integer.parseInt(time)))) {
                // Dann füge diese Kante hinzu.
                this.edges.add(new Edge(getVertexByName(v), getVertexByName(w),
                        Integer.parseInt(distance), Integer.parseInt(time)));
                this.edges.add(new Edge(getVertexByName(w), getVertexByName(v),
                        Integer.parseInt(distance), Integer.parseInt(time)));
                Terminal.printLine("OK");
            }
            else
                throw new InvalidOperationException("There is already an edge.");
        }


        else if ((this.vertices.contains(getVertexByName(v))
                && !this.vertices.contains(getVertexByName(w)))
                || this.vertices.contains(getVertexByName(w))
                && !this.vertices.contains(getVertexByName(v))) {
            if (getVertexByName(v) == null)
                this.vertices.add(new Vertex(v));
            if (getVertexByName(w) == null)
                this.vertices.add(new Vertex(w));
            this.edges.add(new Edge(getVertexByName(v), getVertexByName(w),
                    Integer.parseInt(distance), Integer.parseInt(time)));
            this.edges.add(new Edge(getVertexByName(w), getVertexByName(v),
                    Integer.parseInt(distance), Integer.parseInt(time)));
            getVertexByName(w).getEdges().add(new Edge(getVertexByName(v), getVertexByName(w),
                    Integer.parseInt(distance), Integer.parseInt(time)));
            getVertexByName(w).getEdges().add(new Edge(getVertexByName(w), getVertexByName(v),
                    Integer.parseInt(distance), Integer.parseInt(time)));
            getVertexByName(w).getNeighbours().add(getVertexByName(v));
            getVertexByName(v).getNeighbours().add(getVertexByName(w));
            this.world.put(getVertexByName(w), getVertexByName(w).getEdges());
            Terminal.printLine("OK");
        }
        else if (!(this.vertices.contains(getVertexByName(v))
                && this.vertices.contains(getVertexByName(w))))
            throw new NoSuchEntryException("both nodes do not exist");
    }

    /**
     * Methode um eine Verbindung, falls möglich, zu entfernen
     * @param v Stadt 1
     * @param w Stadt 2
     * @throws NoSuchEntryException falls die gesuchte Verbindung nicht existiert.
     */
    public void remove(String v, String w) throws NoSuchEntryException {
        if ((getVertexByName(v) != null
                && getVertexByName(w) != null)
                && getEdgeByVertices(new Vertex(v), new Vertex(w)) != null) {
            this.edges.remove(getEdgeByVertices(new Vertex(v), new Vertex(w)));
            this.edges.remove(getEdgeByVertices(new Vertex(w), new Vertex(v)));
            getVertexByName(v).getNeighbours().remove(getVertexByName(w));
            getVertexByName(w).getNeighbours().remove(getVertexByName(v));
            getVertexByName(v).getEdges().remove(getEdgeByVertices(new Vertex(v), new Vertex(w)));
            getVertexByName(w).getEdges().remove(getEdgeByVertices(new Vertex(w), new Vertex(v)));
            if (getVertexByName(v).getNeighbours().isEmpty())
                this.vertices.remove(getVertexByName(v));
            if (getVertexByName(w).getNeighbours().isEmpty())
                this.vertices.remove(getVertexByName(w));
            Terminal.printLine("OK");
        }
        else
            throw new NoSuchEntryException("There is no connection or one of the nodes does not exist.");
    }
}
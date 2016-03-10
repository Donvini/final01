package navi.graph;

import navi.algorithms.DeepSearch;
import navi.commandline.Terminal;
import navi.exceptions.GraphSyntaxException;
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
     * Konstruktor der Klasse.
     * @param cities Die Namen und Anzahl der Knoten die erstellt werden müssen.
     * @param startCities alle Startknoten für die Kanten
     * @param destinationCities alle Zielknoten für die Kanten
     * @param km alle Distanzen für die Kanten
     * @param time alle Zeitabstände für die Kanten
     * @throws GraphSyntaxException falls der Graph nicht zusammenhängend ist.
     */
    public MapGraph(String[] cities, Vertex[] startCities, Vertex[] destinationCities, int[] km, int[] time) throws GraphSyntaxException {
        for (String city : cities) {
            this.vertices.add(new Vertex(city));
        }
        for (int i = 0; i < km.length; i++) {
            this.edges.add(new Edge(getVertexByName(startCities[i].getName()),
                    getVertexByName(destinationCities[i].getName()), km[i], time[i]));
            this.edges.add(new Edge(getVertexByName(destinationCities[i].getName())
                    , getVertexByName(startCities[i].getName()), km[i], time[i]));
        }
        for (Vertex element : vertices) {
            for (int j = 0; j <= vertices.size(); j++) {
                try {
                    if (startCities[j].getName().equals(element.getName())) {
                        element.getEdges().add(new Edge(getVertexByName(startCities[j].getName())
                                , getVertexByName(destinationCities[j].getName()),
                                km[j], time[j]));
                        element.getEdges().add(new Edge(getVertexByName(destinationCities[j].getName())
                                , getVertexByName(startCities[j].getName()),
                                km[j], time[j]));
                        element.getNeighbours().add(getVertexByName(destinationCities[j].getName()));
                    }
                    else if (destinationCities[j].getName().equals(element.getName())) {
                        element.getEdges().add(new Edge(getVertexByName(destinationCities[j].getName())
                                , getVertexByName(startCities[j].getName()),
                                km[j], time[j]));
                        element.getEdges().add(new Edge(getVertexByName(startCities[j].getName())
                                , getVertexByName(destinationCities[j].getName()),
                                km[j], time[j]));
                        element.getNeighbours().add(getVertexByName(startCities[j].getName()));
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }
            this.world.put(element, element.getEdges());
        }
        if (!isConnected(startCities[0]))
            throw new GraphSyntaxException("graph is not connected!");
    }

    /**
     * Überprüft ob der Graph zusammenhängend ist.
     * @param root Beliebige Pseudowurzel im Graphen, schließlich muss von überall ein Pfad existieren.
     * @return true wenn ja, fehler wenn nein
     */
    public boolean isConnected(Vertex root) {
        ArrayList<Vertex> route = new ArrayList<>();
        for (Vertex element : vertices) {
            if (DeepSearch.dfsAll(this, route, root, element).size() == 0)
            return false;
        }
        return true;
    }

    /**
     * Sucht den Graphen nach allen Verbindungen zwischen zwei Städten ab
     * @param v Name des Startknoten
     * @param w name des Endknoten
     * @throws NoSuchEntryException falls einer davon nicht existiert.
     */
    public void searchAllPaths(String v, String w) throws NoSuchEntryException {
        if (getVertexByName(v) != null && getVertexByName(w) != null) {
            ArrayList<Vertex> route = new ArrayList<>();
            ArrayList<ArrayList<Vertex>> path = DeepSearch.dfsAll(this, route, getVertexByName(v), getVertexByName(w));
            for (ArrayList<Vertex> partList : path) {
                String output = "";
                for (Vertex element : partList) {
                    output += (element.getName() + " ");
                }
                Terminal.printLine(output);
            }
        }
        else throw new NoSuchEntryException("at least one of the nodes does not exist!");
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
        if (this.vertices.isEmpty())
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
     * @throws InvalidOperationException wegen insert.
     */
    public void remove(String v, String w) throws NoSuchEntryException, InvalidOperationException {
        try {
            if ((getVertexByName(v) != null
                    && getVertexByName(w) != null)
                    && getEdgeByVertices(getVertexByName(v), getVertexByName(w)) != null) {
                int timeBackup = getEdgeByVertices(getVertexByName(v), getVertexByName(w)).getTime();
                int distBackup = getEdgeByVertices(getVertexByName(v), getVertexByName(w)).getDistance();
                this.edges.remove(getEdgeByVertices(getVertexByName(v), getVertexByName(w)));
                this.edges.remove(getEdgeByVertices(getVertexByName(w), getVertexByName(v)));
                getVertexByName(v).getNeighbours().remove(getVertexByName(w));
                getVertexByName(w).getNeighbours().remove(getVertexByName(v));
                getVertexByName(v).getEdges().remove(getEdgeByVertices(getVertexByName(v), getVertexByName(w)));
                getVertexByName(w).getEdges().remove(getEdgeByVertices(getVertexByName(w), getVertexByName(v)));

                if (!isConnected(getVertexByName(v)) && !getVertexByName(v).getNeighbours().isEmpty()
                        && !getVertexByName(w).getNeighbours().isEmpty()) {
                    getVertexByName(v).getEdges().add(new Edge(getVertexByName(v), getVertexByName(w),
                            distBackup, timeBackup));
                    getVertexByName(w).getEdges().add(new Edge(getVertexByName(w), getVertexByName(v),
                            distBackup, timeBackup));
                    getVertexByName(v).getNeighbours().add(getVertexByName(w));
                    getVertexByName(w).getNeighbours().add(getVertexByName(v));
                    this.edges.add(new Edge(getVertexByName(v), getVertexByName(w),
                            distBackup, timeBackup));
                    new Edge(getVertexByName(w), getVertexByName(v),
                            distBackup, timeBackup);
                    throw new GraphSyntaxException("graph would not be connected anymore.");
                }
                if (getVertexByName(v).getNeighbours().isEmpty())
                    this.vertices.remove(getVertexByName(v));
                if (getVertexByName(w).getNeighbours().isEmpty())
                    this.vertices.remove(getVertexByName(w));
                Terminal.printLine("OK");
            }
            else
                throw new NoSuchEntryException("There is no connection or one of the nodes does not exist.");
        } catch (GraphSyntaxException e) {
            Terminal.printLine("Error, " + e.getMessage());
        }
    }
}
package final01.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class MapGraph extends Graph {

    HashMap<Vertex, ArrayList<Edge>> world = new HashMap<>();
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();


    public MapGraph(String[] cities, Vertex[] startCities, Vertex[] destinationCities, int[] km, int[] time) {
        for (String city : cities) {
            this.vertices.add(new Vertex(city));
        }
        for (int i = 0; i < km.length; i++) {
            this.edges.add(new Edge(startCities[i], destinationCities[i], km[i], time[i]));
        }
        for (Vertex element : vertices) {
            for (int j = 0; j <= vertices.size(); j++) {
                if (startCities[j].getName().equals(element.getName()))
                    element.getEdges().add(new Edge(element, destinationCities[j],
                            km[j], time[j]));
                else if (destinationCities[j].getName().equals(element.getName()))
                    element.getEdges().add(new Edge(startCities[j], element,
                            km[j], time[j]));
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
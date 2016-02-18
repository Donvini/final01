package final01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class GraphList extends Graph{
    HashMap<Vertex, ArrayList<Edge>> graph = new HashMap<>();

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

    public HashMap<Vertex, ArrayList<Edge>> getGraph() {
        return graph;
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

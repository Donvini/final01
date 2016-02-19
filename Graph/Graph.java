package final01.Graph;

import java.util.List;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public abstract class Graph {
    private Vertex vertices;
    private int numVertices;
    private int numEdges;

    public abstract List<Vertex> getNeighbours(Vertex v);
    public abstract List<Edge> getEdges(Vertex v);
    public abstract Edge getEdge(Vertex v, Vertex w);
    public abstract int getNumVertices();
    public abstract int getNumEdges();
    public abstract Vertex getVertex();
    public abstract Vertex[] vertices();
    public abstract Vertex getVertexByName(String name);

    public abstract int search(Vertex v, Vertex w, String criterion);
    public abstract Vertex[] route(Vertex v, Vertex w);
    public abstract void removeEdge(Vertex v, Vertex w);
    public abstract void insertEdge(Vertex v, Vertex w, int distance, int time);



}

package navi.graph;

import java.util.List;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 * abstrakte Klasse um festzulegen was der Graph alles können soll
*/
public abstract class Graph {

    public abstract List<Vertex> getNeighbours(Vertex v);
    public abstract List<Edge> getEdges(Vertex v);
    public abstract Edge getEdge(Vertex v, Vertex w);
    public abstract int getNumVertices();
    public abstract int getNumEdges();
    public abstract Vertex getVertex();
    public abstract void vertices();
    public abstract Vertex getVertexByName(String name);

    public abstract void info();
    public abstract int search(Vertex v, Vertex w, String criterion);
    public abstract Vertex[] route(Vertex v, Vertex w);
    public abstract void removeEdge(Vertex v, Vertex w);
    public abstract void insertEdge(Vertex v, Vertex w, int distance, int time);
    public abstract void nodes(String name);

}
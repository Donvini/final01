package final01.graph;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class Edge {

    private Vertex startNode;
    private Vertex endNode;
    private int distance;
    private int time;

    public Edge(Vertex start, Vertex end, int distance, int time) {
        this.startNode = start;
        this.endNode = end;
        this.distance = distance;
        this.time = time;
    }

    @Override
    public String toString() {
        return this.startNode.toString() + ";" + this.endNode.toString() + ";"
                + this.distance + ";" + this.time;
    }
}

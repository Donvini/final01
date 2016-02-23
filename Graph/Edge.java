package final01.Graph;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class Edge {

    private Vertex startNode;
    private Vertex endNode;
    private double distance;
    private double time;

    public Edge(Vertex start, Vertex end, double distance, double time) {
        this.startNode = start;
        this.endNode = end;
        this.distance = distance;
        this.time = time;
    }
}

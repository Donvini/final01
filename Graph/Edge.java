package navi.graph;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class Edge {

    /**
     * Die Attribute jeder Kante
     */
    private Vertex startNode;
    private Vertex endNode;
    private int distance;
    private int time;

    /**
     * Konstruktor der Klasse.
     * @param start Startknoten
     * @param end Zielknoten
     * @param distance Abstand in km
     * @param time Abstand in Zeit
     */
    public Edge(Vertex start, Vertex end, int distance, int time) {
        this.startNode = start;
        this.endNode = end;
        this.distance = distance;
        this.time = time;
    }

    /**
     *
     * @return eine Kante mit der ursprünglichen Regex spezifikation.
     */
    @Override
    public String toString() {
        return this.startNode.toString() + ";" + this.endNode.toString() + ";"
                + this.distance + ";" + this.time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (distance != edge.distance) return false;
        if (time != edge.time) return false;
        if (!startNode.equals(edge.startNode)) return false;
        return endNode.equals(edge.endNode);

    }

    @Override
    public int hashCode() {
        int result = startNode.hashCode();
        result = 31 * result + endNode.hashCode();
        result = 31 * result + distance;
        result = 31 * result + time;
        return result;
    }
}

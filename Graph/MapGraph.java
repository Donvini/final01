package final01.graph;

import java.util.HashMap;
import java.util.List;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class MapGraph {

    HashMap<Vertex, List<Edge>> world = new HashMap<>();
    private List<Vertex> vertices;
    private List<Edge> edges;

    private int numVertices;
    private int numEdges;

    public MapGraph(String[] cities, Vertex[] startCities, Vertex[] destinationCities, int[] km, int[] time) {
        for (String city : cities) {
            Vertex node = new Vertex(city);
            this.vertices.add(node);
        }
        for(int i = 0; i < km.length; i++) {
            this.edges.add(new Edge( startCities[i], destinationCities[i], km[i], time[i]));
        }
        for (Vertex element : vertices) {
            for (int j = 0; j < vertices.size(); j++) {
                if (startCities[j].getName().equals(element.getName())
                        || destinationCities[j].getName().equals(element.getName()))
                    this.world.put(element, this.edges);
            }
        }
    }

    }
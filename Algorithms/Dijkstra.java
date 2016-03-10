package edu.kit.informatik.algorithms;

import edu.kit.informatik.graph.Edge;
import edu.kit.informatik.graph.Vertex;
import edu.kit.informatik.graph.MapGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Klasse für den Dijkstra Algorithmus
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public final class Dijkstra {
    private Dijkstra() {
    }

    /**
     * Berechnet den kürzestens Pfad nach gewähltem Kriterium.
     * @param g der Graph der traversiert wird.
     * @param start da fangen wir an
     * @param criterion danach suchen wir.
     */
    public static void shortestPath(MapGraph g, Vertex start, String criterion) {
        PriorityQueue<Vertex> p = new PriorityQueue<>();
        for (Vertex v : g.getVertices()) {
            v.setDistance(Integer.MAX_VALUE);
            v.setVisited(false);
            v.setPrev(null);
        }
        start.setDistance(0);
        p.add(start);
        while (!p.isEmpty()) {
            int c = 0;
            Vertex v = p.poll();
            if (!v.isVisited())
                v.setVisited(true);
            for (Edge e : v.getEdges()){
                Vertex w = e.getEndNode();
                if (criterion.equalsIgnoreCase("route")) {
                     c = e.getDistance();
                }
                else if (criterion.equalsIgnoreCase("time")) {
                    c = e.getTime();
                }
                int distSoFar = c + v.getDistance();
                if (w.getDistance() > distSoFar) {
                    w.setDistance(distSoFar);
                    w.setPrev(v);
                    p.add(w);
                }
            }
        }
    }

    /**
     * Liefert alle zwischenschritte bis zum Ziel
     * @param goal der Zielknoten
     * @return alle Zwischenschritte
     */
    public static ArrayList<Vertex> getShortestPathTo(Vertex goal) {
        ArrayList<Vertex> path = new ArrayList<>();
        for (Vertex v = goal; v != null; v = v.getPrev()) {
        path.add(v);
        }
        Collections.reverse(path);
        return path;
    }
}
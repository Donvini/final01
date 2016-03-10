package edu.kit.informatik.algorithms;

import edu.kit.informatik.graph.Vertex;
import edu.kit.informatik.graph.MapGraph;

import java.util.*;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 *
 * Hashset für visited nodes, HashMap für die nodes von denen sie vorher kam
 */
public final class DeepSearch {

    /**
     * privater Konstruktor um Instantiierung zu vermeiden.
     */
    private DeepSearch() {
    }

    /**
     * @param start Der Knoten von dem aus wir unseren Pfad beginnen.
     * @param goal  der Knoten den wir erreichen wollen.
     */
    public static void dfs(Vertex start, Vertex goal) {
        Stack<Vertex> stack = new Stack<>();
        HashSet<Vertex> visited = new HashSet<>();
        HashMap<Vertex, Vertex> parent = new HashMap<>();
        stack.push(start);
        visited.add(start);
        while (!stack.isEmpty()) {
            Vertex curr = stack.pop();
            if (curr.equals(goal))
                return;
            for (Vertex n :
                    curr.getNeighbours()) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    parent.put(curr, n);
                    stack.add(n);
                }
            }
        }
    }

    /**
     * Rekursive Tiefensuche
     *
     * @param s
     * @param g
     * @param visited
     * @param parents
     * @return
     */
    public static void dfsRec(MapGraph graph, Vertex s, Vertex g, HashSet<Vertex> visited, HashMap<Vertex, Vertex> parents) {
        Vertex curr = graph.getVertexByName(s.getName());
        visited.add(curr);
        if (curr.equals(g)) {
         System.out.println(parents.values());
            return;
        }
        for (Vertex n : curr.getNeighbours()) {
            if (!visited.contains(n)) {
                visited.add(n);
                parents.put(curr, n);
                dfsRec(graph, n, g, visited, parents);
            }
        }
    }


    /**
     * Liefert alle Pfade zwischen zwei Städten
     * @param g der Graph auf dem sich alles abspielt
     * @param route die Route in die gespeichert wird
     * @param start Startstadt
     * @param goal Zielstadt
     * @return Alle möglichen Pfade.
     */
    public static ArrayList<ArrayList<Vertex>> dfsAll(MapGraph g, ArrayList<Vertex> route, Vertex start, Vertex goal) {
            Vertex curr = g.getVertexByName(start.getName());
            route.add(curr);
            ArrayList<ArrayList<Vertex>> path = new ArrayList<>();

            if (curr.equals(g.getVertexByName(goal.getName()))) {
                path.add(route);
                return path;
            }
            for (Vertex n : curr.getNeighbours()) {
                if (!route.contains(n)) {
                    ArrayList<Vertex> newRoute = new ArrayList<>();
                    newRoute.addAll(route);
                    path.addAll(dfsAll(g, newRoute, n, goal));
                }

            }
            return path;
        }
}
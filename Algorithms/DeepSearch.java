package navi.algorithms;

import navi.commandline.Terminal;
import navi.graph.Edge;
import navi.graph.MapGraph;
import navi.graph.Vertex;

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
    public static void dfsRec(Vertex s, Vertex g, HashSet<Vertex> visited, HashMap<Vertex, Vertex> parents) {
        visited.add(s);
        if (s.equals(g)) {
            Terminal.printLine(parents.keySet().toString());
            return;
        }
        for (Vertex n :
                s.getNeighbours()) {
            if (!visited.contains(n)) {
                visited.add(n);
                parents.put(s, n);
                dfsRec(n, g, visited, parents);
            }
        }
    }

    public static ArrayList<Vertex> dfsWiki(MapGraph g, Vertex v) {
        HashSet<Vertex> visited = new HashSet<>();
        visited.add(v);
        for (Vertex n :
                v.getNeighbours()) {
            if (!visited.contains(n))
                return dfsWiki(g, n);
        }
        return null;
    }


    public static ArrayList<ArrayList<Vertex>> dfsNiko(ArrayList<Vertex> route, Vertex start, Vertex goal) {
        route.add(start);
        ArrayList<ArrayList<Vertex>> path = new ArrayList<>();
        System.out.println("aktueller Knoten: " + start);
        System.out.println("aktuelle Route: " + route);
        System.out.println("Aktueller Pfad: " + path);
        if (start.equals(goal)) {
            path.add(route);
            return path;
        }
        System.out.println(start.getNeighbours());
        for (Vertex n :
                start.getNeighbours()) {
            if (!route.contains(n)) {
                ArrayList<Vertex> newRoute = new ArrayList<>();
                newRoute.addAll(route);
                System.out.println("Neuer Knoten: " + n);
                System.out.println("Neue Route" + newRoute);
                path.addAll(dfsNiko(newRoute, n, goal));
            }

        }
        return path;
    }
}
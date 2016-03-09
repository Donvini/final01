package navi.algorithms;

import navi.graph.MapGraph;
import navi.graph.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

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
    private DeepSearch(){
    }

    /**
     *
     * @param start Der Knoten von dem aus wir unseren Pfad beginnen.
     * @param goal der Knoten den wir erreichen wollen.
     */
    public HashMap<Vertex, Vertex> dfs(Vertex start, Vertex goal) {
        Stack<Vertex> stack = new Stack<>();
        HashSet<Vertex> visited = new HashSet<>();
        HashMap<Vertex, Vertex> parent = new HashMap<>();
        stack.add(start);
        visited.add(start);
        while (!stack.isEmpty()) {
            Vertex curr = stack.pop();
            if (curr.equals(goal))
                return parent;
            for (Vertex n :
                 curr.getNeighbours()) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    parent.put(n, curr);
                    stack.add(n);
                }
            }
        }
        return null;
    }
}

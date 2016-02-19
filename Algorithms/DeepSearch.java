package final01.Algorithms;

import final01.Graph.Vertex;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 *
 * Hashset für visited nodes, HashMap für die partent nodes
 */
public class DeepSearch {
    HashSet<Vertex> visited = new HashSet<>();
    HashMap<Vertex, Vertex> parent = new HashMap<>();
}

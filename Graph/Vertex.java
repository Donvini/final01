package edu.kit.informatik.graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Diese Klasse representiert eine Stadt in unserem Navigationssystem.
 *
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class Vertex implements Comparable {

    // name um Stadt zu identifizieren
    private String name;

    // bool für Dijkstra
    private boolean isVisited;
    private int distance;
    private Vertex prev;


    private ArrayList<Edge> edges = new ArrayList<>();
    private LinkedList<Vertex> neighbours = new LinkedList<>();

    /**
     * Konstruktor der Klasse
     * @param name der Name anhand dessen man den Knoten später identifiziert.
     */
    public Vertex(String name) {
        this.name = name;
        this.isVisited = false;
        this.distance = 0;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Vertex getPrev() {
        return prev;
    }

    public void setPrev(Vertex prev) {
        this.prev = prev;
    }

    /**
     * Methode um alle Kanten eines Knotens zu erhalten
     * @return alle Kanten des Knoten
     */
    public ArrayList<Edge> getEdges() {
        return edges;
    }

    /**
     *
     * @return alle nachbarn des Knoten
     */
    public LinkedList<Vertex> getNeighbours() {
        return neighbours;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    /**
     *
     * @return den Namen des Knoten
     */
    public String getName() {
        return this.name;
    }

    /**
     * Bool der später für Tiefensuche genutzt wird.
     * @return true wenn besucht, false wenn nicht.
     */
    public boolean isVisited() {
        return isVisited;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex vertex = (Vertex) o;

        return name.equalsIgnoreCase(vertex.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        Vertex vertex = (Vertex) o;
        return Integer.compare(this.getDistance(), vertex.getDistance());
    }
}

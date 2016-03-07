package navi.graph;

import java.util.ArrayList;

/**
 * Diese Klasse representiert eine Stadt in unserem Navigationssystem.
 *
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class Vertex {

    // name um Stadt zu identifizieren
    private String name;

    // bool für Tiefensuche
    private boolean isVisited;


    // TODO: Nachbarliste einbauen
    private ArrayList<Edge> edges = new ArrayList<>();
    private ArrayList<Vertex> neighbours = new ArrayList<>();

    /**
     * Konstruktor der Klasse
     * @param name der Name anhand dessen man den Knoten später identifiziert.
     */
    public Vertex(String name) {
        this.name = name;
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
    public ArrayList<Vertex> getNeighbours() {
        return neighbours;
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

    /**
     * Override der toString Methode um einen Knoten ausgeben zu können.
     * @return den namen des Strings für die Ausgabe
     */
    @Override
    public String toString() {
        return this.name;
    }
}

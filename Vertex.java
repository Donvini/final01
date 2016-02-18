package final01;

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

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return isVisited;
    }
}

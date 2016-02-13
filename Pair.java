package final01;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 * @param <T>
 * @param <U>
 */
public class Pair<T, U> {

    private T first;
    private U second;

    /**
     * Konstruktor der Klasse Pair
     * @param first eines Objektes wird initalisiert
     * @param second eines Objektes wird initialisiert
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Getter Methode für first
     * @return first
     */
    public T getFirst() {
        return first;
    }

    /**
     * Getter Methode für second
     * @return second
     */
    public U getSecond() {
        return second;
    }
}

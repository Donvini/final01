package navi.exceptions;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class GraphSyntaxException extends Exception{
    static final long serialVersionUID = 654765745L;

    /**
     * Konstruktor der Exception
     * @param message die Fehlermeldung, die ausgegeben werden soll.
     */
    public GraphSyntaxException(String message) {
        super(message);
    }
}

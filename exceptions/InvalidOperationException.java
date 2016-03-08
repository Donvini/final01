package navi.exceptions;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class InvalidOperationException extends Exception {
    static final long serialVersionUID = 68674L;

    /**
     * Konstukor der Exception
     * @param message die Fehlermeldung
     */
    public InvalidOperationException(String message) {
        super(message);
    }
}

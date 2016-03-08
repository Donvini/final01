package navi.exceptions;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class UserInputException extends Exception{
    static final long serialVersionUID = 3453453453L;

    /**
     * Konstruktor der Exception
     * @param message Die Fehlermeldung
     */
    public UserInputException(String message) {
        super(message);
    }
}

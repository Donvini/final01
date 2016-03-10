package edu.kit.informatik.exceptions;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class NoSuchEntryException extends Exception {
    static final long serialVersionUID = 1283891230981273L;

    /**
     * Konstruktor der Exception
     * @param message Die Fehlermeldung
     */
    public NoSuchEntryException(String message) {
        super(message);
    }
}

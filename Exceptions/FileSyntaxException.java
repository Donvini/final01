package edu.kit.informatik.exceptions;

import java.io.IOException;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class FileSyntaxException extends IOException {
    static final long serialVersionUID = 123123151123L;

    /**
     * Konstukor der Exception
     * @param message die Fehlermeldung
     */
    public FileSyntaxException(String message) {
        super(message);
    }
}

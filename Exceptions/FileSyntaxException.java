package final01.Exceptions;

import java.io.IOException;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class FileSyntaxException extends IOException {
    static final long serialVersionUID = 123123151123L;

    public FileSyntaxException(String message) {
        super(message);
    }
}

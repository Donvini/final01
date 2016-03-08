package navi.exceptions;

/**
 * @author Vincenzo Pace | KIT
 * @version 1.0
 */
public class UserInputException extends IllegalArgumentException{
    static final long serialVersionUID = 3453453453L;

    public UserInputException(String message) {
        super(message);
    }
}

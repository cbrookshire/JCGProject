/*
 * Throws exception during login phase for both a bad username and/or password
 */
package JCGExceptions;

/**
 *
 * @author taylor
 */
public class InvalidUserException extends Exception {
    public InvalidUserException(String message) {
        super(message);
    }
}

/*
 * This error is thrown when user tries to query outside their provileges
 */
package JCGExceptions;

/**
 *
 * @author taylor
 */
public class UnauthorizedUserException extends Exception {
    public UnauthorizedUserException(String message) {
        super(message);
    }
    
}

package nl.miwgroningen.ch10.topshelf.exception;

/**
 * @author Inge Dikland
 * <p>
 * Dit is wat het programma doet.
 */
public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException(String message) {
        super(message);
    }
}

package nl.miwgroningen.ch10.topshelf.exception;

/**
 * @author Inge Dikland
 * When Username is already used before, this will provide an exception error message
 */
public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException(String message) {
        super(message);
    }
}

package nl.miwgroningen.ch10.topshelf.exception;

/**
 * @author Inge Dikland
 * <p>
 * Dit is wat het programma doet.
 */
public class InvalidReCaptchaException extends RuntimeException {
    public InvalidReCaptchaException(String message) { super(message);
    }
}

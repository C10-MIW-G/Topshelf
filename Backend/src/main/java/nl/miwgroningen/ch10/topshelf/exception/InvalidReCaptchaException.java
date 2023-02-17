package nl.miwgroningen.ch10.topshelf.exception;

/**
 * @author Inge Dikland
 * When the ReCaptcha is not verified, this will provide an exception error message
 */
public class InvalidReCaptchaException extends RuntimeException {
    public InvalidReCaptchaException(String message) { super(message);
    }
}

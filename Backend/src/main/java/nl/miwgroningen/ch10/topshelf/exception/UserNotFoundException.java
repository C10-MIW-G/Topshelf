package nl.miwgroningen.ch10.topshelf.exception;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * When a user isn't found, this will be the exception message
 */

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}

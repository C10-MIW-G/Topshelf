package nl.miwgroningen.ch10.topshelf.exception;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * When an invalid password is given, this will provide an exception error message
 */

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException(String message) {
        super(message);
    }
}

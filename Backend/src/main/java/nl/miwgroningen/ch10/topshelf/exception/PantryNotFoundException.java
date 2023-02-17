package nl.miwgroningen.ch10.topshelf.exception;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * When pantry isn't found, this will provide an exception error message
 */
public class PantryNotFoundException extends RuntimeException{
    public PantryNotFoundException(String message) {
        super(message);
    }
}

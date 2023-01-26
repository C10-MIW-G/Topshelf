package nl.miwgroningen.ch10.topshelf.exception;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Exception for when a pantry is not found by id
 */
public class PantryNotFoundException extends RuntimeException{
    public PantryNotFoundException(String message) {
        super(message);
    }
}

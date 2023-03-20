package nl.miwgroningen.ch10.topshelf.exception;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Dit is wat het programma doet.
 */
public class ProductWithNameAlreadyExistsException extends RuntimeException{

    public ProductWithNameAlreadyExistsException(String message) { super(message);}
}

package nl.miwgroningen.ch10.topshelf.exception;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * throws an exception when a user is trying to add a product, which already exists
 */

public class ProductAlreadyAddedException extends RuntimeException {

    public ProductAlreadyAddedException(String message) {
        super(message);
    }
}

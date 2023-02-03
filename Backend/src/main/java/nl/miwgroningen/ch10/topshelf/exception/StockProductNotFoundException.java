package nl.miwgroningen.ch10.topshelf.exception;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * Exception for when a stockProduct is not found by id
 */

public class StockProductNotFoundException extends RuntimeException {
    public StockProductNotFoundException(String message) {
        super(message);
    }
}

package nl.miwgroningen.ch10.topshelf.exception;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * When StockProduct isn't found, this will provide an exception error message
 */

public class StockProductNotFoundException extends RuntimeException {
    public StockProductNotFoundException(String message) {
        super(message);
    }
}

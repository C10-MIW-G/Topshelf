package nl.miwgroningen.ch10.topshelf.exception;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * When basicStockProduct isn't found, this will provide an exception error message
 */

public class BasicStockProductNotFoundException extends RuntimeException {

    public BasicStockProductNotFoundException(String message) {
        super(message);
    }
}

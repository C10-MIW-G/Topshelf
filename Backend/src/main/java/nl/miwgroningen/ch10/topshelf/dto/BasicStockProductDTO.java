package nl.miwgroningen.ch10.topshelf.dto;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * Supplies information to our frontend
 */

public record BasicStockProductDTO(
        Long basicStockProductId,
        Long pantryId,
        String name,
        int amount
) {
}

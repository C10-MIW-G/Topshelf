package nl.miwgroningen.ch10.topshelf.dto;

import java.time.LocalDate;

/**
 * @author Inge Dikland
 * <p>
 */
public record StockProductDTO (
        Long stockProductId,
        Long pantryId,
        String name,
        LocalDate expirationDate
) {
}

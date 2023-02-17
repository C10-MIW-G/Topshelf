package nl.miwgroningen.ch10.topshelf.dto;

import java.time.LocalDate;

/**
 * @author Inge Dikland
 * Supplies information to our frontend
 */
public record StockProductDTO (
        Long stockProductId,
        Long pantryId,
        String name,
        LocalDate expirationDate
) {
}

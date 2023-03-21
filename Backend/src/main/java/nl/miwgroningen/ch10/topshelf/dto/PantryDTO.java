package nl.miwgroningen.ch10.topshelf.dto;

/**
 * @author Inge Dikland
 * Supplies information to our frontend
 */

public record PantryDTO(
        Long pantryId,
        String name
) {
}

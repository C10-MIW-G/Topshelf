package nl.miwgroningen.ch10.topshelf.dto;

/**
 * Auteur Jessica Schouten.
 * <p>
 * Provides a safe package of information to communicate between front- en backend.
 */

public record GroceryProductDTO(
    Long groceryProductId,
    Long pantryId,
    String name,
    int amount
) {
}

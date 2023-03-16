package nl.miwgroningen.ch10.topshelf.exception;

/**
 * Auteur Jessica Schouten.
 * <p>
 * Gives an exception error when a grocery product cannot be found.
 */

public class GroceryProductNotFoundException extends RuntimeException {
    public GroceryProductNotFoundException(String message) { super(message); }
}

package nl.miwgroningen.ch10.topshelf.exception;

/**
 * Author: Jacob Visser
 * <p>
 * Dit is wat het programma doet.
 */

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String message){
        super(message);
    }
}

package nl.miwgroningen.ch10.topshelf.passwordGenerator;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * Generates a random password of 12 characters
 */

public class PasswordGenerator {

    private static final int AMOUNT_OF_CHARACTERS = 3;

    public String generateSecureRandomPassword() {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < AMOUNT_OF_CHARACTERS; i++) {
            char numbers = getRandomDigitCharacter();
            char lowerCase = getRandomLowerCaseLetter();
            char upperCase = getRandomUpperCaseLetter();
            char character = getRandomCharacter();

            stringBuilder.append(numbers);
            stringBuilder.append(lowerCase);
            stringBuilder.append(upperCase);
            stringBuilder.append(character);
        }
        return String.valueOf(stringBuilder);
    }

    public static char getRandomCharacter(char char1, char char2) {
        return (char) (char1 + Math.random() * (char2 - char1 + 1));
    }

    /**
     * Generate a random lowercase letter
     */
    public char getRandomLowerCaseLetter() {
        return getRandomCharacter('a', 'z');
    }

    /**
     * Generate a random uppercase letter
     */
    public char getRandomUpperCaseLetter() {
        return getRandomCharacter('A', 'Z');
    }

    /**
     * Generate a random digit character
     */
    public char getRandomDigitCharacter() {
        return getRandomCharacter('0', '9');
    }

    /**
     * Generate a random character
     */
    public char getRandomCharacter() {
        return getRandomCharacter('!', '%');
    }
}

package nl.miwgroningen.ch10.topshelf.passwordGenerator;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 *
 * Generates a random password of 12 characters
 */

public class PasswordGenerator {

    private static final int AMOUNT_OF_CHARACTERS = 12;
    private static final int AMOUNT_OF_OPTIONS = 1;
    private static final int MAX = 4;
    private static final int MIN = 0;

    public String generateSecureRandomPassword() {

        StringBuilder characterString = new StringBuilder();
        StringBuilder passwordString = new StringBuilder();

        for (int i = 0; i < AMOUNT_OF_CHARACTERS; i++) {
            for (int j = 0; j < AMOUNT_OF_OPTIONS; j++) {
                characterString.delete(0, 4);
                char number = getRandomDigitCharacter();
                char lowerCase = getRandomLowerCaseLetter();
                char upperCase = getRandomUpperCaseLetter();
                char symbol = getRandomSymbol();

                characterString.append(number);
                characterString.append(lowerCase);
                characterString.append(upperCase);
                characterString.append(symbol);
            }
            int index = (int)((Math.random() * (MAX - MIN)) + MIN);
            passwordString.append(String.valueOf(characterString).charAt(index));
        }
        return String.valueOf(passwordString);
    }

    private static char getRandomCharacter(char char1, char char2) {
        return (char) (char1 + Math.random() * (char2 - char1 + 1));
    }

    /**
     * Generate a random lowercase letter
     */
    private static char getRandomLowerCaseLetter() {
        return getRandomCharacter('a', 'z');
    }

    /**
     * Generate a random uppercase letter
     */
    private static char getRandomUpperCaseLetter() {
        return getRandomCharacter('A', 'Z');
    }

    /**
     * Generate a random digit character
     */
    private static char getRandomDigitCharacter() {
        return getRandomCharacter('0', '9');
    }

    /**
     * Generate a random character
     */
    private static char getRandomSymbol() {
        return getRandomCharacter('!', '%');
    }
}

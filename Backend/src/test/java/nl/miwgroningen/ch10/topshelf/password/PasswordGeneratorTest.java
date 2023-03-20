package nl.miwgroningen.ch10.topshelf.password;

import nl.miwgroningen.ch10.topshelf.passwordGenerator.PasswordGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 *
 * Test function for PasswordGenerator
 */

public class PasswordGeneratorTest {

    @Test
    @DisplayName("Test if password contains characters")
    public void characterTest() {

        Assertions.assertTrue(checkChar());
    }

    @Test
    @DisplayName("Test if password contains numbers")
    public void numberTest() {

        Assertions.assertTrue(checkNumber());
    }

    @Test
    @DisplayName("Test if password contains lower case characters")
    public void lowerCaseTest() {

        Assertions.assertTrue(checkLowerCase());
    }

    @Test
    @DisplayName("Test if password contains upper case characters")
    public void upperCaseTest() {

        Assertions.assertTrue(checkUpperCase());
    }

    public boolean checkChar() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String newPassword = passwordGenerator.generateSecureRandomPassword();
        char[] charsToSearch = {'!', '@', '#', '$', '%'};

        boolean checkIfCharIsPresent = false;
        for (int i = 0; i < newPassword.length(); i++) {
            char charToBeChecked = newPassword.charAt(i);
            for (char toSearch : charsToSearch) {
                if (toSearch == charToBeChecked) {
                    checkIfCharIsPresent = true;
                    break;
                }
            }
        }
        return checkIfCharIsPresent;
    }

    public boolean checkNumber() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String newPassword = passwordGenerator.generateSecureRandomPassword();
        char[] numbersToSearch = {'0','1','2','3','4','5','6','7','8','9'};

        boolean checkIfNumberIsPresent = false;
        for (int i = 0; i < newPassword.length(); i++) {
            char numberToBeChecked = newPassword.charAt(i);
            for (char toSearch : numbersToSearch) {
                if (toSearch == numberToBeChecked) {
                    checkIfNumberIsPresent = true;
                    break;
                }
            }
        }
        return checkIfNumberIsPresent;
    }

    public boolean checkLowerCase() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String newPassword = passwordGenerator.generateSecureRandomPassword();
        return Character.isLowerCase(newPassword.charAt(1)) && Character.isLowerCase(newPassword.charAt(5))
                && Character.isLowerCase(newPassword.charAt(9));
    }

    public boolean checkUpperCase() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        String newPassword = passwordGenerator.generateSecureRandomPassword();
        return Character.isUpperCase(newPassword.charAt(2)) && Character.isUpperCase(newPassword.charAt(6))
                && Character.isUpperCase(newPassword.charAt(10));
    }
}

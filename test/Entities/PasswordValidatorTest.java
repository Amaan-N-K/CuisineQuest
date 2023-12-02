package Entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    private final PasswordValidator validator = new PasswordValidator();

    @Test
    void validate_shouldReturnMessageForNullPassword() {
        String result = validator.Validate(null);
        assertEquals("Password cannot be empty.", result);
    }

    @Test
    void validate_shouldReturnMessageForEmptyPassword() {
        String result = validator.Validate(" ");
        assertEquals("Password cannot be empty.", result);
    }

    @Test
    void validate_shouldReturnMessageForShortPassword() {
        String result = validator.Validate("Abc123");
        assertEquals("Password must be at least 8 character long.", result);
    }

    @Test
    void validate_shouldReturnMessageForPasswordWithoutUppercase() {
        String result = validator.Validate("abcdefgh1");
        assertEquals("Password must contain at least one uppercase letter.", result);
    }

    @Test
    void validate_shouldReturnMessageForPasswordWithoutNumber() {
        String result = validator.Validate("Abcdefgh");
        assertEquals("Password must contain at least one number", result);
    }

    @Test
    void validate_shouldReturnEmptyForValidPassword() {
        String result = validator.Validate("ValidPass1");
        assertEquals("", result);
    }
}
package ictgradschool.project.util;

import org.junit.jupiter.api.Test;

import static ictgradschool.project.util.PasswordUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilTest {

    @Test
    void testSaltLength() {
        for (int i = 0; i < 100; i++) {
            byte[] salt = getNextSalt();
            String encoded = base64Encode(salt);
            assertEquals(44, encoded.length());
        }
    }

    @Test
    void testInsecureHashLength() {
        String password = "password123";
        byte[] insecureHash = insecureHash(password);
        String encoded = base64Encode(insecureHash);
        System.out.println(encoded);
        assertEquals(88, encoded.length());
    }

    @Test
    void testHashLength() {
        String password = "password123";
        byte[] salt = getNextSalt();
        String saltEncoded = base64Encode(salt);
        byte[] hash = hash(password.toCharArray(), salt);
        String hashEncoded = base64Encode(hash);
        assertEquals(88, hashEncoded.length());
    }

    @Test
    void testCanVerifyPassword() {
        String password = "password123";
        byte[] salt = getNextSalt();
        byte[] hash = hash(password.toCharArray(), salt);
        assertTrue(isExpectedPassword(password.toCharArray(), salt, hash));
    }

    @Test
    void testCanDenyIncorrectPassword() {
        String password = "password123";
        byte[] salt = getNextSalt();
        byte[] hash = hash(password.toCharArray(), salt);
        assertFalse(isExpectedPassword("password321".toCharArray(), salt, hash));
    }
}
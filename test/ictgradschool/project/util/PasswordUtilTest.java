package ictgradschool.project.util;

import org.junit.jupiter.api.Test;

import static ictgradschool.project.util.PasswordUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilTest {

    @Test
    void testSaltLength() {
        for (int i = 0; i < 100; i++) {
            byte[] salt = getNextSalt();
            String saltBase64 = base64Encode(salt);
            assertEquals(44, saltBase64.length());
        }
    }

    @Test
    void testInsecureHashLength() {
        String password = "password123";
        byte[] insecureHash = insecureHash(password);
        String insecureHashBase64 = base64Encode(insecureHash);
        System.out.println(insecureHashBase64);
        assertEquals(88, insecureHashBase64.length());
    }

    @Test
    void testHashLength() {
        String password = "password123";
        byte[] salt = getNextSalt();
        byte[] hash = hash(password.toCharArray(), salt);
        String hashBase64 = base64Encode(hash);
        assertEquals(88, hashBase64.length());
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
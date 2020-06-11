package ictgradschool.project.controller;

import ictgradschool.project.entity.Token;
import ictgradschool.project.repository.UserDao;
import ictgradschool.project.util.PasswordUtil;

import static ictgradschool.project.util.PasswordUtil.*;

public class UserController {
    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    public void signUp(String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new UnsupportedOperationException();
        }
        byte[] salt = getNextSalt();
        String saltBase64 = base64Encode(salt);
        hash(password.toCharArray(), salt);
    }

    public boolean isUserExist(String username) {
        throw new UnsupportedOperationException();
    }

    public void deleteUser(int id) {
        throw new UnsupportedOperationException();
    }

    public void changeUsername(Token token, String newUsername) {
        throw new UnsupportedOperationException();
    }

    public void changePassword(Token token, String newPassword, String confirmNewPassword) {
        throw new UnsupportedOperationException();
    }
}

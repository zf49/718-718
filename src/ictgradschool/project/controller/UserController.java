package ictgradschool.project.controller;

import ictgradschool.project.entity.Token;
import ictgradschool.project.repository.UserDao;

public class UserController {
    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    public void signUp(String username, String password, String confirmPassword) {
        throw new UnsupportedOperationException();
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

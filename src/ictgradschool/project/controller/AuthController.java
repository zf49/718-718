package ictgradschool.project.controller;

import ictgradschool.project.entity.Token;
import ictgradschool.project.entity.User;
import ictgradschool.project.entity.UserCredential;
import ictgradschool.project.repository.UserDao;
import ictgradschool.project.util.PasswordUtil;

import java.io.IOException;

public class AuthController {
    private UserDao userDao;

    public AuthController(UserDao userDao) {
        this.userDao = userDao;
    }

    public User signIn(String username, String password) throws IOException {
        UserCredential credential = userDao.getUserCredentialByName(username);
        if (!credential.isExpectedPassword(password)) {
            return null;
        }
        return userDao.getUserByName(username);
    }

    public void signOut(Token token) {
        throw new UnsupportedOperationException();
    }
}

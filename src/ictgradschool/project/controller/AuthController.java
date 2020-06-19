package ictgradschool.project.controller;

import ictgradschool.project.controller.exception.IncorrectPasswordException;
import ictgradschool.project.controller.exception.NoSuchUsernameException;
import ictgradschool.project.entity.User;
import ictgradschool.project.entity.UserCredential;
import ictgradschool.project.repository.UserDao;

import java.io.IOException;

public class AuthController {
    private UserDao userDao;

    public AuthController(UserDao userDao) {
        this.userDao = userDao;
    }

    public User signIn(String username, String password) throws IOException, NoSuchUsernameException, IncorrectPasswordException {
        UserCredential credential = userDao.getUserCredentialByName(username);
        if (credential == null) {
            throw new NoSuchUsernameException();
        }
        if (!credential.isExpectedPassword(password)) {
            throw new IncorrectPasswordException();
        }
        return userDao.getUserByName(username);
    }
}

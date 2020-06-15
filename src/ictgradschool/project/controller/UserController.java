package ictgradschool.project.controller;

import ictgradschool.project.entity.Token;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.UserDao;
import ictgradschool.project.util.HashInfo;

import java.io.IOException;
import java.sql.SQLException;

import static ictgradschool.project.util.PasswordUtil.*;

public class UserController {
    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    public User signUp(String username, String password, String confirmPassword) throws IOException {
        if (!password.equals(confirmPassword)) {
            return null;
        }
        HashInfo hashInfo = quickHash(password);
        return userDao.addUser(username, hashInfo.saltBase64, hashInfo.hashBase64);
    }

    public boolean isUsernameExist(String username) throws IOException, SQLException {
        User user = userDao.getUserByName(username);
        throw new UnsupportedOperationException();
    }

    public void deleteUser(int id) throws IOException, SQLException {
        userDao.deleteUserById(id);
    }

    public void changeUsername(Token token, String newUsername) {
        throw new UnsupportedOperationException();
    }

    public void changePassword(Token token, String newPassword, String confirmNewPassword) {
        throw new UnsupportedOperationException();
    }
}

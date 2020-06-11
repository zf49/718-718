package ictgradschool.project.controller;

import ictgradschool.project.entity.Token;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.UserDao;
import ictgradschool.project.util.DBConnectionUtils;
import ictgradschool.project.util.HashInfo;

import java.io.IOException;
import java.sql.SQLException;

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
        HashInfo hashInfo = quickHash(password);
    }

    public boolean isUserExist(String username) throws IOException, SQLException {
        User user = userDao.getUserByName(username);
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

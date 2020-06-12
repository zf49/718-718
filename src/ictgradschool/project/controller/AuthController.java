package ictgradschool.project.controller;

import ictgradschool.project.entity.Token;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.UserDao;
import ictgradschool.project.util.PasswordUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AuthController {
    private UserDao userDao;

    public AuthController(UserDao userDao) {
        this.userDao = userDao;
    }

    public User signIn(String username, String password) throws IOException, SQLException {
        User user = userDao.getUserByName(username);
        if (user == null) {
            return null;
        }
        byte[] saltBytes = PasswordUtil.base64Decode(user.getSalt());
        byte[] passwordHashBytes = PasswordUtil.base64Decode(user.getPasswordHash());
        boolean isValid = PasswordUtil.isExpectedPassword(password.toCharArray(), saltBytes, passwordHashBytes);
        System.out.println("mark 1");
        System.out.println(isValid);
        return user;
    }

    public void signOut(Token token) {
        throw new UnsupportedOperationException();
    }
}

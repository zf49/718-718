package ictgradschool.project.controller;

import ictgradschool.project.entity.User;
import ictgradschool.project.repository.UserDao;
import ictgradschool.project.util.HashInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public void deleteUser(int id) throws IOException {
        try {
            userDao.deleteUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUserDetail(HttpServletRequest req) throws IOException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(req.getParameter("dateBirth"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User user = (User) req.getSession().getAttribute("user");
        user.setDateBirth(date);
        user.setFname(req.getParameter("fname"));
        user.setLname(req.getParameter("lname"));
        user.setDescription(req.getParameter("description"));
        userDao.addUserDetails(user);
    }

    public User changeUser(HttpServletRequest req, int userId) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        if (!password.equals(confirmPassword)) {
            return null;
        }
        HashInfo hashInfo = quickHash(password);
        return userDao.changeUser(userId, username, hashInfo.saltBase64, hashInfo.hashBase64);
    }

    public void changeUserDetail(HttpServletRequest req, User user) throws IOException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(req.getParameter("dateBirth"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int detailId = Integer.parseInt(req.getParameter("detailId"));
        user.setDetailId(detailId);
        user.setFname(req.getParameter("fname"));
        user.setLname(req.getParameter("lname"));
        user.setDateBirth(date);
        user.setDescription(req.getParameter("description"));
        userDao.updateUserDetail(user);
    }

    public User getUserDetails(User user) throws IOException {
        try {
            return userDao.getUserDetails(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}

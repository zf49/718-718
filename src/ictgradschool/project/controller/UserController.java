package ictgradschool.project.controller;

import ictgradschool.project.controller.exception.InvalidUsernameException;
import ictgradschool.project.controller.exception.PasswordsDontMatchException;
import ictgradschool.project.entity.User;
import ictgradschool.project.repository.UserDao;
import ictgradschool.project.util.HashInfo;
import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import static ictgradschool.project.util.PasswordUtil.*;

public class UserController {
    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    public User signUp(String username, String password, String confirmPassword) throws IOException, InvalidUsernameException, PasswordsDontMatchException {
        checkUsernameValidity(username);
        checkPasswordsMatch(password, confirmPassword);
        HashInfo hashInfo = quickHash(password);
        return userDao.addUser(username, hashInfo.saltBase64, hashInfo.hashBase64);
    }

    private void checkPasswordsMatch(String password, String confirmPassword) throws PasswordsDontMatchException {
        if (!password.equals(confirmPassword)) {
            throw new PasswordsDontMatchException();
        }
    }

    private void checkUsernameValidity(String username) throws InvalidUsernameException {
        if (username.length() < 3) {
            throw new InvalidUsernameException();
        }
        if (!Pattern.matches("[a-zA-Z0-9]+", username)) {
            throw new InvalidUsernameException();
        }
    }

    public boolean isUsernameExist(String username) throws IOException, SQLException {
        User user = userDao.getUserByName(username);
        throw new UnsupportedOperationException();
    }

    public void deleteUser(HttpServletRequest req, int id) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user.getId() == id) {
            try {
                userDao.deleteUserById(user.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addUserDetail(HttpServletRequest req) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        user.setDateBirth(convertStringToDate(req.getParameter("dateBirth")));
        user.setFname(StringEscapeUtils.escapeHtml4(req.getParameter("fname")));
        user.setLname(StringEscapeUtils.escapeHtml4(req.getParameter("lname")));
        user.setDescription(StringEscapeUtils.escapeHtml4(req.getParameter("description")));
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
        int detailId = Integer.parseInt(req.getParameter("detailId"));
        user.setDetailId(detailId);
        user.setFname(StringEscapeUtils.escapeHtml4(req.getParameter("fname")));
        user.setLname(StringEscapeUtils.escapeHtml4(req.getParameter("lname")));
        user.setDateBirth(convertStringToDate(req.getParameter("dateBirth")));
        user.setDescription(StringEscapeUtils.escapeHtml4(req.getParameter("description")));
        userDao.updateUserDetail(user);
    }

    private Date convertStringToDate(String s) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
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

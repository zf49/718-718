package ictgradschool.project.servlet;

import ictgradschool.project.entity.User;
import ictgradschool.project.servlet.exception.UserNotSignedInException;

import javax.servlet.http.HttpServletRequest;

public class ServletUtil {
    public static User getCurrentUser(HttpServletRequest req) throws UserNotSignedInException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            throw new UserNotSignedInException();
        }
        return user;
    }
}

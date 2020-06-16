package ictgradschool.project.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ictgradschool.project.entity.User;
import ictgradschool.project.entity.UsernameAvailability;
import ictgradschool.project.repository.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Check username availability
 */
@WebServlet("/username-available/*")
public class UsernameAvailabilityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getPathInfo().substring(1);
        System.out.println(username);

        UserDao userDao = new UserDao();
        User user = userDao.getUserByName(username);

        UsernameAvailability availability = new UsernameAvailability(username, user == null);
        String availabilityJson = new ObjectMapper().writeValueAsString(availability);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(availabilityJson);
    }
}

package ictgradschool.project.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletUtil {
    public static void forward(HttpServletRequest req, HttpServletResponse resp, ServletContext context, String relativePath) throws ServletException, IOException {
        RequestDispatcher dispatcher = context.getRequestDispatcher("/WEB-INF/" + relativePath);
        dispatcher.forward(req, resp);
    }
}

package controllers;

import common.exceptions.UserDaoException;
import models.pojo.User;
import org.apache.log4j.Logger;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by root on 24.02.17.
 */
public class LogInServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LogInServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = UserService.authorise(login,password);
            if (user.getUid()!=0) {
                HttpSession session = req.getSession(true);
                session.setAttribute("id",user.getUid());
                resp.sendRedirect("/home");
            } else {
                logger.trace("noauth");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        } catch (UserDaoException e) {
            logger.error(e);
            resp.sendRedirect("/error.jsp");
        }
    }
}

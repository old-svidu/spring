package spring.controllers.servlets;

import spring.common.exceptions.UserDaoException;
import spring.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import spring.services.interfaces.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by root on 24.02.17.
 */
@Component
public class LogInServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LogInServlet.class);

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = userService.authorise(login,password);
            if (user.getUid()!=0) {
                HttpSession session = req.getSession(true);
                session.setAttribute("id",user.getUid());
                session.setAttribute("role",user.getRole());
                resp.sendRedirect("/home");
            } else {
                logger.trace("noauth");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        } catch (UserDaoException e) {
            resp.sendRedirect("/error.jsp");
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }
}

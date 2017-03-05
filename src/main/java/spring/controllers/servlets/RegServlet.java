package spring.controllers.servlets;

import spring.common.exceptions.UserDaoException;

import spring.models.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import spring.services.interfaces.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by root on 22.02.17.
 */

@Component
public class RegServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(RegServlet.class);

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String email = req.getParameter("email");
        User user = new User(login, pass, email, "user");
        try {
            boolean flag = userService.isUserInserted(user);
            if (flag) {
                logger.trace("true");
                resp.sendRedirect("/login");
            } else {
                logger.trace("false");
                req.getRequestDispatcher("/error.jsp").forward(req, resp);
            }
        } catch (UserDaoException e){
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

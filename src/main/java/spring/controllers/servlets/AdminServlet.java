package spring.controllers.servlets;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import spring.common.exceptions.ThingDaoException;
import spring.common.exceptions.UserDaoException;
import spring.models.entity.Thing;
import spring.models.entity.User;
import spring.services.interfaces.ThingService;
import spring.services.interfaces.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by root on 01.03.17.
 */
@Component
public class AdminServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(AdminServlet.class);

    private UserService userService;
    private ThingService thingService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setThingService(ThingService thingService) {
        this.thingService = thingService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> users = userService.selectAllUsers();
            List<Thing> things = thingService.selectAllThings();

            req.setAttribute("users", users);
            req.setAttribute("things", things);

            req.getRequestDispatcher("/admin.jsp").forward(req,resp);
        } catch (UserDaoException e) {
            resp.sendRedirect("/error.jsp");
        } catch (ThingDaoException e) {
            resp.sendRedirect("/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }
}

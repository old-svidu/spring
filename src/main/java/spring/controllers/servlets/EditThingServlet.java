package spring.controllers.servlets;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import spring.common.exceptions.ThingDaoException;
import spring.services.interfaces.ThingService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by root on 05.03.17.
 */
@Component
public class EditThingServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EditThingServlet.class);

    private ThingService thingService;
    @Autowired
    public void setThingService(ThingService thingService) {
        this.thingService = thingService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("editThing.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int tid = Integer.parseInt(req.getParameter("id"));
        String thing = req.getParameter("thing");
        int price = Integer.parseInt(req.getParameter("price"));
        int prior = Integer.parseInt(req.getParameter("prior"));

        try {
            if (thingService.isThingUpdated(tid, thing, price, prior)){
                resp.sendRedirect("/admin");
            }
        } catch (ThingDaoException e) {
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

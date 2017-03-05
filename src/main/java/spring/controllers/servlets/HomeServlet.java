package spring.controllers.servlets;

import spring.common.exceptions.MoneyDaoException;
import spring.common.exceptions.ReportsDaoException;
import spring.common.exceptions.ThingDaoException;
import spring.common.exceptions.UserDaoException;

import spring.models.entity.Money;
import spring.models.entity.Report;
import spring.models.entity.Thing;
import spring.models.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import spring.services.interfaces.MoneyService;
import spring.services.interfaces.ReportsService;
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
 * Created by root on 25.02.17.
 */
@Component
public class HomeServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(HomeServlet.class);

    private MoneyService moneyService;
    private ThingService thingService;
    private ReportsService reportsService;

   @Autowired
    public void setMoneyService(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @Autowired
    public void setThingService(ThingService thingService) {
        this.thingService = thingService;
    }

    @Autowired
    public void setReportsService(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Thing> things = thingService.selectAllThings();
            List<Money> monies = moneyService.selectAllMoney();
            List<Report> reports = reportsService.selectAllReports();

            req.setAttribute("things", things);
            req.setAttribute("monies", monies);
            req.setAttribute("reports", reports);

            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        } catch (ThingDaoException e) {
            resp.sendRedirect("/login");
        } catch (ReportsDaoException e) {
            resp.sendRedirect("/login");
        } catch (MoneyDaoException e) {
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }
}

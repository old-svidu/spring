package controllers;

import models.dao.MoneyDao;
import models.dao.ReportDao;
import models.dao.ThingDao;
import models.dao.UserDao;
import models.pojo.Money;
import models.pojo.Report;
import models.pojo.Thing;
import models.pojo.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by root on 25.02.17.
 */
public class HomeServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(HomeServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.trace("here homeServlet");
        Object obj = req.getSession().getAttribute("id");
        if (obj !=null){
            logger.trace("here session exist");
            List<User> users = UserDao.selectAllUsers();
            List<Thing> things = ThingDao.selectAllThings();
            List<Money> monies = MoneyDao.selectAllMoney();
            List<Report> reports = ReportDao.selectAllReports();

            req.setAttribute("users",users);
            req.setAttribute("things",things);
            req.setAttribute("monies",monies);
            req.setAttribute("reports",reports);


            req.getRequestDispatcher("/home.jsp").forward(req,resp);

        } else {
            logger.trace("here session = null");
            resp.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

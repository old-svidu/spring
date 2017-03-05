package spring.models.dao;

import org.springframework.stereotype.Component;
import spring.common.exceptions.ReportsDaoException;
import spring.common.utils.Conn;
import spring.models.entity.Report;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 18.02.17.
 */
@Component
public class ReportDao {
    private static Logger logger = Logger.getLogger(ReportDao.class);

    private static final String INSERT_REPORT = "INSERT INTO main.reports (thing, login, status) VALUES(?,?,?)";
    private static final String SELECT_ALL_REPORTS = "SELECT * FROM main.reports";

    public void insert(Report report) throws ReportsDaoException {
        try {
            Connection conn = Conn.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_REPORT);
            ps.setString(1, report.getThing());
            ps.setString(2, report.getLogin());
            ps.setString(3, report.getStatus());

            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            throw new ReportsDaoException();
        }
    }

    public List<Report> selectAllReports() throws ReportsDaoException {
        List<Report> list = new ArrayList<>();
        try {
            Connection conn = Conn.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_REPORTS);
            while (rs.next()) {
                int id = rs.getInt("rid");
                String thing = rs.getString("thing");
                String login = rs.getString("login");
                String status = rs.getString("status");

                Report report = new Report(id, login, thing, status);
                list.add(report);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new ReportsDaoException();
        }
        return list;
    }
}

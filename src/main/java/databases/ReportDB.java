package databases;

import lists.Reports;
import utils.Conn;
import main.Sets;
import tables.Report;
import utils.Log;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by root on 18.02.17.
 */
public class ReportDB {


    public static void insert(Object obj) {
        Reports reports = (Reports) obj;
        try {
            Connection conn = Conn.getInstance();
            String sql = "INSERT INTO main.reports (thing, login, status,avg) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            for (Report report : reports.getList()) {

                while (!Sets.loginsSet.contains(report.getLogin())) {
                    Thread.yield();
                }
                ps.setString(2, report.getLogin());
                ps.setString(1, report.getThing());
                ps.setString(3, report.getStatus());
                ps.setInt(4, report.getAvg());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            Log.logger.error(e);
        }
    }


    public static void insert(Report report) {
        try {
            Connection conn = Conn.getInstance();
            String sql = "INSERT INTO main.reports (login,thing,status,avg) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, report.getLogin());
            ps.setString(2, report.getThing());
            ps.setString(3, report.getStatus());
            ps.setInt(4, report.getAvg());
            ps.executeUpdate();

        } catch (SQLException e) {
            Log.logger.error(e);
        }
    }

    public static ArrayList<Report> selectAll() {
        ArrayList<Report> list = new ArrayList<>();

        try {
            Connection conn = Conn.getInstance();
            String query = "SELECT * FROM main.reports";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String thing = rs.getString("thing");
                String login = rs.getString("login");
                String status = rs.getString("status");
                int avg = rs.getInt("avg");

                Report report = new Report(id, login, thing, status, avg);
                list.add(report);
            }

        } catch (SQLException e) {
            Log.logger.error(e);
        }
        return list;
    }
}

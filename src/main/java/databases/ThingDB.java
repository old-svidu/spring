package databases;

import lists.Things;
import utils.Conn;
import main.Sets;
import tables.Thing;
import utils.Log;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by root on 18.02.17.
 */
public class ThingDB {


    public static void insert(Object obj) {
        Things things = (Things) obj;
        try {
            Connection conn = Conn.getInstance();
            String sql = "INSERT INTO main.thing (login,thing,price,tdate,priority) values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            for (Thing thing : things.getList()) {

                while (!Sets.loginsSet.contains(thing.getLogin())) {
                    Thread.yield();
                }
                ps.setString(1, thing.getLogin());
                ps.setString(2, thing.getThing());
                ps.setInt(3, thing.getPrice());
                ps.setDate(4, new Date(thing.getTdate().getTime()));
                ps.setInt(5, thing.getPrior());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            Log.logger.error(e);
        }
    }

    public static void insert(Thing thing) {

        try {
            Connection conn = Conn.getInstance();
            String sql = "INSERT INTO main.thing (login,thing,price,tdate,priority) values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, thing.getLogin());
            ps.setString(2, thing.getThing());
            ps.setInt(3, thing.getPrice());
            ps.setDate(4, (Date) thing.getTdate());
            ps.setInt(5, thing.getPrior());
            ps.executeUpdate();

        } catch (SQLException e) {
            Log.logger.error(e);
        }
    }

    public static ArrayList<Thing> selectAll() {
        ArrayList<Thing> list = new ArrayList<>();

        try {
            Connection conn = Conn.getInstance();
            String query = "SELECT * FROM main.thing";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int tid = rs.getInt("id");
                String login = rs.getString("login");
                String thng = rs.getString("thing");
                int price = rs.getInt("price");
                Date tdate = rs.getDate("tdate");
                int prior = rs.getInt("priority");

                Thing thing = new Thing(tid, login, thng, price, tdate, prior);
                list.add(thing);
            }

        } catch (SQLException e) {
            Log.logger.error(e);
        }
        return list;
    }
}

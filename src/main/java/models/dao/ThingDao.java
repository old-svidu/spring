package models.dao;

import models.lists.Things;
import common.utils.Conn;
import main.Sets;
import models.pojo.Thing;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 18.02.17.
 */
public class ThingDao {
    private static Logger logger = Logger.getLogger(ThingDao.class);

    private static final String SELECT_ALL_THINGS = "SELECT * FROM main.thing";
    private static final String INSERT_THING = "INSERT INTO main.thing (login,thing,price,prior) values(?,?,?,?)";


    public static void insert(Object obj) {
        Things things = (Things) obj;
        try {
            Connection conn = Conn.getInstance();

            PreparedStatement ps = conn.prepareStatement(INSERT_THING);
            for (Thing thing : things.getList()) {

                while (!Sets.loginsSet.contains(thing.getLogin())) {
                    Thread.yield();
                }
                ps.setString(1, thing.getLogin());
                ps.setString(2, thing.getThing());
                ps.setInt(3, thing.getPrice());
                ps.setInt(4, thing.getPrior());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public static void insert(Thing thing) {

        try {
            Connection conn = Conn.getInstance();
            PreparedStatement ps = conn.prepareStatement(INSERT_THING);
            ps.setString(1, thing.getLogin());
            ps.setString(2, thing.getThing());
            ps.setInt(3, thing.getPrice());
            ps.setInt(4, thing.getPrior());
            ps.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public static ArrayList<Thing> selectAll() {
        ArrayList<Thing> list = new ArrayList<>();

        try {
            Connection conn = Conn.getInstance();


            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_THINGS);
            while (rs.next()) {
                int tid = rs.getInt("tid");
                String login = rs.getString("login");
                String thng = rs.getString("thing");
                int price = rs.getInt("price");
                int prior = rs.getInt("prior");

                Thing thing = new Thing(tid, login, thng, price, prior);
                list.add(thing);
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    public static List<Thing> selectAllThings(){
        List<Thing> list = new ArrayList<>();
        try {
            Connection conn = Conn.getInstance();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_THINGS);
            while (rs.next()){
                int tid = rs.getInt("tid");
                String login = rs.getString("login");
                String thng = rs.getString("thing");
                int price = rs.getInt("price");
                int prior = rs.getInt("prior");
                list.add( new Thing(tid,login,thng,price,prior));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }
}

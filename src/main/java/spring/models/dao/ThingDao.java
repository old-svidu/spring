package spring.models.dao;

import org.springframework.stereotype.Component;
import spring.common.exceptions.ThingDaoException;

import spring.common.utils.Conn;

import spring.models.entity.Thing;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by root on 18.02.17.
 */
@Component
public class ThingDao {
    private static Logger logger = Logger.getLogger(ThingDao.class);

    private static final String SELECT_ALL_THINGS = "SELECT * FROM main.thing";
    private static final String SELECT_THING_BY_ID = "SELECT * FROM main.thing WHERE tid = ?";
    private static final String DELETE_THING_BY_ID = "DELETE FROM main.thing WHERE tid = ?";
    private static final String INSERT_THING = "INSERT INTO main.thing (login,thing,price,prior) values(?,?,?,?)";
    private static final String UPDATE_THING_BY_ID = "UPDATE main.thing SET (thing,price,prior) = (?,?,?) WHERE tid = ?";


    public boolean insert(Thing thing) throws ThingDaoException {
        try {
            Connection conn = Conn.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_THING);
            ps.setString(1, thing.getLogin());
            ps.setString(2, thing.getThing());
            ps.setInt(3, thing.getPrice());
            ps.setInt(4, thing.getPrior());
            int num = ps.executeUpdate();
            if (num > 0){
                return true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ThingDaoException();
        }
        return false;
    }

    public List<Thing> selectAllThings() throws ThingDaoException {
        List<Thing> list = new ArrayList<>();
        try {
            Connection conn = Conn.getConnection();
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
            throw new ThingDaoException();
        }
        return list;
    }

    public boolean deleteThingById(int id) throws ThingDaoException{
        try {
            Connection conn = Conn.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETE_THING_BY_ID);
            ps.setInt(1,id);
            int num = ps.executeUpdate();
            if (num > 0){
                return true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ThingDaoException();
        }
        return false;
    }

    public boolean updateThing(int id, String thing, int price, int prior) throws ThingDaoException{
        try {
            Connection conn = Conn.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATE_THING_BY_ID);
            ps.setString(1,thing);
            ps.setInt(2,price);
            ps.setInt(3,prior);
            ps.setInt(4,id);
            int num = ps.executeUpdate();
            if (num > 0){
                return true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new ThingDaoException();
        }
        return false;
    }
}

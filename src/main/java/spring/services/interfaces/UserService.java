package spring.services.interfaces;

import spring.common.exceptions.UserDaoException;
import spring.models.entity.User;

import java.util.List;

/**
 * Created by root on 02.03.17.
 */
public interface UserService {
    boolean isUserInserted(User user) throws UserDaoException;
    User selectUserById(int id) throws UserDaoException;
    void deleteAll() throws UserDaoException;
    boolean isUserDeleted(int id) throws UserDaoException;
    User authorise(String login, String password) throws UserDaoException;
    List<User> selectAllUsers() throws UserDaoException;
    boolean updateUser(int id, String login, String pass, String email) throws UserDaoException;

}

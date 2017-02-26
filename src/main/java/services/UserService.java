package services;

import common.exceptions.UserDaoException;
import models.dao.UserDao;
import models.pojo.User;

/**
 * Created by root on 24.02.17.
 */
public class UserService {
    public static User authorise(String login, String password) throws UserDaoException {
        return UserDao.getUserByLoginAndPassword(login, password);
    }

    public static boolean registration(User user) {
        return UserDao.insert(user);
    }
}

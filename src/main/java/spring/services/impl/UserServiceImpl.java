package spring.services.impl;

import spring.common.exceptions.UserDaoException;

import spring.models.dao.UserDao;
import spring.models.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.services.interfaces.UserService;

import java.util.List;

/**
 * Created by root on 24.02.17.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean isUserInserted(User user) throws UserDaoException {
        return userDao.insert(user);
    }

    @Override
    public User selectUserById(int id) throws UserDaoException {
        return userDao.selectUserById(id);
    }

    @Override
    public void deleteAll() throws UserDaoException {
        userDao.deleteAll();
    }

    @Override
    public User authorise(String login, String password) throws UserDaoException {
        return userDao.getUserByLoginAndPassword(login,password);
    }


    @Override
    public boolean isUserDeleted(int id) throws UserDaoException {
        return userDao.deleteUserById(id);
    }

    @Override
    public List<User> selectAllUsers() throws UserDaoException {
        return userDao.selectAllUsers();
    }

    @Override
    public boolean updateUser(int id, String login, String pass, String email) throws UserDaoException {
        return userDao.updateUserById(id,login,pass,email);
    }

}

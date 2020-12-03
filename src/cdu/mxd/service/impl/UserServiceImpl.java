package cdu.mxd.service.impl;


import cdu.mxd.dao.UserDao;
import cdu.mxd.dao.impl.UserDaoImpl;
import cdu.mxd.entity.User;
import cdu.mxd.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean checkLogin(String username, String passsword) {
        return userDao.checkLogin(username, passsword);
    }

    @Override
    public boolean register(User user) {
        return false;
    }

    @Override
    public boolean add(User user) {
        return userDao.insert(user);
    }

    @Override
    public boolean modify(User user) {
        return userDao.modify(user);
    }

    @Override
    public boolean delete(int id) {
        return userDao.delete(id);
    }

    @Override
    public List<User> findByPage(int start, int num) {
        return userDao.findByPage(start, num);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User get(int id) {
        return userDao.get(id);
    }

    @Override
    public int getCount() {
        return userDao.getCount();
    }
}

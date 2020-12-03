package cdu.mxd.dao;

import cdu.mxd.entity.User;

import java.util.List;

public interface UserDao {

    boolean checkLogin(String username , String password);

    boolean insert(User user);

    boolean delete(int id);

    boolean modify(User user);

    List<User> findByPage(int start , int num);

    List<User> findAll();

    User findById(int id);

    User findByIdentification(String name , String password);

    User get(int id);

    int getCount();

}

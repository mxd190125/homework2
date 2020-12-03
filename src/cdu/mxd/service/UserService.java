package cdu.mxd.service;

import cdu.mxd.entity.User;

import java.util.List;

public interface UserService {
    boolean checkLogin(String username , String password);

    boolean register(User arg1);

    boolean add(User user);

    boolean modify(User user);

    boolean delete(int id);

    List<User> findByPage(int start , int num);

    List<User> findAll();

    User get(int id);

    int getCount();

}

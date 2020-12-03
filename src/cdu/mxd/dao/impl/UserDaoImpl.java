package cdu.mxd.dao.impl;

import cdu.mxd.dao.BaseDao;
import cdu.mxd.dao.UserDao;
import cdu.mxd.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public boolean checkLogin(String username, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        String sql = "select count(*) from user where username = ? and password = ?";
        try {
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1 , username);
            statement.setString(2 , password);
            set = statement.executeQuery();
            set.next();
            if (set.getInt(1) == 1) {
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }finally {
            this.release(connection ,statement ,set);
        }
    }

    @Override
    public boolean insert(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "insert into user(username , password , sex , age , regTime)values(?,?,?,?,now())";
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1 , user.getName());
            statement.setString(2 , user.getPassword());
            statement.setString(3 , user.getSex());
            statement.setInt(4 , user.getAge());
            int i = statement.executeUpdate();
            if (i<=0){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.release(connection , statement);
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "delete from user where id=?";
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1 , id);
            if (statement.executeUpdate()<=0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.release(connection , statement);
        }
        return true;
    }

    @Override
    public boolean modify(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "update user set username=? , sex=? , age=?  where id =?";
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(4 , user.getId());
            statement.setString(1 , user.getName());
            statement.setString(2 , user.getSex());
            statement.setInt(3 , user.getAge());
            int i = statement.executeUpdate();
            if (i>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.release(connection ,statement);
        }
        return false;
    }

    /**
     * 实现分页：第start页
     * @param start
     * @param num
     * @return
     */
    @Override
    public List<User> findByPage(int start, int num) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<User> list = new ArrayList<>();
        String sql = "select * from user limit ? , ?";
        try {
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1 , (start-1) * num);
            statement.setInt(2 , num);
            set = statement.executeQuery();
            while (set.next()) {
                User user = new User();
                user.setId(set.getInt(1));
                user.setName(set.getString(2));
                user.setPassword(set.getString(3));
                user.setSex(set.getString(4));
                user.setAge(set.getInt(5));
                user.setRegTime(set.getString(6));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<User> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        List<User> userList = new ArrayList<>();
        String sql = "select * from user";
        try {
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            set = statement.executeQuery();
            while (set.next()) {
                User user = new User();
                user.setId(set.getInt(1));
                user.setName(set.getString(2));
                user.setPassword(set.getString(3));
                user.setSex(set.getString(4));
                user.setAge(set.getInt(5));
                user.setRegTime(set.getString(6));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.release(connection ,statement ,set);
        }
        return userList;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByIdentification(String name, String password) {
        return null;
    }

    @Override
    public User get(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        User user = new User();
        String sql = "select * from user where id=?";
        try {
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1 , id);
            set = statement.executeQuery();
            while (set.next()) {
                user.setId(set.getInt(1));
                user.setName(set.getString(2));
                user.setPassword(set.getString(3));
                user.setSex(set.getString(4));
                user.setAge(set.getInt(5));
                user.setRegTime(set.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(connection , statement , set);
        }
        return user;
    }

    @Override
    public int getCount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        String sql = "select count(*) as count from user";
        int count = 0;
        try {
            connection = this.getConnection();
            statement = connection.prepareStatement(sql);
            set = statement.executeQuery();
            if (set.next()) {
                count = set.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(connection , statement , set);
        }
        return count;
    }
}

package cdu.mxd.controller;

import cdu.mxd.entity.User;
import cdu.mxd.service.UserService;
import cdu.mxd.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = "/user")
public class UserController extends BaseController {
    private UserService userService = new UserServiceImpl();

    public String checkLogin(HttpServletRequest request, HttpServletResponse response) {
        String page ="";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.checkLogin(username , password)) {
            int count = userService.getCount();
            //每页7条数据
            int pages = count%7 == 0 ? count/7 : count/7+1;
            //登录成功后，默认第一页，展示前7页数据
            List<User> list = userService.findByPage(1, 7);
            request.setAttribute("pages" , pages);
            request.setAttribute("userList" , list);
            request.setAttribute("curPage" , 1);
            request.getSession().setAttribute("username" , username);
            request.getSession().setAttribute("path" , request.getContextPath() + "/");
            page = "page/listUser.jsp";
        }else {
            request.setAttribute("err" , "用户名或密码错误!");
            page = "index.jsp";
        }
        return page;
    }

    public String findByPage(HttpServletRequest request, HttpServletResponse response){
        String page ="";
        int count = userService.getCount();
        //每页7条数据
        int pages = count%7 == 0 ? count/7 : count/7+1;
        int curPage = Integer.valueOf(request.getParameter("page"));
        List<User> list = userService.findByPage(curPage, 7);
        request.setAttribute("pages" , pages);
        request.setAttribute("userList" , list);
        request.setAttribute("curPage" , curPage);
        page = "page/listUser.jsp";
        return page;
    }

    public String modPage(HttpServletRequest request, HttpServletResponse response){
        String page = "";
        int id = Integer.valueOf(request.getParameter("id"));
        User user = userService.get(id);
        if (user != null) {
            request.setAttribute("user" , user);
            page = "page/modUser.jsp";
        }else {
            page = "index.jsp";
        }
        return page;
    }

    public String delete(HttpServletRequest request, HttpServletResponse response){
        String page = "";
        int id = Integer.valueOf(request.getParameter("id"));
        boolean b = userService.delete(id);

        int count = userService.getCount();
        //每页7条数据
        int pages = count%7 == 0 ? count/7 : count/7+1;
        //登录成功后，默认第一页，展示前7页数据
        List<User> list = userService.findByPage(1, 7);
        request.setAttribute("pages" , pages);
        request.setAttribute("userList" , list);
        request.setAttribute("curPage" , 1);
        return "page/listUser.jsp";
    }

    public String modify(HttpServletRequest request, HttpServletResponse response){
        String page = "";
        User user = new User();
        user.setId(Integer.valueOf(request.getParameter("id")));
        user.setName(request.getParameter("username"));
        user.setSex(request.getParameter("sex"));
        user.setAge(Integer.valueOf(request.getParameter("age")));
        if (userService.modify(user)) {
            int count = userService.getCount();
            //每页7条数据
            int pages = count%7 == 0 ? count/7 : count/7+1;
            //登录成功后，默认第一页，展示前7页数据
            List<User> list = userService.findByPage(1, 7);
            request.setAttribute("pages" , pages);
            request.setAttribute("userList" , list);
            request.setAttribute("curPage" , 1);
            page = "page/listUser.jsp";
        }else {
            page = "page/modUser.jsp";
        }
        return page;
    }

    public String add(HttpServletRequest request, HttpServletResponse response){
        String page = "";
        User user = new User();
        user.setName(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setSex(request.getParameter("sex"));
        user.setAge(Integer.valueOf(request.getParameter("age")));
        boolean b = userService.add(user);
        if (b){
            int count = userService.getCount();
            //每页7条数据
            int pages = count%7 == 0 ? count/7 : count/7+1;
            //登录成功后，默认第一页，展示前7页数据
            List<User> list = userService.findByPage(1, 7);
            request.setAttribute("pages" , pages);
            request.setAttribute("userList" , list);
            request.setAttribute("curPage" , 1);
            page = "page/listUser.jsp";
        }else {
            page = "page/addUser.jsp";
        }
        return page;
    }

}

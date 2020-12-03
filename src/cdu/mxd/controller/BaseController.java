package cdu.mxd.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String methodName = request.getParameter("method");
        Class baseClass = this.getClass();
        Method method = null;
        if (methodName.isEmpty() || methodName.trim().isEmpty()) {
            throw new RuntimeException("当前方法为空");
        }
        try {
            method=baseClass.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        String page = "";
        try {
            page = (String) method.invoke(this, request, response);
        }catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("当前方法执行有问题");
        }
        if (page.isEmpty() || page.trim().isEmpty()) {
            return;
        }
//        String path =request.getContextPath() + "/";
        request.getRequestDispatcher(page).forward(request , response);
    }
}

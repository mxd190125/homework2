<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath() + "/";
%>
<html>
  <head>
    <title>登录页</title>
  </head>
  <link rel="stylesheet" type="text/css" href="css/all.css">
  <link rel="stylesheet" type="text/css" href="css/index.css">
  <body>
       <div id="login">
         <h1>Login</h1>
         <form action="<%=path%>user?method=checkLogin" method="post">
           <input type="text" name="username" required="required" placeholder="用户名">
           <input type="password" name="password" required="required" placeholder="密码">
           <button type="submit" class="but">登录</button>
           <button type="reset" class="res">重置</button>
         </form>
         <c:if test="${err != null}">
           <p style="color:red;">${err}</p>
         </c:if>
       </div>
  </body>
</html>

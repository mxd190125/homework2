<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cdu.mxd.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户Page</title>
</head>
<link rel="stylesheet" type="text/css" href="${path}css/all.css">
<style type="text/css">
    .a{
        margin: 0 auto;
        width: 300px;
    }
    .b{
        width: 50px;
    }
</style>
<body>
  <div class="a">
      <c:if test="${user != null}">
          <form action="${path}user?method=modify" method="post">
              <span class="b">ID：</span><input type="text" name="id" value="${user.getId()}" readonly>
              <br>
              <span class="b">名字：</span><input type="text" name="username" value="${user.getName()}">
              <br>
              <span class="b">性别：</span><input type="text" name="sex" value="${user.getSex()}">
              <br>
              <span class="b">年龄：</span><input type="text" name="age" value="${user.getAge()}">
              <br>
              <span>注册时间：</span><input type="text" value="${user.getRegTime()}" readonly>
              <br>
              <button type="submit" class="but">提交</button>
              <button type="reset" class="res">重置</button>
          </form>
      </c:if>
  </div>
</body>
</html>

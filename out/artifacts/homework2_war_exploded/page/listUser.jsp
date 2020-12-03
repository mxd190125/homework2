<%@ page import="cdu.mxd.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>展示所有用户Page</title>
</head>
<link rel="stylesheet" type="text/css" href="${path}css/all.css">
<style type="text/css">
    .a{
        margin: 0 auto;
        width: 430px;
    }
    #_table {
        color: #fff;
        text-shadow:0 0 10px;
        letter-spacing: 1px;
        text-align: center;
        font-size: 2em;
        margin: 0.67em 0;
    }
</style>
<body>
   <div class="a" >
       <span><a href="page/addUser.jsp">添加用户</a></span>
       <div>
           <table border="1">
               <thead id="_table">用户列表</thead>
               <tbody>
               <tr>
                   <th>ID</th>
                   <th>名字</th>
                   <th>性别</th>
                   <th>年龄</th>
                   <th>注册日期</th>
                   <th colspan="2">操作</th>
               </tr>
               <c:forEach items="${userList}" var="user" varStatus="curStatus">
               <tr>
                   <td>${curStatus.index+1}</td>
                   <td>${user.getName()}</td>
                   <td>${user.getSex()}</td>
                   <td>${user.getAge()}</td>
                   <td>${user.getRegTime()}</td>
                   <td><a href="${path}user?method=modPage&id=${user.getId()}">修改</a></td>
                   <td><a href="${path}user?method=delete&id=${user.getId()}"}>删除</a></td>
               </tr>
               </c:forEach>
               <tr>
                   <td colspan="7">
                       <c:if test="${!(curPage == 1)}">
                       <a href="${path}user?method=findByPage&page=${curPage-1}">上一页</a>
                       </c:if>
                       <c:forEach var="index" begin="1" end="${pages}">
                          <a href="${path}user?method=findByPage&page=${index}" style="${curPage == index ? 'color:red;':''}">
                              第${index}页
                          </a>
                           </c:forEach>
                        <c:if test="${!(curPage == pages)}">
                           <a href="${path}user?method=findByPage&page=${curPage+1}">下一页</a>
                       </c:if>
                  </td>
               </tr>
               </tbody>
           </table>
       </div>
   </div>
</body>
</html>

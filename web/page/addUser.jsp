<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加新用户Page</title>
</head>
<link rel="stylesheet" type="text/css" href="${path}css/all.css">
<style type="text/css">
    .a{
        margin: 0 auto;
        width: 300px;
    }
    .b{
        width: 70px;
    }
</style>
<body>
   <div class="a">
       <form action="${path}user?method=add" method="post">
           <span class="b">名字：</span><input type="text" name="username" class="c">
           <br>
           <input type="text" name="password" value="1111" hidden>
           <span class="b">性别：</span><input type="text" name="sex" class="c">
           <br>
           <span class="b">年龄：</span><input type="text" name="age" class="c">
           <br>
           <button type="submit" class="but">提交</button>
           <button type="reset" class="res">重置</button>
       </form>
   </div>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/6
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
    <form action="/loginUser" method="post">

        <table>
            <tr>
                <td> 用户名:</td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td> 密码:</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td>记住我?</td>
                <td><input type="checkbox" name="remenberme"></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><button type="submit" >登录</button></td>
            </tr>
        </table>
    </form>

</body>
</html>

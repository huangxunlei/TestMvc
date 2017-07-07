<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/7/4 0004
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="save" method="post">
    <fieldset>
        <legend>创建用户</legend>

        <p>
            <label>姓名:</label><input type="text" id="name" name="name" tabindex="1">
        </p>
        <P>
            <label>年龄:</label><input type="text" id="age" name="age" tabindex="2">
        </P>
        <P>
            <label>密码:</label><input type="text" id="pwd" name="pwd" tabindex="3">
        </P>
        <P id="buttons">
            <input id="reset" type="reset" tabindex="4" value="取消">
            <input id="submit" type="submit" tabindex="5" value="创建">
        </P>
    </fieldset>
</form>
</body>
</html>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<%--<sf:form action="/signup"> &lt;%&ndash;使用POST方法, 隐藏url&ndash;%&gt;--%>
    <%--<table>--%>
        <%--<tr>--%>
            <%--<td><label>name：--%>
                <%--<input type="text" name="name" value="" />--%>
            <%--</label></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><label>password:--%>
                <%--<input type="password" name="password" value="" />--%>
            <%--</label></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td></td>--%>
            <%--<td><label><input type="submit" name="sign_up" value="注册" /></label></td>--%>
        <%--</tr>--%>
    <%--</table>--%>
<%--</sf:form>--%>

<sf:form action="/login"> <%--使用POST方法, 隐藏url--%>
    <table>
        <tr>

            <td><label>name：
                <input type="text" name="name" value="" />
            </label></td>
        </tr>
        <tr>
            <td><label>password:
                <input type="password" name="password" value="" />
            </label></td>
        </tr>
        <tr>
            <td></td>
            <td><label><input type="submit" name="log_in" value="登录" /></label></td>
        </tr>
    </table>
</sf:form>
<h2>${message}</h2>
</body>
</html>

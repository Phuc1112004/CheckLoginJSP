<%@ page import="com.example.demo1.LoginBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login controller</title>
    <%
        // step1 call Controller
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // step 2 Call instance JavaBean(model)
        LoginBean loginBean = new LoginBean();
        boolean status = loginBean.checkLogin(username,password);
        if(status){%>

    <%--    step 3--%>
    <jsp:forward page="success.jsp"></jsp:forward>

    <%} else {%>
    <jsp:forward page="fail.jsp"></jsp:forward>
    <%}
    %>
</head>
<body>

</body>
</html>

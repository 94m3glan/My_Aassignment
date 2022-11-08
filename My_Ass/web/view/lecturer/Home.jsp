<%-- 
    Document   : Home
    Created on : Nov 8, 2022, 10:57:21 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../css/timetable_style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <header>
            <h1>FPT University Academic Portal</h1>
            <img src="../img/fptlogo.png">
        </header>

        <h1>Welcome ${lecturer.name}</h1>
        <h2>Information Access(Tra cứu thông tin)</h2>
        <a href="timetable?lid=${lecturer.id}">Weekly timetable</a>(Thời khóa biểu từng tuần)

        <h2>Reports(Báo cáo)</h2>
        <p>Attendance Report(Báo cáo điểm danh)</p>
        <c:forEach items="${groups}" var="gr">
            <a href="CheckAttendanceRp?grid=${gr.id}">${gr.name}</a>
        </c:forEach>

        <footer> <a href="/My_Ass/logout" >click here to logout</a></footer>
    </body>
</html>

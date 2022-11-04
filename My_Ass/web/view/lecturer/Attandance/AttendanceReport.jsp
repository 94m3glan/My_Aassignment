<%-- 
    Document   : AttendanceReport
    Created on : Nov 3, 2022, 2:21:49 AM
    Author     : HP
--%>
<jsp:useBean id="helper" class="util.AttendanceRpHelper"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../css/AttendanceReport_style.css"/>
    </head>
    <body>
        <header>
            <nav><a href="">Home</a></nav>
            <h1>FPT University Academic Portal</h1>
            <img src="../img/fptlogo.png">
        </header>

        <h2>Attendance Report for Group ${list.get(0).groups.get(0).classname}</h2>
        
        
            <p>Attendances for ${list.get(0).groups.get(0).subject.name} with lecturer ${list.get(0).groups.get(0).supervisor.name}
            on ${list.get(0).groups.get(0).semester} ${list.get(0).groups.get(0).year} at FU-HL</p>

        <table>
            <tr>
                <th>NO</th>
                <th>MASV</th>
                <th>NAME</th>
                <th>Slot 1</th>
                <th>Slot 2</th>
                <th>Slot 3</th>
                <th>Slot 4</th>
                <th>Slot 5</th>
                <th>Slot 6</th>
                <th>Status</th>
            </tr>
            <c:forEach items="${list}" var="student">
                <tr>
                    <td>${list.indexOf(student) + 1}</td>
                    <td>${student.code}</td>
                    <td>${student.name}</td>
                    <c:forEach items="${student.attandances}"  var="att" end="5">
                        <td>
                        <c:if test="${att.isPresent()}">
                            <img src="../img/check-icon.jpg" alt="attend" style="width: 40%"/>
                        </c:if>
                         <c:if test="${!att.isPresent()}">
                             <img src="../img/x-mark-icon.jpg" alt="absent" style="width: 40%"/>
                        </c:if>
                        </td>
                    </c:forEach>
                        <td><progress value="${100 - helper.percentAbsent(student.attandances, 6)}" max="100" ></progress><br/>
                         ${100 - helper.percentAbsent(student.attandances, 6)}% attended so far <br/>
                         <span style="background-color: green; border-radius: 5px;">${helper.countAttend(student.attandances, 6)} Present</span><br/>
                         <span style="background-color: red ; border-radius: 5px;">${helper.countAbsent(student.attandances, 6)} Absent</span><br/>
                         <span style="background-color: #ffcccc ; border-radius: 5px;">${6 - helper.countAttend(student.attandances, 6)- helper.countAbsent(student.attandances, 6)} Future</span></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

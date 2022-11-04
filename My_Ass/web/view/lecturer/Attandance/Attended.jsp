<%-- 
    Document   : TakeAttendance
    Created on : Oct 30, 2022, 10:47:38 AM
    Author     : HP
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="helper" class="util.DateTimeHelper"/>
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

        <h2>Single activity Attendance</h2>

        <p>Attendance for ${atts.get(0).session.group.subject.name} with lecturer ${atts.get(0).session.lecturer.name}
            at slot ${atts.get(0).session.timeslot.id} on ${helper.getDayNameofWeek(atts.get(0).session.date)} 
            ${helper.format(atts.get(0).session.date, "dd/MM/yyyy")}, ${atts.get(0).session.group.semester}
            ${atts.get(0).session.group.year}, in room ${atts.get(0).session.room.name} at FU-HL</p>

        <table>
            <tr>
                <th>NO</th>
                <th>GROUP</th>
                <th>NAME</th>
                <th>IMAGE</th>
                <th>STATUS</th>
                <th>COMMENT</th>
                <th>TAKER</th>
                <th>RECORD TIME<th>
            </tr>
            <c:forEach items="${atts}" var="att">
                <tr>
                    <td>${atts.indexOf(att) + 1}</td>
                    <td>${att.session.group.name}</td>
                    <td>${att.student.name}</td>
                    <td style="width: 5%"><img src="../img/fptlogo.png" width="100%"  alt="avatar"/></td>
                    <td
                        <c:if test="${att.isPresent()}">
                            class="present"
                        </c:if>
                        <c:if test="${!att.isPresent()}">
                            class="absent"
                        </c:if>    
                        >
                        <c:if test="${att.isPresent()}">
                            Present
                        </c:if>
                        <c:if test="${!att.isPresent()}">
                            Absent
                        </c:if>
                    </td> 
                    <td>${att.description}</td>
                    <td>${att.session.lecturer.name}</td>
                    <td>${helper.format(att.record_time, "dd/MM/yyyy hh:mm:ss")}</td>
                </tr>
            </c:forEach>
        </table>
        <form action="timetable" method="GET">
              <input type="hidden" name="lid" value="${lid}"/>
             <input type="hidden" name="week" value="${week}"/>
             <input type="submit" value="Exit"/>
        </form>
    </body>
</html>

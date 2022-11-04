<%-- 
    Document   : AddAttandance
    Created on : Oct 31, 2022, 8:12:45 PM
    Author     : HP
--%>
<jsp:useBean id="helper" class="util.DateTimeHelper"/>
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

        <h2>Single activity Attendance</h2>

         <p>Attendance for ${atts.get(0).session.group.subject.name} with lecturer ${atts.get(0).session.lecturer.name}
            at slot ${atts.get(0).session.timeslot.id} on ${helper.getDayNameofWeek(atts.get(0).session.date)} 
            ${helper.format(atts.get(0).session.date, "dd/MM/yyyy")}, ${atts.get(0).session.group.semester}
            ${atts.get(0).session.group.year}, in room ${atts.get(0).session.room.name} at FU-HL</p>

        <form action="TakeAttendace" method="POST">
            <input type="hidden" name="sesid" value="${atts.get(0).session.id}"/>
            <input type="hidden" name="lid" value="${lid}"/>
             <input type="hidden" name="week" value="${week}"/>
            <table>
                <tr>
                    <th>NO</th>
                    <th>GROUP</th>
                    <th>MASV</th>
                    <th>NAME</th>
                    <th>STATUS</th>
                    <th>COMMENT</th>
                    <th>IMAGE</th>
                </tr>
                <c:forEach items="${atts}" var="att">
                    <tr>
                        <td>${atts.indexOf(att) + 1}</td>
                        <td>${att.session.group.name}</td>
                        <td>${att.student.code}</td>
                        <td>${att.student.name}</td>
                        <td>
                            <input type="hidden" name="attid" value="${att.id}"/>
                           <input type="radio"
                               <c:if test="${att.isPresent()}">
                               checked="checked"
                               </c:if>
                               name="status${att.id}" value="present" />Present
                    <input type="radio"
                               <c:if test="${!att.isPresent()}">
                               checked="checked"
                               </c:if>
                               name="status${att.id}" value="absent" />Absent
                        </td>
                        <td><input type="text" name="comment${att.id}" value="${att.description}"/></td>
                        <td style="width: 5%"><img src="../img/fptlogo.png" width="100%"  alt="avatar"/></td>
                    </tr>
                </c:forEach>

            </table>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>

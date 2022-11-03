<%-- 
    Document   : AttendanceReport
    Created on : Oct 30, 2022, 12:34:36 AM
    Author     : HP
--%>
<jsp:useBean id="AttHelper" class="util.AttendanceRpHelper"/>
<jsp:useBean id="helper" class="util.DateTimeHelper"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../../css/AttendanceReport_style.css"/>

        <script>
            function changeCourse() {
                var x = document.getElementById("course").value;
                window.location.href = '?stdid=' +${requestScope.student.getId()} + '&grid=' + x;
            }
        </script>
    </head>
    <body>
        <header>
            <nav><a href="">Home</a></nav>
            <h1>FPT University Academic Portal</h1>
            <img src="../../img/fptlogo.png">
        </header>

        <h2>View attendance for ${student.name}</h2>

        Select a campus: 
        <select >
            <option selected>FU-HL</option>
        </select>

        Select a term: 
        <select >
            <option>Spring2022</option>
            <option>Summer2022</option>
            <option selected>Fall2022</option>
        </select>


        Select a course:
        <select id="course" onchange="changeCourse();">
            <c:forEach items="${subs}" var="sub">
                <option value="${sub.groups.get(0).getId()}" 
                        <c:if test="${atts.get(0).session.group.id eq sub.groups.get(0).getId()}">
                            selected="selected"
                        </c:if>   >
                    ${sub.description}(${sub.groups.get(0).name})
                </option>
            </c:forEach>
        </select>
        <table >
            <div>
                <tr>
                    <th >NO.</th>
                    <th >DATE</th>
                    <th >SLOT</th>
                    <th >ROOM</th>
                    <th >LECTURER</th>
                    <th >GROUP NAME</th>
                    <th >ATTEDANCE STATUS</th>
                    <th >LECTURER'S COMMENT</th>

                </tr>
            </div>

            <c:forEach items="${atts}" var="att">
                <tr>
                    <td>${att.session.index}</td>
                    <td class="date">
                        ${helper.getDayNameofWeek(att.session.date)}
                        ${helper.format(att.session.date, "dd/MM/yyyy")}
                    </td>
                    <td class="slot">${att.session.timeslot.id}_(${att.session.timeslot.description})</td>
                    <td>${att.session.room.name}</td>
                    <td>${att.session.lecturer.name}</td>
                    <td>${att.session.group.name}</td>
                    <td 
                        <c:if test="${att.isPresent() }">
                            class="present"
                        </c:if>
                        <c:if test="${!att.isPresent() }">
                            class="absent"
                        </c:if>    

                        >
                        <c:if test="${att.isPresent()}">
                            Present
                        </c:if>
                        <c:if test="${!att.isPresent()}">
                            Absent
                        </c:if>
                        <c:if test="${att.isPresent() eq null}">
                            future
                        </c:if> 
                    </td>
                    <td></td>
                </tr>
            </c:forEach>


            <tr>
                <td colspan="7">
                    ABSENT: ${AttHelper.percentAbsent(atts, atts.size())}% ABSENT SO FAR (${AttHelper.countAbsent(atts,atts.size())} ABSENT ON ${atts.size()} TOTAL).
                </td>
                <td></td>
            </tr>
        </table>
    </body>
</html>

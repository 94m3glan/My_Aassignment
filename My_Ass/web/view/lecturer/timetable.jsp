<%-- 
    Document   : timetable
    Created on : Nov 2, 2022, 10:46:44 AM
    Author     : HP
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../css/timetable_style.css"/>
    

        <script>
            function changeWeek() {
                var x = document.getElementById("weeks").value;
                window.location.href = '?lid=' +${lec.id} + '&week=' + x;
            }
        </script>
    </head>
    <body>
        <header>
            <nav><a href="">Home</a></nav>
            <h1>FPT University Academic Portal</h1>
            <img src="../img/fptlogo.png">
        </header>

        <h2>Activities for ${lec.name}</h2>
        <div class="timetable">
            <table>
                <tr><th rowspan="2">
                        YEAR
                        <select>
                            <option>2022</option>
                        </select><br>
                        WEEK
                        <select id="weeks" onchange="changeWeek();">
                            <c:forEach items="${requestScope.weeks}" var="w" >
                                <option value="${w.getIndexWeekOfYear()}" 
                                        <c:if test="${w.getIndexWeekOfYear() eq requestScope.indexCurrentWeek}" >
                                            selected
                                        </c:if>

                                        > ${w.toString()}</option>
                            </c:forEach>

                        </select>

                    </th>
                    <th>MON</th>
                    <th>TUE</th>
                    <th>WED</th>
                    <th>THU</th>
                    <th>FRI</th>
                    <th>SAT</th>
                    <th>SUN</th>
                </tr>
                <tr>
                    <c:forEach items="${requestScope.daysOfWeek}" var="d">
                        <th>${d}</th>
                        </c:forEach>
                </tr>
                <c:forEach items="${requestScope.slots}" var="slot">
                    <tr>
                        
                        <td>Slot ${slot.id}</td>
                        <c:forEach items="${requestScope.currentWeek.getDayList()}" var="day" >
                            <td>
                                <c:forEach items="${requestScope.sessions}" var="ses">
                                    <c:if test="${(ses.getDate().compareTo(day) eq 0 ) and(ses.getTimeslot().getId() eq slot.getId()) }">
                                        ${ses.getGroup().getName()}<br>
                                        at ${ses.getRoom().getName()}<br>

                                        <c:if test="${ses.isAttandated()}">
                                            <abbr class="attended" title="${sessions.get(0).lecturer.name} had attended this activity">Attended</abbr><br>
                                        </c:if>

                                        <c:if test="${!ses.isAttandated()}">
                                            <abbr class="absent" title="${sessions.get(0).lecturer.name} had NOT attended this activity ">Absent</abbr><br>
                                        </c:if>
                                          <c:if test="${ses.isAttandated()}">
                                           Not yet<br>
                                        </c:if>
                                        ${ses. getTimeslot().getDescription()}<br>  

                                    </c:if>

                                </c:forEach>
                            </td>
                        </c:forEach>



                    </tr>
                </c:forEach>
            </table>
    </body>
    </body>
</html>

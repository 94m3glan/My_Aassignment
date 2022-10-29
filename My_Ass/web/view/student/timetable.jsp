<%-- 
    Document   : timetable
    Created on : Oct 26, 2022, 8:27:25 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../css/timetable_style.css"/>

        <script>
            function changeWeek() {
                var x = document.getElementById("weeks").value;
                window.location.href = '?stdid='+${requestScope.student.getId()}+'&week='+x;
            }
        </script>
    </head>
    <body>
        <header>
            <nav><a href="">Home</a></nav>
            <h1>FPT University Academic Portal</h1>
            <img src="../img/fptlogo.png">
        </header>

        <h2>Activities for ${requestScope.student.name}</h2>
        <div class="note">
            <p>
                <b>Note</b>: These activities do not include extra-curriculum activities, such as
                club activities ...
            </p>

            <p>
                <b>Chú thích</b>: Các hoạt động trong bảng dưới không bao gồm hoạt động ngoại khóa,
                ví dụ như hoạt động câu lạc bộ ...
            </p>
        </div>
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
                        <c:forEach items="${requestScope.currentWeek.getDayList()}" var="day">
                            <td>
                                <c:forEach items="${requestScope.sessions}" var="ses">
                                    <c:if test="${(ses.getDate().compareTo(day) eq 0 ) and(ses.getTimeslot().getId() eq slot.getId()) }">
                                        ${ses.getGroup().getName()}<br>
                                        ${ses.getRoom().getName()}<br>
                                        ${ses. getTimeslot().getDescription()}<br>  

                                    </c:if>

                                </c:forEach>
                            </td>
                        </c:forEach>



                    </tr>
                </c:forEach>

                <!--	    	<div class="row">
                                <tr>
                                        <td>Slot 0</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td>
                                </tr>
                                   </div>
                                   <div class="row">
                                <tr>
                                        <td>Slot 1</td>
                                        <td>
                                                <a href="">IOT102-</a><br>
                                                <abbr class="location" title="Delta building">DE-C205</abbr><br>
                                                <abbr class="attended" title=" KhanhHDHE161639 had attended this activity">attended</abbr><br>
                                                <span>(7:30-9:00)</span>
                                        </td>
                                        <td>-</td>
                                        <td>
                                                <a href="">IOT102-</a><br>
                                                <abbr class="location" title="Delta building">DE-C205</abbr><br>
                                                <abbr class="attended" title=" KhanhHDHE161639 had attended this activity">attended</abbr><br>
                                                <span>(7:30-9:00)</span>
                                        </td>
                                        <td>-</td>
                                        <td>
                                                <a href="">IOT102-</a><br>
                                                <abbr class="location" title="Delta building">DE-C205</abbr><br>
                                                <abbr class="attended" title=" KhanhHDHE161639 had attended this activity">attended</abbr><br>
                                                <span>(7:30-9:00)</span>
                                        </td>
                                        <td>-</td>
                                        <td>-</td>
                                </tr>
                                </div>
                                <div class="row">
                                <tr>
                                        <td>Slot 2</td>
                                        <td>
                                                <a href="">PRJ301-</a><br>
                                                <abbr class="location" title="Delta building">DE-C205</abbr><br>
                                                <abbr class="attended" title=" KhanhHDHE161639 had attended this activity">attended</abbr><br>
                                                <span>(9:10-10:40)</span>
                                        </td>
                                        <td>
                                                <a href="">JPD123-</a><br>
                                                <abbr class="location" title="Delta building">DE-C203</abbr><br>
                                                <span>(Not yet)</span><br>
                                                <span>(9:10-10:40)</span>
                                        </td>
                                        <td>
                                                <a href="">PRJ301-</a><br>
                                                <abbr class="location" title="Delta building">DE-C205</abbr><br>
                                                <span>(Not yet)</span><br>
                                                <span>(9:10-10:40)</span>
                                        </td>
                                        <td>-</td>
                                        <td>
                                                <a href="">PRJ301-</a><br>
                                                <abbr class="location" title="Delta building">DE-C205</abbr><br>
                                                <span>(Not yet)</span><br>
                                                <span>(9:10-10:40)</span>
                                        </td>
                                        <td>-</td>
                                        <td>-</td>
                                </tr>
                                         </div>
                                         <div class="row">
                                <tr>
                                        <td>Slot 3</td>
                                        <td>
                                                <a href="">MAS291-</a><br>
                                                <abbr class="location" title="Delta building">DE-C205</abbr><br>
                                                <abbr class="attended" title=" KhanhHDHE161639 had attended this activity">attended</abbr><br>
                                                <span>(10:50-12:20)</span>
                                        </td>
                                        <td>
                                                <a href="">JPD123-</a><br>
                                                <abbr class="location" title="Delta building">DE-C203</abbr><br>
                                                <abbr class="attended" title=" KhanhHDHE161639 had attended this activity">attended</abbr><br>
                                                <span>(10:50-12:20)</span>
                                        </td>
                                        <td>
                                                <a href="">MAS291-</a><br>
                                                <abbr class="location" title="Delta building">DE-C205</abbr><br>
                                                <abbr class="attended" title=" KhanhHDHE161639 had attended this activity">attended</abbr><br>
                                                <span>(10:50-12:20)</span>
                                        </td>
                                        <td>
                                                <a href="">JPD123-</a><br>
                                                <abbr class="location" title="Delta building">DE-C203</abbr><br>
                                                <span>(Not yet)</span><br>
                                                <span>(10:50-12:20)</span>
                                        </td>
                                        <td>
                                                <a href="">MAS291-</a><br>
                                                <abbr class="location" title="Delta building">DE-C205</abbr><br>
                                                <span>(Not yet)</span><br>
                                                <span>(10:50-12:20)</span>
                                        </td>
                                        <td>
                                                <a href="">SWE201c-</a><br>
                                                <abbr class="location" title="Beta building">BE-301</abbr><br>
                                                <span>(Not yet)</span><br>
                                                <span>(10:50-12:20)</span>
                                        </td>
                                        <td>-</td>
                                </tr>
                                         </div>
                                         <div class="row">
                                <tr>
                                        <td>Slot 4</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td>
                                </tr>
                                </div>
                                <div class="row">
                                <tr>
                                        <td>Slot 5</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td>
                                </tr>
                                </div>
                                <div class="row">
                                <tr>
                                        <td>Slot 6</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td>
                                </tr>
                                         </div>
                                         <div class="row">
                                <tr>
                                        <td>Slot 7</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td>
                                        </tr>
                                </div>
                                <div class="row">
                                <tr>
                                        <td>Slot 8</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td>
                                </tr>
                                 </div>
                            </table>
                        </div>-->
                </body>
                </html>

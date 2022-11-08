<%-- 
    Document   : Home
    Created on : Nov 8, 2022, 8:52:11 PM
    Author     : HP
--%>

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

        <h1>Welcome ${student.name}</h1>
        <h2>Information Access(Tra cứu thông tin)</h2>
        <a href="timetable?stdid=${student.id}">Weekly timetable</a>(Thời khóa biểu từng tuần)

        <h2>Reports(Báo cáo)</h2>
        <a href="Report/Attendance?stdid=${student.id}">Attendance Report</a>(Báo cáo điểm danh)
        
        
        <footer> <a href="/My_Ass/logout" >click here to logout</a></footer>
       

    </body>
</html>

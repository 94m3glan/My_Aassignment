/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.lecturer;

import controller.auth.BaseRoleController;
import dal.AttendanceDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import model.Account;
import model.Attendance;
import model.Session;
import util.DateTimeHelper;

/**
 *
 * @author HP
 */
public class TakeAttendance extends BaseRoleController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
            ArrayList<Attendance> atts = new ArrayList<>();
        int lid = Integer.parseInt(req.getParameter("lid"));
        int week = Integer.parseInt(req.getParameter("week"));

        int sesid = Integer.parseInt(req.getParameter("sesid"));
        String[] attid = req.getParameterValues("attid");
        for (String id : attid) {
            Attendance att = new Attendance();
            Session ses = new Session();
            ses.setId(sesid);
            att.setSession(ses);
            att.setId(Integer.parseInt(id));
            att.setPresent(req.getParameter("status" + id).equals("present"));
            att.setDescription(req.getParameter("comment" + id));
            atts.add(att);
        }
        AttendanceDBContext atdb = new AttendanceDBContext();
        atdb.update(atts);
        resp.sendRedirect("timetable?lid=" + lid + "&week=" + week);
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        int grid = Integer.parseInt(req.getParameter("grid"));
        int index = Integer.parseInt(req.getParameter("index"));
        int lid = Integer.parseInt(req.getParameter("lid"));
        int week = Integer.parseInt(req.getParameter("week"));
        AttendanceDBContext atdb = new AttendanceDBContext();
        ArrayList<Attendance> atts = atdb.getByLecturer(grid, index);

        Date day = atts.get(0).getSession().getDate();

        int time = DateTimeHelper.compareToNowByDay(day);
        if (time == - 1) {
            req.setAttribute("lid", lid);
            req.setAttribute("week", week);
            req.setAttribute("atts", atts);
            req.getRequestDispatcher("/lecturer/Attended/view").forward(req, resp);
        } else {
            req.setAttribute("lid", lid);
            req.setAttribute("week", week);
            req.setAttribute("atts", atts);
            req.getRequestDispatcher("/lecturer/addAttendance/view").forward(req, resp);
        }
    }
}

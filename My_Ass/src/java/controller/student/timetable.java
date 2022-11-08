/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.auth.BaseRoleController;
import dal.SessionDBContext;
import dal.StudentDBContext;
import dal.TimeSlotDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import model.Account;
import model.Session;
import model.Student;
import model.TimeSlot;
import model.Week;
import util.DateTimeHelper;

/**
 *
 * @author HP
 */
public class timetable extends BaseRoleController {

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
         int stdid = Integer.parseInt(req.getParameter("stdid"));
        String paramWeek = req.getParameter("week");
        ArrayList<Week> weeks = DateTimeHelper.getAllWeek();

        TimeSlotDBContext tsdb = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = tsdb.list();

        StudentDBContext stddb = new StudentDBContext();
        Student student = stddb.get(stdid);

        int indexWeek;
        if (paramWeek == null) {
            Date now = new Date();
            indexWeek = DateTimeHelper.getWeekOfYear(now);
        } else {
            indexWeek = Integer.parseInt(paramWeek);
        }
        Week currentWeek = DateTimeHelper.getWeekTime(indexWeek);
        ArrayList<String> daysOfWeek = currentWeek.toStringValues();

        SessionDBContext ssdb = new SessionDBContext();
        ArrayList<Session> sessions = ssdb.getByStudent(stdid, new Date(currentWeek.getFrom().getTime()), new Date(currentWeek.getTo().getTime()));

        req.setAttribute("slots", slots);
        req.setAttribute("weeks", weeks);
        req.setAttribute("currentWeek", currentWeek);
        req.setAttribute("indexCurrentWeek", indexWeek);
        req.setAttribute("daysOfWeek", daysOfWeek);
        req.setAttribute("sessions", sessions);
        req.setAttribute("student", student);
        req.getRequestDispatcher("/student/timetable/view").forward(req, resp);
    }



}

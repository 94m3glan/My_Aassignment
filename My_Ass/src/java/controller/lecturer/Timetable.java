package controller.lecturer;


import dal.LecturerDBContext;
import dal.SessionDBContext;
import dal.StudentDBContext;
import dal.TimeSlotDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import model.Lecturer;
import model.Session;
import model.TimeSlot;
import model.Week;
import util.DateTimeHelper;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class Timetable extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int lid = Integer.parseInt(req.getParameter("lid"));
        String paramWeek = req.getParameter("week");
        ArrayList<Week> weeks = DateTimeHelper.getAllWeek();
        
        LecturerDBContext lecdb = new LecturerDBContext();
        Lecturer lec = lecdb.get(lid);
        
        TimeSlotDBContext tsdb = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = tsdb.list();

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
        ArrayList<Session> sessions = ssdb.getByLecturer(lid, new Date(currentWeek.getFrom().getTime()), new Date(currentWeek.getTo().getTime()));
       
        req.setAttribute("slots", slots);
        req.setAttribute("weeks", weeks);
        req.setAttribute("currentWeek", currentWeek);
        req.setAttribute("indexCurrentWeek", indexWeek);
        req.setAttribute("daysOfWeek", daysOfWeek);
        req.setAttribute("sessions", sessions);
        req.setAttribute("lec", lec);
        req.getRequestDispatcher("/lecturer/timetable/view").forward(req, resp);
    }
    
}

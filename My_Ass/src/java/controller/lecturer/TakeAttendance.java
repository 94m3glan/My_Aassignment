/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.lecturer;

import dal.AttendanceDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import model.Attendance;
import model.Session;
import util.DateTimeHelper;

/**
 *
 * @author HP
 */
public class TakeAttendance extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       ArrayList<Attendance> atts = new ArrayList<>();
       int grid = Integer.parseInt(req.getParameter("grid"));
       int index = Integer.parseInt(req.getParameter("index"));
       int sesid = Integer.parseInt(req.getParameter("sesid"));
       String[] attid = req.getParameterValues("attid");
       for(String id : attid){
           Attendance att = new Attendance();
           Session ses = new Session();
           ses.setId(sesid);
           att.setSession(ses);
           att.setId(Integer.parseInt(id));
           att.setPresent(req.getParameter("status"+id).equals("present"));
           att.setDescription(req.getParameter("comment"+id));
           atts.add(att);
       }
       AttendanceDBContext atdb = new AttendanceDBContext();
        atdb.update(atts);
        resp.sendRedirect("SingleAttendanceReport?grid="+grid+"&index="+index);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int grid = Integer.parseInt(req.getParameter("grid"));
        int index = Integer.parseInt(req.getParameter("index"));
        
        AttendanceDBContext atdb = new AttendanceDBContext();
        ArrayList<Attendance> atts = atdb.getByLecturer(grid, index);
        int slot = atts.get(0).getSession().getTimeslot().getId();
        Date day = atts.get(0).getSession().getDate();
        
        day = DateTimeHelper.setTime(day, slot);
        
        int time = DateTimeHelper.compareToNowByDay(day);
//        if(time == - 1){
//        req.setAttribute("atts", atts);
//        req.getRequestDispatcher("/lecturer/Attended/view").forward(req, resp);
//        }
//        else{
              req.setAttribute("grid", grid);
              req.setAttribute("index", index);
              req.setAttribute("atts", atts);
        req.getRequestDispatcher("/lecturer/addAttendance/view").forward(req, resp);
////        }
    }
    
    public static void main(String[] args){
        AttendanceDBContext atdb = new AttendanceDBContext();
        ArrayList<Attendance> atts = atdb.getByLecturer(1, 13);
//        
        for(Attendance att : atts){
            System.out.println(att.isPresent());
        }
//        System.out.println("present: " + atts.get(0).isPresent());
//        System.out.println("record time: " + DateTimeHelper.format(atts.get(0).getRecord_time(), "dd/MM/yyyy") );
        }
    }
    


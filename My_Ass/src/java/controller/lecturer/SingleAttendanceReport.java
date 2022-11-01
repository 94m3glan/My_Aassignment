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
import util.DateTimeHelper;

/**
 *
 * @author HP
 */
public class SingleAttendanceReport extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int grid = Integer.parseInt(req.getParameter("grid"));
        int index = Integer.parseInt(req.getParameter("index"));

        AttendanceDBContext atdb = new AttendanceDBContext();
        ArrayList<Attendance> atts = atdb.getByLecturer(grid, index);
        req.setAttribute("grid", grid);
        req.setAttribute("index", index);
        req.setAttribute("atts", atts);
        req.getRequestDispatcher("/lecturer/Attended/view").forward(req, resp);
    }
    public static void main(String[] args){
         AttendanceDBContext atdb = new AttendanceDBContext();
        ArrayList<Attendance> atts = atdb.getByLecturer(1, 13);
        for(Attendance att : atts){
            System.out.println("record time: "  +att.getRecord_time());
        }
    }
}

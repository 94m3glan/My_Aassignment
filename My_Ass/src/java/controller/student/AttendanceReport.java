/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.auth.BaseRoleController;
import dal.AttendanceDBContext;
import dal.StudentDBContext;
import dal.SubjectDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Attendance;
import model.Student;
import model.Subject;

/**
 *
 * @author HP
 */
public class AttendanceReport extends BaseRoleController {


    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
            int stdid = Integer.parseInt(req.getParameter("stdid"));
       String sem = req.getParameter("semester");
       String grid = req.getParameter("grid");
       if(sem == null){
           sem = "FALL";
       }
        StudentDBContext stdb = new StudentDBContext();
        Student s = stdb.get(stdid);
       
        SubjectDBContext sjdb = new SubjectDBContext();
        ArrayList<Subject> subs = sjdb.get(stdid, sem);
        int Grid = 0;
        if(grid == null){
            Grid = subs.get(0).getGroups().get(0).getId();
       }
        else{
            Grid = Integer.parseInt(grid);
        }
        AttendanceDBContext atdb = new AttendanceDBContext();
        ArrayList<Attendance> atts = atdb.getByStudent(stdid, Grid);
        
        req.setAttribute("student", s);
        req.setAttribute("subs", subs);
        req.setAttribute("atts", atts);
        req.getRequestDispatcher("/student/Report/Attendance/view").forward(req, resp);
    }
}

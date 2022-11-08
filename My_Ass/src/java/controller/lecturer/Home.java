/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.lecturer;

import controller.auth.BaseRoleController;
import dal.GroupDBContext;
import dal.LecturerDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Group;
import model.Lecturer;
import model.Student;

/**
 *
 * @author HP
 */
public class Home extends BaseRoleController{

    @Override
    protected void processPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void processGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        HttpSession ses = req.getSession();
        Account acc = (Account) ses.getAttribute("account");
        
        LecturerDBContext ldb = new LecturerDBContext();
        Lecturer lec = ldb.getByAccount(acc);
        
        GroupDBContext grdb = new GroupDBContext();
        ArrayList<Group> grs = grdb.getByLecturer(lec);
        
        req.setAttribute("groups", grs);
        req.setAttribute("lecturer", lec);
        req.getRequestDispatcher("/lecturer/home/view").forward(req, resp);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.student;

import controller.auth.BaseAuthenticationController;
import controller.auth.BaseRoleController;
import dal.AccountDBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.Account;
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
        
        StudentDBContext sdb = new StudentDBContext();
        Student s = sdb.getByAcount(acc);
        
        req.setAttribute("student", s);
        req.getRequestDispatcher("/student/home/view").forward(req, resp);
    }

    
    
    
}

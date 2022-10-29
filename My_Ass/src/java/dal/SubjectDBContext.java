/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Group;
import model.Subject;

/**
 *
 * @author HP
 */
public class SubjectDBContext extends DBContext<Subject> {

    @Override
    public void insert(Subject model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Subject model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Subject model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Subject get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Subject> get(int stdid, String sem) {
        ArrayList<Subject> subs = new ArrayList<>();
        try {
            String statement = "select sub.subname, sub.[description],gr.gid, gr.gname\n"
                    + "					from [Subject] sub join [Group]	gr on sub.subid = gr.subid\n"
                    + "						  join Student_Group sg on gr.gid = sg.gid\n"
                    + "						  join Student st on st.stdid = sg.stdid\n"
                    + "						  where st.stdid = ? and gr.sem = ?";
            PreparedStatement pstm = connection.prepareStatement(statement);
            pstm.setInt(1, stdid);
            pstm.setString(2, sem);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Subject sub = new Subject();
                Group gr = new Group();
                
                sub.setName(rs.getString("subname"));
                sub.setDescription(rs.getString("description"));
                
                gr.setId(rs.getInt("gid"));
                gr.setName(rs.getString("gname"));
                sub.getGroups().add(gr);
                
                subs.add(sub);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subs;
    }

    @Override
    public ArrayList<Subject> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

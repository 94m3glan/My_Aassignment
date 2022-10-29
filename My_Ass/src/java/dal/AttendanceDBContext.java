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
import model.Attendance;
import model.Group;
import model.Lecturer;
import model.Room;
import model.Session;
import model.TimeSlot;

/**
 *
 * @author HP
 */
public class AttendanceDBContext extends DBContext<Attendance> {

    @Override
    public void insert(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Attendance> get(int stdid, int grid) {
        ArrayList<Attendance> list = new ArrayList<>();
        try {

            String statment = "select ses.[index] ,ses.date, ses.tid, ts.description, r.rname, lc.lname, gr.gname, att.present\n"
                    + "							from attandance att join student s on att.stdid = s.stdid\n"
                    + "							join Session ses on att.sesid = ses.sesid\n"
                    + "							join TimeSlot ts on ses.tid = ts.tid\n"
                    + "							join Room r on ses.rid = r.rid\n"
                    + "							join Lecturer lc on ses.lid = lc.lid\n"
                    + "							join [Group] gr on ses.gid = gr.gid\n"
                    + "							where att.stdid = ? and ses.gid = ?";
            PreparedStatement pstm = connection.prepareStatement(statment);
            pstm.setInt(1, stdid);
            pstm.setInt(2, grid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Attendance att = new Attendance();
                Session ses = new Session();
                TimeSlot ts = new TimeSlot();
                Room r = new Room();
                Lecturer lec = new Lecturer();
                Group gr = new Group();

                ses.setIndex(rs.getInt("index"));
                ses.setDate(new java.util.Date(rs.getDate("date").getTime()));

                ts.setId(rs.getInt("tid"));
                ts.setDescription(rs.getString("description"));
                ses.setTimeslot(ts);

                r.setName(rs.getString("rname"));
                ses.setRoom(r);

                lec.setName(rs.getString("lname"));
                ses.setLecturer(lec);
                
                gr.setId(grid);
                gr.setName(rs.getString("gname"));
                ses.setGroup(gr);

                att.setSession(ses);
                att.setPresent(rs.getBoolean("present"));

                list.add(att);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
    public ArrayList<Attendance> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

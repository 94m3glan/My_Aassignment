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
import model.Student;
import model.Subject;
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

    public void update(ArrayList<Attendance> list) {
        try {
            connection.setAutoCommit(false);
            String statement = "UPDATE [Session] SET attanded = 1 WHERE sesid = ?";
            PreparedStatement pstm = connection.prepareStatement(statement);
            pstm.setInt(1, list.get(0).getSession().getId());
            pstm.executeUpdate();

            for(Attendance att : list){
            String statement2 = "UPDATE [dbo].[Attandance]\n"
                    + "      SET \n"
                    + "      [present] = ?"
                    + "      ,[description] = ?\n"
                    + "      ,[record time] = GETDATE()"
                    + "      WHERE aid = ?		";
            pstm = connection.prepareStatement(statement2);
            pstm.setBoolean(1, att.isPresent());
            pstm.setString(2, att.getDescription());
            pstm.setInt(3, att.getId());
            pstm.executeUpdate();
            }
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Attendance> getByStudent(int stdid, int grid) {
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

    public ArrayList<Attendance> getByLecturer(int grid, int index) {
        ArrayList<Attendance> list = new ArrayList<>();
        try {

            String statement = "select att.aid, ses.sesid, gr.gname, st.masv, st.stdname, att.present, att.description, lec.lname, att.[record time],\n"
                    + "						  sub.subname, ses.tid, ses.[date], gr.sem, gr.[year],  r.rname\n"
                    + "						  from [Session] ses join [Group] gr on ses.gid = gr.gid\n"
                    + "						  join Attandance att on ses.sesid = att.sesid\n"
                    + "						  join Student st on att.stdid = st.stdid\n"
                    + "						  join Lecturer lec on ses.lid = lec.lid\n"
                    + "						  join Subject sub on gr.subid = sub.subid\n"
                    + "						  join Room r on ses.rid = r.rid\n"
                    + "						  where ses.gid = ? and ses.[index] = ?";
            PreparedStatement pstm = connection.prepareStatement(statement);
            pstm.setInt(1, grid);
            pstm.setInt(2, index);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Session ses = new Session();
                Group gr = new Group();
                Student st = new Student();
                Attendance att = new Attendance();
                Lecturer lec = new Lecturer();
                Subject sub = new Subject();
                Room r = new Room();
                TimeSlot ts = new TimeSlot();

                att.setId(rs.getShort("aid"));

                ses.setId(rs.getInt("sesid"));
                ses.setDate(new java.util.Date(rs.getDate("date").getTime()));
                att.setSession(ses);

                gr.setName(rs.getString("gname"));
                gr.setSemester(rs.getString("sem"));
                gr.setYear(rs.getInt("year"));
                ses.setGroup(gr);

                st.setCode(rs.getString("masv"));
                st.setName(rs.getString("stdname"));
                att.setStudent(st);

                att.setPresent(rs.getBoolean("present"));

                att.setDescription(rs.getString("description"));

                lec.setName(rs.getString("lname"));
                ses.setLecturer(lec);

                if (rs.getDate("record time") == null) {
                    att.setRecord_time(null);
                } else {
                    att.setRecord_time(new java.util.Date(rs.getTimestamp("record time").getTime()));
                }
                
                sub.setName(rs.getString("subname"));
                gr.setSubject(sub);

                ts.setId(rs.getInt("tid"));
                ses.setTimeslot(ts);

                r.setName(rs.getString("rname"));
                ses.setRoom(r);

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

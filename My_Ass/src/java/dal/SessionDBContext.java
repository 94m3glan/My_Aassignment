/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Group;
import model.Lecturer;
import model.Room;
import model.Session;
import model.Subject;
import model.TimeSlot;
import util.DateTimeHelper;

/**
 *
 * @author Ngo Tung Son
 */
public class SessionDBContext extends DBContext<Session> {

    public ArrayList<Session> filter(int lid, Date from, Date to) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = "SELECT  \n"
                    + "	ses.sesid,ses.[date],ses.[index],ses.attanded\n"
                    + "	,l.lid,l.lname\n"
                    + "	,g.gid,g.gname\n"
                    + "	,sub.subid,sub.subname\n"
                    + "	,r.rid,r.rname\n"
                    + "	,t.tid,t.[description]\n"
                    + "FROM [Session] ses \n"
                    + "			INNER JOIN Lecturer l ON l.lid = ses.lid\n"
                    + "			INNER JOIN [Group] g ON g.gid = ses.gid\n"
                    + "			INNER JOIN [Subject] sub ON sub.subid = g.subid\n"
                    + "			INNER JOIN Room r ON r.rid = ses.rid\n"
                    + "			INNER JOIN TimeSlot t ON t.tid = ses.tid\n"
                    + "WHERE\n"
                    + "l.lid = ?\n"
                    + "AND ses.[date] >= ?\n"
                    + "AND ses.[date] <= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lid);
            stm.setDate(2, new java.sql.Date(DateTimeHelper.removeTime(from).getTime()));
            stm.setDate(3, new java.sql.Date(DateTimeHelper.removeTime(to).getTime()));
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Session session = new Session();
                Lecturer l = new Lecturer();
                Room r = new Room();
                Group g = new Group();
                TimeSlot t = new TimeSlot();
                Subject sub = new Subject();

                session.setId(rs.getInt("sesid"));
                session.setDate(rs.getDate("date"));
                session.setIndex(rs.getInt("index"));
                session.setAttandated(rs.getBoolean("attanded"));

                l.setId(rs.getInt("lid"));
                l.setName(rs.getString("lname"));
                session.setLecturer(l);

                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                session.setGroup(g);

                sub.setId(rs.getInt("subid"));
                sub.setName(rs.getString("subname"));
                g.setSubject(sub);

                r.setId(rs.getInt("rid"));
                r.setName(rs.getString("rname"));
                session.setRoom(r);

                t.setId(rs.getInt("tid"));
                t.setDescription(rs.getString("description"));
                session.setTimeslot(t);

                sessions.add(session);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessions;
    }

    @Override
    public void insert(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Session> getByStudent(int stdid, Date from, Date to) {
        ArrayList<Session> sessions = new ArrayList<>();

        try {
            String statement = "SELECT ses.sesid, gr.gname, r.rname, at.present, ts.tid, ts.description, ses.date  \n"
                    + "								   FROM Session ses join [Group] gr on ses.gid = gr.gid\n"
                    + "								   join Room r on ses.rid = r.rid\n"
                    + "								   join TimeSlot ts on ses.tid = ts.tid\n"
                    + "								   join Attandance at on ses.sesid = at.sesid\n"
                    + "								   where at.stdid = ? and ses.date between ? and ?";
            PreparedStatement pstm = connection.prepareStatement(statement);
            pstm.setInt(1, stdid);
            pstm.setDate(2, new java.sql.Date(DateTimeHelper.removeTime(from).getTime()));
            pstm.setDate(3, new java.sql.Date(DateTimeHelper.removeTime(to).getTime()));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {

                Session ses = new Session();
                Room r = new Room();
                TimeSlot ts = new TimeSlot();
                Group gr = new Group();
                Attendance att = new Attendance();

                ses.setId(rs.getInt("sesid"));
                ses.setDate(new java.util.Date(DateTimeHelper.removeTime(rs.getDate("date")).getTime()));

                gr.setName(rs.getString("gname"));
                ses.setGroup(gr);

                r.setName(rs.getString("rname"));
                ses.setRoom(r);

                ts.setId(rs.getInt("tid"));
                ts.setDescription(rs.getString("description"));
                ses.setTimeslot(ts);

                att.setPresent(rs.getBoolean("present"));
                ses.getAttandances().add(att);

                sessions.add(ses);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sessions;
    }

    public ArrayList<Session> getByLecturer(int lid, Date from, Date to) {
        ArrayList<Session> sessions = new ArrayList<>();

        try {
            String statement = "select ses.sesid, ses.[index], lec.lname, gr.gid, gr.gname, r.rname, ses.attanded, ts.tid, ts.description, ses.date ,gr.class, sub.subname\n"
                    + "					from Session ses \n"
                    + "					join Lecturer lec on ses.lid = lec.lid\n"
                    + "					join [Group] gr on ses.gid = gr.gid\n"
                    + "					 join Room r on ses.rid = r.rid\n"
                    + "					   join TimeSlot ts on ses.tid = ts.tid\n"
                    + "					   join Subject sub on gr.subid = sub.subid\n"
                    + "					where ses.lid = ? and ses.date between ? and ?";
            PreparedStatement pstm = connection.prepareStatement(statement);
            pstm.setInt(1, lid);
            pstm.setDate(2, new java.sql.Date(DateTimeHelper.removeTime(from).getTime()));
            pstm.setDate(3, new java.sql.Date(DateTimeHelper.removeTime(to).getTime()));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {

                Session ses = new Session();
                Room r = new Room();
                TimeSlot ts = new TimeSlot();
                Group gr = new Group();
                Lecturer lec = new Lecturer();
                Subject sub = new Subject();
                ses.setId(rs.getInt("sesid"));
                ses.setDate(new java.util.Date(DateTimeHelper.removeTime(rs.getDate("date")).getTime()));
                ses.setIndex(rs.getInt("index"));
                
                lec.setId(lid);
                lec.setName(rs.getString("lname"));
                ses.setLecturer(lec);
                
                gr.setId(rs.getInt("gid"));
                gr.setName(rs.getString("gname"));
                gr.setClassname(rs.getString("class"));
                ses.setGroup(gr);

                r.setName(rs.getString("rname"));
                ses.setRoom(r);

                ts.setId(rs.getInt("tid"));
                ts.setDescription(rs.getString("description"));
                ses.setTimeslot(ts);

                ses.setAttandated(rs.getBoolean("attanded"));

                sub.setName(rs.getString("subname"));
                gr.setSubject(sub);
                sessions.add(ses);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sessions;
    }

    @Override
    public ArrayList<Session> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) throws ParseException{
        SessionDBContext ssdb = new SessionDBContext();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        ArrayList<Session> sessions = ssdb.getByStudent(1, sdf.parse("17/10/2022"), sdf.parse("23/10/2022"));
        
        for(Session ses : sessions){
            System.out.println(ses);
        }
    }
}

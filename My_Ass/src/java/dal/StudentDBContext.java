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
import model.TimeSlot;

/**
 *
 * @author HP
 */
public class StudentDBContext extends DBContext<Student> {

    @Override
    public void insert(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int id) {
         Student s = new Student();
        try {
            String statement = "select stdname from student where stdid = ?";
            PreparedStatement pstm = connection.prepareStatement(statement);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            rs.next();
            String name = rs.getString(1);
            s.setId(id);
            s.setName(name);
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
            return s;
    }
    
//    public static void main(String[] args){
//        StudentDBContext stdb = new StudentDBContext();
//        Student s = stdb.get(1);
//        System.out.println(s.getName());
//    }

    @Override
    public ArrayList<Student> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Student get(int id, int gid) {
        try {
            Student student = null;
            Group g = new Group();
            g.setId(gid);
            ArrayList<Group> groups = new ArrayList<>();
            groups.add(g);
            Attendance attend;
            ArrayList<Attendance> attendances = new ArrayList<>();
//                                         1         2          3        4      5       6         7       8         
            String statement = "select s.stdname, ss.sesid, a.present, ss.rid, ss.date, ss.tid, ss.lid, ss.attanded\n"
                    + "						from Student s join Attandance a on s.stdid = a.stdid\n"
                    + "						join [Session] ss on ss.sesid = a.sesid\n"
                    + "						where s.stdid = ? and ss.gid = ?";
            PreparedStatement pstm = connection.prepareStatement(statement);
            pstm.setInt(1, id);
            pstm.setInt(2, gid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                if (student == null) {
                    student = new Student();
//                    create student
                    student.setId(id);
                    student.setName(rs.getString(1));
//                  create attendance
                    attend = new Attendance();
                    Session ses = new Session();
                    ses.setId(rs.getInt(2));
                    attend.setPresent(rs.getBoolean(3));
                    Room room = new Room();
                    room.setId(rs.getInt(4));
                    ses.setRoom(room);
                    ses.setDate(rs.getDate(5));
                    TimeSlot timeSlot = new TimeSlot();
                    timeSlot.setId(rs.getInt(6));
                    ses.setTimeslot(timeSlot);
                    Lecturer lecturer = new Lecturer();
                    lecturer.setId(rs.getInt(7));
                    ses.setLecturer(lecturer);
                    ses.setAttandated(rs.getBoolean(8));
                    attend.setSession(ses);
                    attendances.add(attend);
                } else {
                    attend = new Attendance();
                    Session ses = new Session();
                    ses.setId(rs.getInt(2));
                    attend.setPresent(rs.getBoolean(3));
                    Room room = new Room();
                    room.setId(rs.getInt(4));
                    ses.setRoom(room);
                    ses.setDate(rs.getDate(5));
                    TimeSlot timeSlot = new TimeSlot();
                    timeSlot.setId(rs.getInt(6));
                    ses.setTimeslot(timeSlot);
                    Lecturer lecturer = new Lecturer();
                    lecturer.setId(rs.getInt(7));
                    ses.setLecturer(lecturer);
                    ses.setAttandated(rs.getBoolean(8));
                    attend.setSession(ses);
                    attendances.add(attend);
                }
            }
            student.setGroups(groups);
            student.setAttandances(attendances);
             return student;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
    }

}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author Ngo Tung Son
 */
public class Session {
    private int id;
    private Lecturer lecturer;
    private Room room;
    private TimeSlot timeslot;
    private Date date;
    private Group group;
    private int index;
    private Boolean attandated; 
    private ArrayList<Attendance> attandances = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public TimeSlot getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(TimeSlot timeslot) {
        this.timeslot = timeslot;
    }

    public Date getDate() {
        
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Boolean isAttandated() {
        return attandated;
    }

    public void setAttandated(Boolean attandated) {
        this.attandated = attandated;
    }

    public ArrayList<Attendance> getAttandances() {
        return attandances;
    }

    public void setAttandances(ArrayList<Attendance> attandances) {
        this.attandances = attandances;
    }

    @Override
    public String toString() {
        return "Session{" + "id=" + id + ", lecturer=" + lecturer + ", room=" + room + ", timeslot=" + timeslot + ", date=" + date + ", group=" + group + ", index=" + index + ", attandated=" + attandated + ", attandances=" + attandances + '}';
    }
    
}

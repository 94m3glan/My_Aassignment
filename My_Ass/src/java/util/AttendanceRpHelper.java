/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dal.AttendanceDBContext;
import java.util.ArrayList;
import model.Attendance;

/**
 *
 * @author HP
 */
public class AttendanceRpHelper {

    public static double percentAbsent(ArrayList<Attendance> atts, int num) {
        int absentCount = 0;
        int sofarCount = 0;
        for (int i = 0; i < num; i++) {
            if (!atts.get(i).isPresent()) {
                absentCount++;
            }
            if (atts.get(i).isPresent() != null) {
                sofarCount++;
            }
        }
        double percent = (double) absentCount / sofarCount * 100;
        return Math.round(percent * 100.0) / 100.0;
    }

    public static int countAbsent(ArrayList<Attendance> atts, int num) {
        int absentCount = 0;
        for (int i = 0; i < num; i++) {
            if (!atts.get(i).isPresent()) {
                absentCount++;
            }

        }
        return absentCount;
    }
    
    public static int countAttend(ArrayList<Attendance> atts, int num){
        int attendCount = 0;
        for (int i = 0; i < num; i++) {
            if (atts.get(i).isPresent()) {
                attendCount++;
            }

        }
        return attendCount;
    }

//    public static void main(String[] args){
//        AttendanceDBContext atdb = new AttendanceDBContext();
//        ArrayList<Attendance> atts = atdb.get(1, 1);
//        double percent = percentAbsent(atts);
//        System.out.println(percent);
//    }
}

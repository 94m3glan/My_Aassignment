/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import util.DateTimeHelper;

/**
 *
 * @author HP
 */
public class Week {

    private Date from;
    private Date To;

    public Week() {
    }

    public Week(Date from, Date To) {
        this.from = from;
        this.To = To;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return To;
    }

    public void setTo(Date To) {
        this.To = To;
    }

    String format(int num) {
        int count = 0;
        int temp = num;
        while (temp != 0) {
            temp /= 10;
            count++;
        }
        if (count == 1) {
            return "0" + num;
        }
        return String.valueOf(num);
    }

    String getDayMonthFormat(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return format(calendar.get(Calendar.DAY_OF_MONTH)) + "/" + format(calendar.get(Calendar.MONTH) + 1);
    }

    @Override
    public String toString() {

        return getDayMonthFormat(from) + " To " + getDayMonthFormat(To);
    }


    public ArrayList<String> toStringValues() {
        ArrayList<String> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        String detail;
        for (int i = 0; i < 7; i++) {
            detail = getDayMonthFormat(calendar.getTime());
            list.add(detail);
            calendar.add(Calendar.DATE, 1);
        }
        return list;
    }
    
    public ArrayList<Date> getDayList(){
        ArrayList<Date> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        for (int i = 0; i < 7; i++) {
            Date day = DateTimeHelper.removeTime(calendar.getTime()) ;
            list.add(day);
            calendar.add(Calendar.DATE, 1);
        }
        return list;
    }
    
    public int getIndexWeekOfYear(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        return calendar.get(Calendar.WEEK_OF_YEAR) - 1;
    }
}

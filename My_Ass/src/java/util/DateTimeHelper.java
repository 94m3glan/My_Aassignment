/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import model.Week;

/**
 *
 * @author HP
 */
public class DateTimeHelper {

     public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    public static Week getWeekTime(int nth) {
//        get current time
        Calendar from = Calendar.getInstance();
//        get number of current day in week
        int dayOfWeek = from.get(Calendar.DAY_OF_WEEK);
//        set to first day of week
        from.add(Calendar.DAY_OF_MONTH, -dayOfWeek + 2);
//        get number of current week in year
        int WeekOfYear = from.get(Calendar.WEEK_OF_YEAR);
//        set to first day of nth week of year
        from.add(Calendar.WEEK_OF_YEAR, -WeekOfYear + nth + 1);
        Date fr = from.getTime();
//        initiate last day of week variable
        Calendar to = Calendar.getInstance();
        to.setTime(fr);
//        set to last day of nth week
        to.add(Calendar.DATE, 6);
        Date dto = to.getTime();
        Week week = new Week(fr, dto);
        return week;
    }

    public static ArrayList<Week> getAllWeek() {
        ArrayList<Week> Weeks = new ArrayList<>();
        Week week;
        for (int i = 1; i <= 52; i++) {
            week = getWeekTime(i);
            Weeks.add(week);
        }
        return Weeks;
    }

    public static int getWeekOfYear(Date day) {
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(day);
        return calendar.get(Calendar.WEEK_OF_YEAR) - 1;
    }
    
   public static String format(Date day, String format){
       SimpleDateFormat sdf = new SimpleDateFormat(format);
       return sdf.format(day);
   }
   
    public static String getDayNameofWeek(Date day) {
       
        Calendar c = Calendar.getInstance();
        c.setTime(day);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        switch(dayOfWeek)
        {
            case 1: return "Sun";
            case 2: return "Mon";
            case 3: return "Tue";
            case 4: return "Wed";
            case 5: return "Thu";
            case 6: return "Fri";
            case 7: return "Sat";
        }
        return "Error";
    }


    public static void main(String[] args) {
        ArrayList<Week> Weeks = getAllWeek();
//        for (int i = 1; i <= 52; i++) {
//            System.out.println("week " + i + ":" + Weeks.get(i - 1).toString());
//            System.out.println(Weeks.get(i - 1).toStringValues());
//        }
        Week week = getWeekTime(1);
        int index = week.getIndexWeekOfYear();
        System.out.println("index : " + index);

    }
}

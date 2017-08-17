package org.mwolff.datehelper;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Datehelper {

    public String getWeekday(String datum) {
        
        String[] weekdays =  {
                "nothing",
                "Sonntag",
                "Montag",
                "Dienstag",
                "Mittwoch",
                "Donnerstag",
                "Freitag",
                "Samstag"
        };
        
        String[] datums = datum.split("\\.");
        
        Calendar calendar = new GregorianCalendar(Integer.valueOf(datums[2]),
                Integer.valueOf(datums[1]) -1,
                Integer.valueOf(datums[0]),
                0,0);
        int dow = calendar.get(Calendar.DAY_OF_WEEK);
        return weekdays[dow];
    }

    public boolean isLeap(int year) {
            return ( (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) );
    }

}

package org.example.answer1.util;

import java.sql.Timestamp;
import java.util.Calendar;

public class SetExpirationDate {
    public Timestamp ExpirationDate(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);
        cal.add(Calendar.YEAR, 10);
        timestamp.setTime(cal.getTime().getTime());
        return timestamp;
    }
}

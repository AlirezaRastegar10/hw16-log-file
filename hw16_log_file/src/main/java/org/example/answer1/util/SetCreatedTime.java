package org.example.answer1.util;

import java.sql.Timestamp;

public class SetCreatedTime {
    public Timestamp setTime(){
        return new Timestamp(System.currentTimeMillis());
    }
}

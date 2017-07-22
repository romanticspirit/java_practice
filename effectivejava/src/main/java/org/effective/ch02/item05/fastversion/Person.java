package org.effective.ch02.item05.fastversion;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by stephen on 17/7/8.
 */
public class Person {
    private final Date birthDate;

    public Person(Date birthDate){
        this.birthDate = birthDate;
    }


    private static final Date BOOM_START;
    private static final Date BOOM_END;

    static {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();
    }


    public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >= 0
                && birthDate.compareTo(BOOM_END) < 0;
    }

}

package misc;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateComparator {
    public static boolean equalDates(Date date1, Date date2) {
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(date1);

        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(date2);

        int year1 = calendar1.get(Calendar.YEAR);
        int year2 = calendar2.get(Calendar.YEAR);

        int month1 = calendar1.get(Calendar.MONTH);
        int month2 = calendar2.get(Calendar.MONTH);

        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
        int day2 = calendar1.get(Calendar.DAY_OF_MONTH);

        return year1 == year2 && month1 == month2 && day1 == day2;
    }
}

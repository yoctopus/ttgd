/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.main;

/**
 *
 * @author Vincent
 * 
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
public class TimeTableCalender extends GregorianCalendar {
    private static final long serialVersionUID = 6706860063952093210L;
    
    private Calendar cal = new TimeTableCalender();

    public TimeTableCalender(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        super(year, month, dayOfMonth, hourOfDay, minute);
    }

    public TimeTableCalender() {
    }

    public TimeTableCalender(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    
    
}

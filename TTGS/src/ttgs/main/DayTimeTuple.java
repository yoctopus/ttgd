/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.main;

import java.util.Objects;
import ttgs.ds.tt.TimeSlot;

/**
 *
 * @author Vincent
 */
public class DayTimeTuple {
    private String day;
    private TimeSlot time;
    private boolean active;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.day);
        hash = 19 * hash + Objects.hashCode(this.time);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DayTimeTuple other = (DayTimeTuple) obj;
        if (!Objects.equals(this.day, other.day)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }

    public DayTimeTuple(String day, TimeSlot time) {
        this.day = day;
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public TimeSlot getTime() {
        return time;
    }

    public void setTime(TimeSlot time) {
        this.time = time;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive() {
        this.active = true;
    }
    public void setInactive() {
        this.active = false;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.ds.tt;

import java.time.Duration;
import java.time.LocalTime;

import java.util.Objects;
import ttgs.algorithms.Engine;

/**
 *
 * @author Vincent
 */
public class TimeSlot {
    private TTGSDate start, end;
    private Duration duration;
    private boolean occupied;

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    

    public TTGSDate getStart() {
        return start;
    }

    public void setStart(TTGSDate start) {
        this.start = start;
    }

    public TTGSDate getEnd() {
        return end;
    }

    public void setEnd(TTGSDate end) {
        this.end = end;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public TimeSlot(TTGSDate start, Duration duration) {
        this.start = start;
        this.duration = duration;
        calcDate();
        occupied = false;
    }

    public TimeSlot(TTGSDate start, TTGSDate end) {
        this.start = start;
        this.end = end;
        calcDuration();
        occupied = false;
    }
    
    private void calcDuration() {
        this.setDuration(Engine.calcDurationBetween(getStart(), getEnd()));
    }
    
    private void calcDate() {
        this.setEnd(Engine.calcLocalEndTime(getStart(), getDuration()));
    }
    
     @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.getStart());
        hash = 89 * hash + Objects.hashCode(this.getEnd());
        hash = 89 * hash + Objects.hashCode(this.getDuration());
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
        final TimeSlot other = (TimeSlot) obj;
        if (!Objects.equals(this.start, other.start)) {
            return false;
        }
        if (!Objects.equals(this.end, other.end)) {
            return false;
        }
        if (!Objects.equals(this.duration, other.duration)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       LocalTime lstart = Engine.getLocalTimefromDate(getStart());
       LocalTime lend = Engine.getLocalTimefromDate(getEnd());
       return lstart.toString()+" to "+lend.toString()+", Duration of "+this.getDuration().getSeconds()+" seconds";
    }
    
}

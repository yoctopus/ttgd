/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.main;

import java.util.ArrayList;
import ttgs.ds.Lecturer;
import ttgs.ds.Room;
import ttgs.ds.ActiveUnit;
import ttgs.ds.Group;
import ttgs.ds.tt.Day;
import ttgs.ds.tt.TimeSlot;
import ttgs.ds.tt.WeekDays;

/**
 *
 * @author Vincent
 */
public class GenData {

    private static int count = 1;
    private int id;
    private ArrayList<ActiveUnit> units;
    private ArrayList<Room> rooms;
    private ArrayList<TimeSlot> timeslots;
    private Group group;
    
    private ArrayList<Day> days;

    public GenData(ArrayList<ActiveUnit> units, ArrayList<Room> rooms, ArrayList<TimeSlot> timeslots,
            Group group, ArrayList<Day> days) {
        this.units = units;
        this.rooms = rooms;
        this.timeslots = timeslots;
        this.group = group;
        
        this.days = days;
        this.id = count;
        count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<ActiveUnit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<ActiveUnit> units) {
        this.units = units;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<TimeSlot> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(ArrayList<TimeSlot> timeslots) {
        this.timeslots = timeslots;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

       public ArrayList<Day> getDays() {
        return days;
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }

}

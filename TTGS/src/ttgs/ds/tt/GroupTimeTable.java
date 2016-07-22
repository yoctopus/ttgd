/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.ds.tt;

import java.util.ArrayList;
import ttgs.ds.Group;
import ttgs.main.DayTimeTuple;

/**
 *
 * @author Vincent
 */
public class GroupTimeTable {
    
    private Group students;
    private ArrayList<DayTimeTuple> tuples;
    
    public GroupTimeTable(Group students, ArrayList<Day> timetable) {
        this.students = students;
        this.timetable = timetable;
    }
    
    public void addLecture(Lecture lecture) {
        for (int i = 0; i < this.timetable.size(); i++) {
            if (this.timetable.get(i).getDay().equals(lecture.getDay())) {
                this.timetable.get(i).addLecture(lecture);
            }
            
        }
    }   
    
    
    public void setStudents(Group students) {
        this.students = students;
    }
    
    public Group getStudents() {
        return students;
    }
    private ArrayList<Day> timetable;    
    
    public ArrayList<Day> getTimetable() {
        return timetable;
    }
    
    public void setTimetable(ArrayList<Day> timetable) {
        this.timetable = timetable;
    }
    
    public void addDay(Day tt) {
        this.timetable.add(tt);
    }
    
    public ArrayList<Lecture> getlectures() {
        ArrayList<Lecture> lectures = new ArrayList();
        for (Day day : this.getTimetable()) {
            lectures.addAll(day.getLectures());
        }
        
        return lectures;
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<DayTimeTuple> getTuples() {
        return tuples;
    }

    public void setTuples(ArrayList<DayTimeTuple> tuples) {
        this.tuples = tuples;
    }
}

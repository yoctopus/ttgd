package ttgs.ds.tt;

import java.util.ArrayList;
import ttgs.algorithms.Engine;
/**
 *
 * @author Vin
 */
public class Day {
    private ArrayList<Lecture> lectures;
    private String day;
    public ArrayList<Lecture> getLectures() {
        return lectures;
    }
    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public Day(String day, ArrayList<Lecture> lectures) {
        this.lectures = (ArrayList)lectures.clone();
        this.day = day;
    }
    public Day(String day) {
        this(day, new ArrayList<>());
    }
    public void addLecture(Lecture lecture) {
        this.lectures.add(lecture);
    }
    public Lecture getLecture(int index) {
        return this.lectures.get(index);
    }
    public void removeLecture(int index) {
        this.lectures.remove(index);
    }
    public void removeLecture(Lecture lecture) {
        this.lectures.remove(lecture);
    }
    public void ClearTT() {
        this.lectures.clear();
    }
    public void swapLectures(Lecture a, Lecture b) {
        Engine.swapLectures(a, b);
    }    
    
    
}

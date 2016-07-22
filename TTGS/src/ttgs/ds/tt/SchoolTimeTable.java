package ttgs.ds.tt;

import ttgs.ds.School;
import java.util.ArrayList;
import ttgs.ds.ActiveUnit;
import ttgs.main.DayTimeTuple;

/**
 *
 * @author Vin
 */
public class SchoolTimeTable {
    private ArrayList<GroupTimeTable> tts;    
    private School school;
    public ArrayList<GroupTimeTable> getTts() {
        return tts;
    }
    public void setTts(ArrayList<GroupTimeTable> tts) {
        this.tts = tts;
    }
    public School getSchool() {
        return school;
    }
    public void setSchool(School school) {
        this.school = school;
    }
    public SchoolTimeTable(School school) {
        this.school = school;
        this.tts = new ArrayList();
    }
    
    public ArrayList<Lecture> getLecturesForSlot(DayTimeTuple tuple) {        
        
        ArrayList<Lecture> units = new ArrayList();
        for (GroupTimeTable gtt : this.tts) {
            for (Lecture lecture : this.getLectures()) {
                if (lecture.getDay().equals(tuple.getDay()) && lecture.getSlot().equals(tuple.getTime())) {
                    units.add(lecture);
                }
            }
        }
        
        return units;
    }
    
    public ArrayList<Lecture> getLectures() {
        ArrayList<Lecture> lectures = new ArrayList();
        
        for(GroupTimeTable gtt : this.getTts()) {
            lectures.addAll(gtt.getlectures());
        }
        
        return lectures;
    }
   
    public void addTimeTable(GroupTimeTable tt) {
        this.tts.add(tt);
    }
    public void removeTimeTable(int index) {
        this.tts.remove(index);
    }
    public void ClearTT() {
        this.tts.clear();
    }
}

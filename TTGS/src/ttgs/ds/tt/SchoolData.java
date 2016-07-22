package ttgs.ds.tt;

import java.util.ArrayList;
import ttgs.algorithms.Engine;
import ttgs.ds.Course;
import ttgs.ds.Faculty;
import ttgs.ds.Room;
import ttgs.ds.School;
import ttgs.ds.Group;
import ttgs.ds.Unit;

/**
 *
 * @author Vincent
 */
public class SchoolData {    
    private class Inner {
        private int sid;
        private School school;
        public Inner(int sid, School school) {
            this.sid = sid;
            this.school = school;
        }
        public int getSid() { return sid; }
        public void setSid(int sid) { this.sid = sid; }
        public School getSchool() { return school; }
        public void setSchool(School school) { this.school = school; }
        private Inner() {
            
        }
    }
    public SchoolData() {
        schoolsData = new ArrayList();
    }
    private ArrayList<Inner> schoolsData;
    public void addSchoolData(int sid, School school) {
        Inner newInner = new Inner(sid, school);
        schoolsData.add(newInner);
    }
    public School getSchoolData(String name, int sid) {
        School temp = new School(name, sid);
        for(Inner n : getSchoolsData()) {
            if (n.getSid() == sid) { temp =  n.getSchool(); }
            else { temp =  null; }
        }
        return temp;
    }
    
    public School getSchool(int sid) {
        for (School school : getSchools()) {
            if (school.getId() == sid) {
                return school;
            }
        }
        return null;
    }
    
    public ArrayList<School>  getSchools () {
        ArrayList<School> schools = new ArrayList();
        for(Inner in : getSchoolsData()) {
            schools.add(in.school);
        }
        return schools;
    }
    public int getNumberofSchools() {
        return schoolsData.size();
    }
    public ArrayList<Inner> getSchoolsData() { return schoolsData; }
    public void setSchoolsData(ArrayList<Inner> schoolsData) { this.schoolsData = schoolsData; }
    
    public void clearData() {
       schoolsData.clear(); 
    }    
    
    public void sortSchoolsWithID() {
        ArrayList<School> schools = getSchools();        
        Engine.sortSchools(schools);
        clearData();
        for(int i = 0; i < schools.size(); i++) {
            addSchoolData(i, schools.get(i));
        }
    }
    
}

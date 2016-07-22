
package ttgs.ds.tt;

import java.util.ArrayList;
import ttgs.ds.Course;

/**
 *
 * @author Vin
 */
public class GroupedCourses {
    private class Inner {
        private int did;
        private ArrayList<Course> courses;
        public int getDid() { return did; }
        public void setDid(int did) { this.did = did; }
        public ArrayList<Course> getCourses() { return courses; }
        public void setCourses(ArrayList<Course> courses) { this.courses = courses; }
        public Inner(int did, ArrayList<Course> courses) { this.did = did;
            this.courses = courses;
        }
    }
    
    private ArrayList<Inner> courses;
    public ArrayList<Inner> getCourses() {
        return courses;
    }
    public ArrayList<Course> getCourses(int did) {
        ArrayList<Course> local = new ArrayList();
        for(Inner n : getCourses()) {
            if (n.getDid() == did) { local =  n.getCourses(); }
            else { local =  null; }
        }
        return local;
    }
    public GroupedCourses() {
        courses = new ArrayList();
    }
    public void addGroup(int did, ArrayList<Course> course) {
        Inner newIn = new Inner(did, course);
        courses.add(newIn);
    }    
    public void Clear() {
        this.courses.clear();
    }
    
}

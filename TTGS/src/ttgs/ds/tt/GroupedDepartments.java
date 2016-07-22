
package ttgs.ds.tt;

import java.util.ArrayList;
import ttgs.ds.Course;
import ttgs.ds.Faculty;

/**
 *
 * @author Vin
 */
public class GroupedDepartments {  
    private class Inner {
        private int sid;
        private ArrayList<Faculty> depts;
        public int getSid() { return sid; }
        public void setSid(int sid) { this.sid = sid; }
        public ArrayList<Faculty> getDept() { return depts; }
        public void setDept(ArrayList<Faculty> dept) { this.depts = dept; }
        public Inner(int sid, ArrayList<Faculty> dept) {
            this.sid = sid;
            this.depts = dept;
        }
    }
    private ArrayList<Inner> d;
    public ArrayList<Inner> getDepts() { return d; }
    public ArrayList<Faculty> getDepts(int sid) {
        ArrayList<Faculty> local = new ArrayList();
        for(Inner n : getDepts()) {
            if (n.getSid() == sid) { local =  n.getDept(); }
            else { local =  null; }
        }
        return local;
    }        
    public GroupedDepartments() {
        d = new ArrayList();
    }
    public void addGroup(int sid, ArrayList<Faculty> depts) {
        Inner newIn = new Inner(sid, depts);
        d.add(newIn);
    }
    public ArrayList<Course> getCoursesforDepartment(int did, GroupedCourses gc) {
        return gc.getCourses(did);
    }
}

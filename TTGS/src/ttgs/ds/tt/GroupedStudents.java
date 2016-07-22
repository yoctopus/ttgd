package ttgs.ds.tt;

import java.util.ArrayList;
import ttgs.ds.Group;

/**
 *
 * @author Vin
 */
public class GroupedStudents {
    private ArrayList<Group> gstudents;
    public void addStudents(Group newStudents) {
        this.gstudents.add(newStudents);
    }
    public void removeStudents(int index) {
        this.gstudents.remove(index);
    }
    public void ClearStudents() {
        this.gstudents.clear();
    }
    public ArrayList<Group> getStudents() {
        return this.gstudents;
    }
    public GroupedStudents() {
        gstudents = new ArrayList();
    }
}

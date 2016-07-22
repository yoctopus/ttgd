package ttgs.ds;

/**
 *
 * @author Vin
 */
import java.util.ArrayList;
import ttgs.ds.tt.GroupTimeTable;

public class School implements Comparable {

    private String name;

    private int id;

    private ArrayList<StudentClass> groups;

    private ArrayList<Room> rooms;

    private ArrayList<Lecturer> lecturers;
    private ArrayList<Faculty> departments;
    private ArrayList<GroupTimeTable> timetables;

    public School(String name, int id) {
        this.setName(name);
        this.setID(id);

        lecturers = new ArrayList();
        departments = new ArrayList();

        groups = new ArrayList();
    }

    @Override
    public int compareTo(Object o) {
        School other = (School) o;
        int a = this.getId();
        int b = other.getId();
        return a > b ? 1 : (b < a ? -1 : 0);
    }

    public boolean hasDepartments() {
        return !this.departments.isEmpty();
    }
    public boolean hasRooms() {
        return !this.rooms.isEmpty();
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    private ArrayList<StudentClass> getGroups() {
        return groups;
    }

    public void addStudentGroup(Course cid, ArrayList<Group> group) {
        StudentClass c = new StudentClass(cid, group);
        getGroups().add(c);
    }

    public ArrayList<Group> getAllGroups() {
        ArrayList<Group> gr = new ArrayList();
        for (StudentClass c : getGroups()) {
            gr.addAll(c.getStugroup());
        }
        return gr;
    }

    public ArrayList<Group> getStudentGroup(Course cid) {
        for (StudentClass c : getGroups()) {
            if (c.getCourse().getId() == cid.getId()) {
                return c.getStugroup();
            }
        }
        return null;
    }

    private void setID(int id) {
        this.setId(id);
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getRooms() {
        return this.rooms;
    }

    public ArrayList<Faculty> getDepartments() {
        return departments;
    }

    public Faculty getDepartment(int id) {
        int i = 0;
        for (Faculty d : getDepartments()) {
            if (d.getId() == id) {
                return getDepartments().get(i);
            }
            i++;
        }
        return null;
    }

    public void setDepartments(ArrayList<Faculty> departments) {
        this.departments = departments;
    }
    
    public void addDepartments(ArrayList<Faculty> departments) {
        this.getDepartments().addAll(departments);
    }

    public ArrayList<GroupTimeTable> getTimetables() {
        return timetables;
    }

    public void setTimetables(ArrayList<GroupTimeTable> timetables) {
        this.timetables = timetables;
    }
    
    public boolean hasGroup(Course c, Stage st) {
        int i = 0;
        for (Group sgrp : this.getStudentGroup(c)) {
            if (sgrp.getStage().getName().equals(st.getName())) {
                return true;
            }
            i++;
        }
        return false;
    }

    public Group getGroup(Course c, Stage s) {
        int i = 0;
        for (Group sgrp : this.getStudentGroup(c)) {
            if (sgrp.getStage().getName().equals(s.getName())) {
                return sgrp;
            }
            i++;
        }
        return null;
    }

    public boolean addCourses(int did, ArrayList<Course> coses) {
        int i = 0;
        for (int k = 0; k < this.getDepartments().size(); k++) {
            if (getDepartments().get(i).getId() == did) {
                for (Course c : coses) {
                    this.getDepartments().get(i).addCourse(c);
                }
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean addUnits(int cid, ArrayList<ActiveUnit> unts) {
        int i = 0, j = 0;
        for (Faculty d : this.getDepartments()) {
            for (Course c : d.getCourses()) {
                if (c.getId() == cid) {
                    if (d.getId() == c.getDept_id()) {
                        this.getDepartments().get(j).getCourses().get(i).setActiveUnits(unts);
                        return true;
                    }
                    j++;
                }
            }
            i++;
        }
        return false;
    }

    public ArrayList<Course> getCourses(int did) {
        for (Faculty d : this.getDepartments()) {
            if (d.getId() == did && d.hasCourses()) {
                return d.getCourses();
            }
        }
        return null;
    }

    private ArrayList<Course> getCourses() {
        ArrayList<Course> myCourses = new ArrayList();
        for (Faculty d : this.getDepartments()) {
            if (d.hasCourses()) {
                myCourses.addAll(d.getCourses());
            }
        }
        return myCourses;
    }

    public ArrayList<ActiveUnit> getUnits(int cid) {
        for (Course c : this.getCourses()) {
            if (c.hasUnits() && c.getId() == cid) {
                return c.getActiveUnits();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    /**
     * @param groups the groups to set
     */
    public void setGroups(ArrayList<StudentClass> groups) {
        this.groups = groups;
    }

    private class StudentClass {

        private Course course;

        public Course getCourse() {
            return course;
        }

        public void setCourse(Course course) {
            this.course = course;
        }

        public ArrayList<Group> getStugroup() {
            return stugroup;
        }

        public void setStugroup(ArrayList<Group> stugroup) {
            this.stugroup = stugroup;
        }

        public StudentClass(Course course, ArrayList<Group> stugroup) {
            this.course = course;
            this.stugroup = stugroup;
        }
        private ArrayList<Group> stugroup;
    }
}

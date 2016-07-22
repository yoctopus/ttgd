package ttgs.ds;

import java.util.ArrayList;

/**
 *
 * @author Vin
 */
public class Faculty {

    private String name;
    private int id;

    private int sch_id;
    private ArrayList<Course> courses;

    public Faculty(String name, int dep_id, ArrayList<Course> courses, int sch_id) {
        this.name = name;
        this.id = dep_id;
        this.courses = courses;
        this.sch_id = sch_id;
    }

    public boolean hasCourses() {
        return !this.courses.isEmpty();
    }

    public Course getCourse(int id) {
        int i = 0;
        for (Course c : getCourses()) {
            if (c.getId() == id) {
                return getCourses().get(i);
            }
            i++;
        }
        return null;
    }

    public int getSch_id() {
        return sch_id;
    }

    public void setSch_id(int sch_id) {
        this.sch_id = sch_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course c) {
        this.getCourses().add(c);
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}

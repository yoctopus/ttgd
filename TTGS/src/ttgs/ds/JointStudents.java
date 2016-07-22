package ttgs.ds;

/**
 *
 * @author Vin
 */
import ttgs.algorithms.Engine;
import java.util.ArrayList;
public class JointStudents {
    private ArrayList<Group> students;
    private int number;    
    private void setNumber(int number) {this.number = number;}

    /**
     *
     * @return
     */
    
    public int getNumber() {return this.number;}
    public void setStudents(ArrayList<Group> students) {
        this.students = students;
    }
    public ArrayList<Group> getStudents() {return this.students;}
    public JointStudents(int number, ArrayList<Group> students) {        
        this.setStudents(students);
        this.setNumber(findTotal());
    }
    private int findTotal() {
        return Engine.getTotalOfStudents(getStudents());
    }
    private void addStudentGroup(Group sgrp) {
        students.add(sgrp);
        refresh();
    }
    private void refresh() {
        this.setNumber(findTotal());
    }
    @Override
    public String toString() {
        return this.getNumber()+ "";
    }    
}

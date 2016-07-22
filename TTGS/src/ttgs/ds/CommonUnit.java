package ttgs.ds;
/**
 *
 * @author Vin
 */
public class CommonUnit extends ActiveUnit{  

    private JointStudents students;

    public JointStudents getStudents() {
        return students;
    }

    public void setStudents(JointStudents students) {
        this.students = students;
    }
    public CommonUnit(Unit unit, Lecturer lecturer, Stage stage, int num_of_lessons) {
        super(unit, lecturer, stage, num_of_lessons);
    }   
    
    
    @Override
    public String toString() {
        return super.toString();
    }
}

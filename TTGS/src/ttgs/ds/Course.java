package ttgs.ds;

import java.util.ArrayList;

/**
 *
 * @author Vin
 */
public final class Course {

    private String name;

    private int id;

    private int dept_id;
    private ArrayList<Unit> units;
    private ArrayList<ActiveUnit> activeUnits;
    private ArrayList<Group> student;

    public Course(String name, int id, int dept_id, ArrayList<Unit> units) {
        this.setName(name);
        this.id = id;
        this.dept_id = dept_id;
        this.units = units;
        activeUnits = new ArrayList();
        
        student = new ArrayList();
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public boolean hasUnits() {
        return !this.activeUnits.isEmpty();
    }

    public boolean hasUnitsforStage(Stage stage) {
        for (ActiveUnit unit : getActiveUnits()) {
            if (unit.getStage().getName().equals(stage.getName())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<ActiveUnit> getUnitsforStage(Stage stage) {
        ArrayList<ActiveUnit> ans = new ArrayList();
        for (ActiveUnit unit : getActiveUnits()) {
            if (unit.getStage().getName().equals(stage.getName())) {
                ans.add(unit);
            }
        }

        return ans;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }    

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public ArrayList<ActiveUnit> getActiveUnits() {
        return activeUnits;
    }

    public void setActiveUnits(ArrayList<ActiveUnit> activeUnits) {
        this.activeUnits = activeUnits;
    }

    public ArrayList<Group> getStudent() {
        return student;
    }

    public void setStudent(ArrayList<Group> student) {
        this.student = student;
    }

    public Course getCourse() {
        return this;
    }

    @Override
    public String toString() {
        return this.getId() + this.getName();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}

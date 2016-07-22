package ttgs.ds;

import java.util.Comparator;
import ttgs.rules.Defaults.SortDefaults;

/**
 *
 * @author Vin
 */
public class ActiveUnit extends Unit implements Comparable {

    private static int ASCENDING = SortDefaults.ACSENDING;
    private static int DESCENDING = SortDefaults.DESCENDING;
    private static int compareMode = ASCENDING;

    public static int getCompareMode() {
        return compareMode;
    }

    public static void setCompareMode(int compareMode) {
        ActiveUnit.compareMode = compareMode;
    }

    /**
     * @return the ASCENDING
     */
    public static int getASCENDING() {
        return ASCENDING;
    }

    /**
     * @param aASCENDING the ASCENDING to set
     */
    public static void setASCENDING(int aASCENDING) {
        ASCENDING = aASCENDING;
    }

    /**
     * @return the DESCENDING
     */
    public static int getDESCENDING() {
        return DESCENDING;
    }

    /**
     * @param aDESCENDING the DESCENDING to set
     */
    public static void setDESCENDING(int aDESCENDING) {
        DESCENDING = aDESCENDING;
    }

    private Lecturer lecturer;
    private Stage stage;
    private int id;   
    
    private int num_of_lectures_in_a_week;

    public ActiveUnit(Unit unit, Lecturer lecturer, Stage stage, int num_of_lessons) {
        super(unit.getCode(), unit.getName(), unit.getC_id());
        this.lecturer = lecturer;
        this.stage = stage;
        this.num_of_lectures_in_a_week = num_of_lessons;
    }    

    public int getNum_of_lectures_in_a_week() {
        return num_of_lectures_in_a_week;
    }

    public void setNum_of_lectures_in_a_week(int num_of_lectures_in_a_week) {
        this.num_of_lectures_in_a_week = num_of_lectures_in_a_week;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Lecturer getLecturer() {
        return this.lecturer;
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public String toString() {
        return super.toString() + this.getLecturer().toString();
    }

    @Override
    public int compareTo(Object o) {
        ActiveUnit other = (ActiveUnit) o;
        int a = this.getNum_of_lectures_in_a_week();
        int b = other.getNum_of_lectures_in_a_week();
        return a > b ? 1 : (b < a ? -1 : 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

}

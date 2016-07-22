package ttgs.ds;

/**
 *
 * @author Vin
 */
public class Unit {

    private String code;

    private int c_id;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    private int courseId;

    public Unit(String code, String name, int c_id) {
        this.code = code;
        this.name = name;
        this.c_id = c_id;

    }    

    @Override
    public String toString() {
        return this.getCode() + " : " + this.getName();
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }
}

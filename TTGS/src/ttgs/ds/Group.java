package ttgs.ds;

/**
 *
 * @author Vin
 */
public class Group {

    private Course course;
    private Stage stage;
    private int number;
    private int id;

    public Group(Course course, Stage stage, int number) {
        this.course = course;
        this.stage = stage;
        this.number = number;

    }

    private void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public String toString() {
        return this.getNumber() + "";
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

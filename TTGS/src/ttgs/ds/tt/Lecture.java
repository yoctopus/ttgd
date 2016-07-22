package ttgs.ds.tt;

import java.util.Date;
import ttgs.ds.Room;
import ttgs.ds.ActiveUnit;
import ttgs.ds.Lecturer;
import ttgs.ds.Stage;
import ttgs.ds.Group;
import ttgs.rules.Defaults.LectureDefaults;

/**
 *
 * @author Vin
 */
public class Lecture {

    private static int LECTURE_IS_ONGOING = LectureDefaults.LECTURE_IS_ONGOING;
    private static int LECTURE_IS_MISSED = LectureDefaults.LECTURE_IS_MISSED;
    private int state;
    private Room room;
    private Stage stage;
    private String description;
    private ActiveUnit semunit;
    private Lecturer lecturer;
    private Group students;
    private Date startime, endtime;
    private String day;
    private TimeSlot slot;

    public Lecture(Room room, ActiveUnit unit, TimeSlot slot, String day) {
        this.room = room;
        this.slot = slot;
        this.semunit = unit;
        this.day = day;
        this.lecturer = unit.getLecturer();
        this.stage = unit.getStage();
        setDates();

    }

    private void setDates() {

        this.setStartime(new Date());
        this.setEndtime(new Date());

        this.getStartime().setYear(this.getSlot().getStart().getYear());
        this.getStartime().setMonth(this.getSlot().getStart().getYear());
        this.getStartime().setHours(this.getSlot().getStart().getHours());
        this.getStartime().setMinutes(this.getSlot().getStart().getMinutes());
        this.getStartime().setSeconds(this.getSlot().getStart().getSeconds());

        this.getEndtime().setYear(this.getSlot().getEnd().getYear());
        this.getEndtime().setMonth(this.getSlot().getEnd().getMonth());
        this.getEndtime().setHours(this.getSlot().getEnd().getHours());
        this.getEndtime().setMinutes(this.getSlot().getEnd().getMinutes());
        this.getEndtime().setSeconds(this.getSlot().getEnd().getSeconds());
    }

    public boolean isOngoing() {
        return this.getState() == getLECTURE_IS_ONGOING();
    }

    public Date getStartime() {
        return startime;
    }

    public void setStartime(Date startime) {
        this.startime = startime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return this.state;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ActiveUnit getSemunit() {
        return semunit;
    }

    public void setSemunit(ActiveUnit semunit) {
        this.semunit = semunit;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Group getStudents() {
        return students;
    }

    public void setStudents(Group students) {
        this.students = students;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    public void setSlot(TimeSlot slot) {
        this.slot = slot;
    }

    /**
     * @return the LECTURE_IS_ONGOING
     */
    public static int getLECTURE_IS_ONGOING() {
        return LECTURE_IS_ONGOING;
    }

    /**
     * @param aLECTURE_IS_ONGOING the LECTURE_IS_ONGOING to set
     */
    public static void setLECTURE_IS_ONGOING(int aLECTURE_IS_ONGOING) {
        LECTURE_IS_ONGOING = aLECTURE_IS_ONGOING;
    }

    /**
     * @return the LECTURE_IS_MISSED
     */
    public static int getLECTURE_IS_MISSED() {
        return LECTURE_IS_MISSED;
    }

    /**
     * @param aLECTURE_IS_MISSED the LECTURE_IS_MISSED to set
     */
    public static void setLECTURE_IS_MISSED(int aLECTURE_IS_MISSED) {
        LECTURE_IS_MISSED = aLECTURE_IS_MISSED;
    }
}

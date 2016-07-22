package ttgs.main;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import ttgs.ds.ActiveUnit;
import ttgs.ds.Course;
import ttgs.ds.Group;
import ttgs.ds.Lecturer;
import ttgs.ds.Room;
import ttgs.ds.tt.Day;
import ttgs.ds.tt.GroupTimeTable;
import ttgs.ds.tt.Lecture;
import ttgs.ds.tt.SchoolTimeTable;
import ttgs.ds.tt.TimeSlot;
import ttgs.ds.tt.WeekDays;
import ttgs.rules.exception.TTGSExceptions.LectureBusyException;

/**
 * this class deals with the timetable generation of 
 * one group of students belonging to a particular stage of a given course 
 * 
 * @author Vincent
 */
public class TimeTableGenerator {
    /*
    data structures to manage the daily week days in the active week
    **/
    private String MONDAY = WeekDays.MON.toString();
    private String TUESDAY = WeekDays.TUE.toString();
    private String WEDNESDAY = WeekDays.WED.toString();
    private String THURSDAY = WeekDays.THU.toString();
    private String FRIDAY = WeekDays.FRI.toString();

    //needed data structures
    //the timetable being generated
    private GroupTimeTable timeTable;
    //the list of available timeslots within a particular day
    private ArrayList<TimeSlot> timeSlots;
    //the actiive units to be scheduled in a particular week
    private ArrayList<ActiveUnit> activeUnits;
    //the rooms where this units are to be taught in a particular school
    private ArrayList<Room> rooms;
    //the active days of the week when the units are to be taught
    private ArrayList<Day> days;
    //the lecturers teaching this units
    private ArrayList<Lecturer> lecturers;
    //the overall school timetable used for checking any coinciding lessons at a particular tuple of day and time
    private SchoolTimeTable schoolTimeTable;
    //the course being done by this students
    private Course course;
    //the group of the students doing the units
    private Group group;

    //changing data structures for storing temporary data 
    //variable total number of units
    private int number_of_units;
    //the overall list of units after being given ids
    private ArrayList<CurrentUnit> currentUnits;
    //the list of all the the alredy scheduled units
    private ArrayList<CurrentUnit> placed_currentUnits;
    //the list of units awaiting scheduling after first trial
    private ArrayList<CurrentUnit> clashes_of_the_day;
    //the list of units awaiting scheduling after last unit unit placed on the last day ofthe week
    private ArrayList<CurrentUnit> clashes_of_the_week;
    //the list of all the pairs of days and timeslots available throught out the week
    private ArrayList<DayTimeTuple> daytimes;
    //the list of all active slots in the week
    private ArrayList<DayTimeTuple> usedDayTimes;
    
    /**
     * this class will prepare the available time slots in the week
     * it will then id the units i.e provide IDs to the units for easy identification
     * schedule each unit on an free time slot 
     **/

    public TimeTableGenerator(ArrayList<TimeSlot> tslots, ArrayList<ActiveUnit> units, ArrayList<Room> rooms, ArrayList<Day> dtts, Group group) {
        //initializing data strucures
        this.timeSlots = tslots;
        this.activeUnits = units;
        this.group = group;
        this.days = dtts;
        this.rooms = rooms;
        this.timeTable = new GroupTimeTable(group, dtts);
        //changing ds
        this.clashes_of_the_day = newObjects();
        this.clashes_of_the_week = newObjects();
        this.lecturers = newObjects();
        this.currentUnits = newObjects();
        this.placed_currentUnits = newObjects();
        this.daytimes = newObjects();
        this.usedDayTimes = newObjects();
        ProvideIds();
        PopulateTuples();
    }

    public TimeTableGenerator(GenData data, Course c, SchoolTimeTable stt) {
        this(data.getTimeslots(), data.getUnits(), data.getRooms(), data.getDays(), data.getGroup());
        this.course = c;
        this.schoolTimeTable = stt;

    }

    public void generate() {
        for (int i = 0; i < this.getCurrentUnits().size(); i++) {
            CurrentUnit unit = this.getCurrentUnits().get(i);
            try {
                ScheduleUnit(unit);
            } catch (LectureBusyException ex) {
                Logger.getLogger(TimeTableGenerator.class.getName()).log(Level.SEVERE, null, ex);
                if (i > 0) {
                    i--;
                }
            }
        }
        this.getTimeTable().setTuples(getDaytimes());
    }

    private boolean ScheduleUnit(CurrentUnit unit) throws LectureBusyException {

        int dtindex = getRandIndex(getFreeDayTimeTuples().size());
        DayTimeTuple daytime = getFreeDayTimeTuples().get(dtindex);
        Room room = this.getRooms().get(getRandIndex(getRooms().size()));
        logStatus("Scheduling unit " + unit.getId_units().getCode()
                + " in room " + room.getName() + " day " + daytime.getDay() + " time " + daytime.getTime().getStart().getHours());
        if (isHavingUnitsforSlot(daytime)) {
            ArrayList<Lecture> lectures = getLecturesInSlot(daytime);
            for (Lecture lec : lectures) {
                if (lec.getLecturer().getId() == unit.getId_units().getLecturer().getId()) {
                    throw new LectureBusyException();
                } else if (lec.getRoom().equals(room)) {
                    Room room2 = this.getRooms().get(getRandIndex(getRooms().size()));
                    while (room.getID() != room2.getID()) {
                        room = room2;
                        room2 = this.getRooms().get(getRandIndex(getRooms().size()));
                    }
                }

            }
        }
        makeALecture(dtindex, unit, daytime, room);
        return true;

    }

    private boolean makeALecture(int dtindex, CurrentUnit unit, DayTimeTuple daytime, Room room) {
        Lecture lecture = new Lecture(room, unit.getId_units(), daytime.getTime(), daytime.getDay());
        lecture.setStudents(getGroup());
        lecture.setDescription(getCourse().getId() + " " + getGroup().getStage().getName());
        this.getTimeTable().addLecture(lecture);
        this.getDaytimes().get(dtindex).setActive();
        return true;
    }

    private boolean isHavingUnitsforSlot(DayTimeTuple tuple) {

        return !this.schoolTimeTable.getLecturesForSlot(tuple).isEmpty();

    }

    private ArrayList<Lecture> getLecturesInSlot(DayTimeTuple tuple) {
        ArrayList<Lecture> aclectures = newObjects();
        for (Lecture lecture : this.getSchoolTimeTable().getLecturesForSlot(tuple)) {
            aclectures.add(lecture);
        }
        return aclectures;
    }

    private ArrayList<DayTimeTuple> getFreeDayTimeTuples() {
        ArrayList<DayTimeTuple> freeSlots = newObjects();
        for (DayTimeTuple tuple : this.getDaytimes()) {
            if (!tuple.isActive()) {
                freeSlots.add(tuple);
            }
        }
        return freeSlots;
    }

    private void PopulateTuples() {
        for (Day dtt : this.getDtts()) {
            for (TimeSlot tt : this.getTimeSlots()) {
                DayTimeTuple dt = new DayTimeTuple(dtt.getDay(), tt);
                this.getDaytimes().add(dt);
            }
        }
    }

    private void ProvideIds() {
        logStatus("Re organizing units with ids...");
        ArrayList<ActiveUnit> myunits = newObjects();
        for (ActiveUnit u : getActiveUnits()) {
            if (hasoneLectureInAWeek(u)) {
                logStatus(u.getCode() + " should be scheduled " + u.getNum_of_lectures_in_a_week() + " times");
                myunits.add(u);
            } else if (hasmorelecturesInAWeek(u)) {
                logStatus(u.getCode() + " should be scheduled " + u.getNum_of_lectures_in_a_week() + " times");
                for (int i = 0; i < u.getNum_of_lectures_in_a_week(); i++) {
                    myunits.add(u);
                }
            }
        }
        logStatus("Units found ...");
        int i = 1;
        for (ActiveUnit u : myunits) {
            CurrentUnit du = new CurrentUnit(i, u);
            logStatus(" " + i + " " + u.getCode() + " " + u.getName() + " by " + u.getLecturer().toString());
            getCurrentUnits().add(du);
            i++;
        }
        this.setNumber_of_units(i);
    }

    private int getRandIndex(int size) {
        if (size <= 1) {
            return 0;
        } else {
            return new Random().nextInt(size - 1);
        }

    }

    private boolean DayClashHasNoUnit() {
        return this.getClashes_of_the_day().isEmpty();
    }

    private boolean isAllUnitsScheduled() {
        return this.getPlaced_currentUnits().size() == this.getNumber_of_units();
    }

    private boolean isAllDayTimesUsed() {
        return this.getDaytimes().isEmpty();
    }

    private boolean hasoneLectureInAWeek(ActiveUnit unit) {
        return unit.getNum_of_lectures_in_a_week() == 1;
    }

    private boolean hasmorelecturesInAWeek(ActiveUnit unit) {
        return unit.getNum_of_lectures_in_a_week() > 1;
    }

    private void logStatus(String status) {
        MainLogger.logger("TimeTableGenerator > " + status);
    }

    /**
     * @param currentUnits the currentUnits to set
     */
    public void setCurrentUnits(ArrayList<CurrentUnit> currentUnits) {
        this.currentUnits = currentUnits;
    }

    /**
     * @return the clashes_of_the_day
     */
    public ArrayList<CurrentUnit> getClashes_of_the_day() {
        return clashes_of_the_day;
    }

    /**
     * @param clashes_of_the_day the clashes_of_the_day to set
     */
    public void setClashes_of_the_day(ArrayList<CurrentUnit> clashes_of_the_day) {
        this.clashes_of_the_day = clashes_of_the_day;
    }

    /**
     * @param clashes_of_the_week the clashes_of_the_week to set
     */
    public void setClashes_of_the_week(ArrayList<CurrentUnit> clashes_of_the_week) {
        this.clashes_of_the_week = clashes_of_the_week;
    }

    public GroupTimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(GroupTimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public ArrayList<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(ArrayList<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public ArrayList<ActiveUnit> getActiveUnits() {
        return activeUnits;
    }

    public void setActiveUnits(ArrayList<ActiveUnit> activeUnits) {
        this.activeUnits = activeUnits;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Day> getDtts() {
        return getDays();
    }

    public void setDtts(ArrayList<Day> dtts) {
        this.setDays(dtts);
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public SchoolTimeTable getSchoolTimeTable() {
        return schoolTimeTable;
    }

    public void setSchoolTimeTable(SchoolTimeTable schoolTimeTable) {
        this.schoolTimeTable = schoolTimeTable;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public ArrayList<CurrentUnit> getPlaced_currentUnits() {
        return placed_currentUnits;
    }

    public void setPlaced_currentUnits(ArrayList<CurrentUnit> placed_currentUnits) {
        this.placed_currentUnits = placed_currentUnits;
    }

    public ArrayList<CurrentUnit> getCurrentUnits() {
        return currentUnits;
    }

    public ArrayList<CurrentUnit> getClashes_of_the_week() {
        return clashes_of_the_week;
    }

    public final ArrayList newObjects() {
        return new ArrayList();
    }

    /**
     * @return the MONDAY
     */
    public String getMONDAY() {
        return MONDAY;
    }

    /**
     * @param MONDAY the MONDAY to set
     */
    public void setMONDAY(String MONDAY) {
        this.MONDAY = MONDAY;
    }

    /**
     * @return the TUESDAY
     */
    public String getTUESDAY() {
        return TUESDAY;
    }

    /**
     * @param TUESDAY the TUESDAY to set
     */
    public void setTUESDAY(String TUESDAY) {
        this.TUESDAY = TUESDAY;
    }

    /**
     * @return the WEDNESDAY
     */
    public String getWEDNESDAY() {
        return WEDNESDAY;
    }

    /**
     * @param WEDNESDAY the WEDNESDAY to set
     */
    public void setWEDNESDAY(String WEDNESDAY) {
        this.WEDNESDAY = WEDNESDAY;
    }

    /**
     * @return the THURSDAY
     */
    public String getTHURSDAY() {
        return THURSDAY;
    }

    /**
     * @param THURSDAY the THURSDAY to set
     */
    public void setTHURSDAY(String THURSDAY) {
        this.THURSDAY = THURSDAY;
    }

    /**
     * @return the FRIDAY
     */
    public String getFRIDAY() {
        return FRIDAY;
    }

    /**
     * @param FRIDAY the FRIDAY to set
     */
    public void setFRIDAY(String FRIDAY) {
        this.FRIDAY = FRIDAY;
    }

    /**
     * @return the daytimes
     */
    public ArrayList<DayTimeTuple> getDaytimes() {
        return daytimes;
    }

    /**
     * @param daytimes the daytimes to set
     */
    public void setDaytimes(ArrayList<DayTimeTuple> daytimes) {
        this.daytimes = daytimes;
    }

    /**
     * @return the usedDayTimes
     */
    public ArrayList<DayTimeTuple> getUsedDayTimes() {
        return usedDayTimes;
    }

    /**
     * @param usedDayTimes the usedDayTimes to set
     */
    public void setUsedDayTimes(ArrayList<DayTimeTuple> usedDayTimes) {
        this.usedDayTimes = usedDayTimes;
    }

    public int getNumber_of_units() {
        return number_of_units;
    }

    public void setNumber_of_units(int number_of_units) {
        this.number_of_units = number_of_units;
    }

    /**
     * @return the days
     */
    public ArrayList<Day> getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }

}

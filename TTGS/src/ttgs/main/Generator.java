package ttgs.main;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import ttgs.algorithms.Engine;
import ttgs.api.database.TTDB;
import ttgs.ds.ActiveUnit;
import ttgs.ds.CommonUnit;
import ttgs.ds.Course;
import ttgs.ds.Faculty;
import ttgs.ds.Group;
import ttgs.ds.JointStudents;
import ttgs.ds.Lecturer;
import ttgs.ds.Room;
import ttgs.ds.School;
import ttgs.ds.Stage;
import ttgs.ds.Unit;
import ttgs.ds.tt.Day;
import ttgs.ds.tt.GroupTimeTable;
import ttgs.ds.tt.GroupedCourses;
import ttgs.ds.tt.GroupedDepartments;
import ttgs.ds.tt.GroupedRooms;
import ttgs.ds.tt.GroupedStudents;
import ttgs.ds.tt.GroupedUnits;
import ttgs.ds.tt.Lecture;
import ttgs.ds.tt.SchoolData;
import ttgs.ds.tt.SchoolTimeTable;
import ttgs.ds.tt.TTGSDate;
import ttgs.ds.tt.TimeSlot;
import ttgs.ds.tt.UniversityTimeTable;
import ttgs.ds.tt.WeekDays;
import ttgs.rules.Defaults;
import ttgs.rules.Defaults.InstitutionDefaults;
import ttgs.rules.Defaults.SortDefaults;

/**
 *
 * @author Vin
 */
public final class Generator {

    private static int ACSENDING = SortDefaults.ACSENDING;
    private static int DESCENDING = SortDefaults.DESCENDING;
    /**
     * @return the ACSENDING
     */
    public static int getACSENDING() {
        return ACSENDING;
    }
    /**
     * @param aACSENDING the ACSENDING to set
     */
    public static void setACSENDING(int aACSENDING) {
        ACSENDING = aACSENDING;
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

    //the university name
    private String uniName;
    //all the schools in the university
    private ArrayList<School> schools;
    // all the student timetable
    private ArrayList<GroupTimeTable> timetables;

    //all departments
    private ArrayList<Faculty> departments;
    //all the romms in the university
    private ArrayList<Room> rooms;
    //all the lecturers in the university
    private ArrayList<Lecturer> lecturers;
    //all the units
    private ArrayList<Unit> units;
    //all the stages in the university
    private ArrayList<Stage> stages;
    //all the semester units in the university
    private ArrayList<ActiveUnit> semUnits;
    //all the common units in the university
    private ArrayList<CommonUnit> commUnits;
    //all the courses in the university
    private ArrayList<Course> courses;
    //all the students in the university
    private ArrayList<Group> students;
    //all the students taking common unit
    private ArrayList<JointStudents> jointStudents;
    //tt datastructures
    //the main timetable
    private UniversityTimeTable mtt;
    //all the school timetable
    private ArrayList<SchoolTimeTable> stts;
    //all the dayTT
    private ArrayList<Day> dtts;
    //all the lectures
    private ArrayList<Lecture> lectures;
    //all the day timeslots
    private ArrayList<TimeSlot> timeslots;

    //grouped datastructures
    private GroupedDepartments gDepartments;
    private GroupedRooms grooms;
    private GroupedCourses gcourses;
    private GroupedStudents gstudents;
    private GroupedUnits gunits;
    private SchoolData alldata;
    //getter setter methods
    private TTDB db;

    public Generator() {
        try {
            Defaults.setFromFile();
        } catch (Exception ex) {

        }
        timetables = newObjects();
        this.uniName = InstitutionDefaults.uniName;
        this.schools = newObjects();
        this.rooms = newObjects();
        this.lecturers = newObjects();
        this.semUnits = newObjects();
        this.units = newObjects();
        this.commUnits = newObjects();
        this.courses = newObjects();
        this.students = newObjects();
        this.jointStudents = newObjects();
        this.stages = newObjects();
        //tt variable
        this.stts = newObjects();
        this.mtt = new UniversityTimeTable(getStts());
        this.dtts = newObjects();
        dtts.add(0, new Day(WeekDays.MON.toString()));
        dtts.add(1, new Day(WeekDays.TUE.toString()));
        dtts.add(2, new Day(WeekDays.WED.toString()));
        dtts.add(3, new Day(WeekDays.THU.toString()));
        dtts.add(4, new Day(WeekDays.FRI.toString()));
        this.lectures = newObjects();
        this.timeslots = newObjects();
        //grouped data
        this.gDepartments = new GroupedDepartments();
        this.gcourses = new GroupedCourses();
        this.grooms = new GroupedRooms();
        this.gstudents = new GroupedStudents();
        this.gunits = new GroupedUnits();
        //school data
        this.alldata = new SchoolData();
        //database object
        db = new TTDB().getTTDB();

    }

    public void run() {
        //loading this data from database
        loadData();
        //generate timeslots
        generatingTimeSlots();
        //group data
        groupData();
        //generating ttusing laoded data
        generateTT();
        //saving the data to tt database
        saveTTData();
    }

    private void loadData() {
        printStatus("Step 1 : Loading data \n\n");
        setSchools(getDb().getSchools());
        setDepartments(getDb().getDepartments());
        setCommUnits(getDb().getComUnits());
        setCourses(getDb().getCourses());
        setLecturers(getDb().getLecturers());
        setRooms(getDb().getRooms());
        setSemUnits(getDb().getSemUnits());
        setStudents(getDb().getStudents());
        setUnits(getDb().getUnits());
        setStages(getDb().getStages());
    }

    private void generatingTimeSlots() {

        printStatus("Step 2 : Generating TimeTables \n\n");
        heading();
        heading();
        printStatus("Generating TimeSlots \n");
        setTimeslots(generateTimeSlots());
        printStatus("TimeSlots generated " + getTimeslots().size() + "\n");
    }

    private void groupData() {
        //repeat grouping for all the schools
        printStatus("Step 3 : Grouping data for all schools\n\n");
        heading();
        heading();
        for (int l = 0; l < getSchools().size(); l++) {
            School temp = this.getSchools().get(l);
            int i = temp.getId();
            ArrayList<Group> groups = new ArrayList();
            printStatus("Grouping info for school " + temp.getName());
            heading();
            heading();

            printStatus("Grouping departments for " + temp.getName());
            temp.addDepartments(getDepartmentsforSchool(temp));

            printStatus("Grouping Rooms for " + temp.getName());
            temp.setRooms(getRoomsforSchool(temp));

            for (Faculty dept : temp.getDepartments()) {
                printStatus("Grouping courses for department " + dept.getName());
                temp.getDepartment(dept.getId()).setCourses(getCoursesforDepartment(dept));
            }

            for (Faculty dpt : temp.getDepartments()) {
                if (dpt.hasCourses()) {
                    for (Course course : dpt.getCourses()) {
                        printStatus("Grouping students for course " + course.getName());
                        temp.addStudentGroup(course, getCoursegroups(course));

                        printStatus("Grouping Active units for course " + course.getName());
                        temp.getDepartment(dpt.getId()).getCourse(course.getId()).setActiveUnits(getUnitdforCourse(course));

                    }
                }
            }
            getAlldata().addSchoolData(i, temp);
        }

    }

    private ArrayList<Faculty> getDepartmentsforSchool(School school) {
        ArrayList<Faculty> d = new ArrayList();
        //grouping departments

        heading();
        for (Faculty dept : getDepartments()) {
            if (belongsTo(dept, school)) {
                d.add(dept);

                printStatus("Grouped Department " + dept.getName());
            }
        }
        return d;
    }

    private ArrayList<Group> getCoursegroups(Course c) {
        ArrayList<Group> groups = new ArrayList();
        for (Group grp : this.getStudents()) {
            if (grp.getCourse().getId() == c.getId()) {
                groups.add(grp);
            }
        }
        return groups;
    }

    private ArrayList<Room> getRoomsforSchool(School temp) {
        ArrayList<Room> r = new ArrayList();
        //grouping rooms

        heading();
        for (Room rm : getRooms()) {
            if (belongsTo(rm, temp)) {
                r.add(rm);
                printStatus("Grouped Room " + rm.getName());
            }
        }
        return r;
    }

    private ArrayList<ActiveUnit> getUnitdforCourse(Course c) {
        ArrayList<ActiveUnit> sunits = new ArrayList();
        for (ActiveUnit u : getSemUnits()) {
            if (belongsTo(u, c)) {
                boolean add = sunits.add(u);
                printStatus("Unit " + u.getCode());
            }
        }
        return sunits;
    }

    private ArrayList<Course> getCoursesforDepartment(Faculty dept) {
        ArrayList<Course> coses = new ArrayList();
        for (Course c : getCourses()) {
            if (c.getDept_id() == dept.getId()) {
                coses.add(c);
            }
        }
        return coses;
    }

    private boolean belongsTo(Faculty d, School s) {
        return Engine.DepartmentBelongsToSchool(d, s);
    }

    private boolean belongsTo(Course c, Faculty d) {
        return Engine.CourseBelongsToDepartment(c, d);
    }

    private boolean belongsTo(Room r, School s) {
        return Engine.RoomBelongsToSchool(r, s);
    }

    private boolean belongsTo(ActiveUnit u, Course c) {
        return Engine.UnitBelongsToCourse(u, c);
    }

    private boolean StudentsDoCourse(Group s, Course c) {
        return Engine.StudentsDoCourse(s, c);
    }

    private void generateTT() {
        SchoolData data = this.getAlldata();
        printStatus("Sorting and preparing school data");
        heading();
        data.sortSchoolsWithID();
        printStatus("Sorted schools\n\n");
        printSchools(data.getSchools());
        Date now = new Date();
        printStatus("Generation beginning in " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds() + "");
        heading();
        heading();
        for (School school : data.getSchools()) {
            SchoolTimeTable stt = new SchoolTimeTable(school);
            if (school.hasDepartments() && school.hasRooms()) {
                printStatus("Analyzing school : " + school.getName());
                for (Faculty dept : school.getDepartments()) {
                    if (dept.hasCourses()) {
                        printStatus("Analyzing department : " + dept.getName());
                        for (Course c : dept.getCourses()) {
                            if (c.hasUnits()) {
                                printStatus("Analyzing course : " + c.getName());
                                for (Stage st : getStages()) {
                                    if (c.hasUnitsforStage(st) && school.hasGroup(c, st)) {
                                        printStatus("Analizing stage : " + st.getName());
                                        Group sgrp = school.getGroup(c, st);
                                        GenData gdata = prepareGenData(school, c, sgrp);
                                        TimeTableGenerator tgen = new TimeTableGenerator(gdata, c, stt);
                                        tgen.setSchoolTimeTable(stt);
                                        tgen.generate();
                                        stt.addTimeTable(tgen.getTimeTable());
                                    } else {
                                        printStatus("No Students and units for stage " + st.getName());
                                    }
                                }
                            } else {
                                printStatus("Course " + c.getName() + " doesnt have any units");
                            }
                        }
                    } else {
                        printStatus("Department " + dept.getName() + " doesnt have any courses registered");
                    }
                }
                getMainTT().addSchoolTT(stt);
            } else {
                printStatus("School " + school.getName() + " doesnt have any departments and or rooms");
            }
        }

        Date after = new Date();

        printStatus("Generation complete in " + (after.getHours() - now.getHours())
                + ":" + (after.getMinutes() - now.getMinutes())
                + ":" + (after.getSeconds() - now.getSeconds()) + "");

    }

    private GenData prepareGenData(School s, Course c, Group group) {
        printStatus("Preparing data for " + c.getName() + " group " + group.getNumber() + " students");
        ArrayList<ActiveUnit> someunits = c.getUnitsforStage(group.getStage());
        ArrayList<Room> somerooms = s.getRooms();
        return new GenData(someunits, somerooms, getTimeslots(), group, getDtts());
    }

    private void printSchools(ArrayList<School> schools) {
        schools.stream().forEach((s) -> {
            printStatus(s.getId() + " " + s.getName());
        });
    }

    private ArrayList<TimeSlot> generateTimeSlots() {
        printStatus("TimeSlot generation");
        TTGSDate startDay = Engine.getDatefromLocalTime(Defaults.TimeDefaults.startTimeofDay);
        TTGSDate endDay = Engine.getDatefromLocalTime(Defaults.TimeDefaults.endTimeofDay);
        Duration defLectureTime = Defaults.TimeDefaults.getLectureDuration();
        ArrayList<TimeSlot> slots = Engine.calcTimeSlots(startDay, endDay, defLectureTime);
        printStatus("Time slot generation complete");
        return slots;

    }

    private void saveTTData() {
        printStatus("Step 4 : Saving events all schools\n\n");
        getDb().saveTTData(getMainTT());

    }

    private void printStatus(String state) {
        MainLogger.logger("Generator > " + state);
    }

    private void heading() {
        printStatus("-------------------------");
    }

    public GroupedDepartments getgDepartments() {
        return gDepartments;
    }

    public void setgDepartments(GroupedDepartments gDepartments) {
        this.gDepartments = gDepartments;
    }

    public SchoolData getAlldata() {
        return alldata;
    }

    //school data object
    public void setAlldata(SchoolData alldata) {
        this.alldata = alldata;
    }

    public ArrayList<GroupTimeTable> getTimetables() {
        return timetables;
    }

    public void setTimetables(ArrayList<GroupTimeTable> timetables) {
        this.timetables = timetables;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public ArrayList<School> getSchools() {
        return schools;
    }

    public void setSchools(ArrayList<School> schools) {
        this.schools = schools;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Faculty> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Faculty> departments) {
        this.departments = departments;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public ArrayList<ActiveUnit> getSemUnits() {
        return semUnits;
    }

    public void setSemUnits(ArrayList<ActiveUnit> semUnits) {
        this.semUnits = semUnits;
    }

    public ArrayList<CommonUnit> getCommUnits() {
        return commUnits;
    }

    public void setCommUnits(ArrayList<CommonUnit> commUnits) {
        this.commUnits = commUnits;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public ArrayList<Group> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Group> students) {
        this.students = students;
    }

    public ArrayList<JointStudents> getJointStudents() {
        return jointStudents;
    }

    public void setJointStudents(ArrayList<JointStudents> jointStudents) {
        this.jointStudents = jointStudents;
    }

    public UniversityTimeTable getMtt() {
        return mtt;
    }

    public void setMtt(UniversityTimeTable mtt) {
        this.mtt = mtt;
    }

    public UniversityTimeTable getMainTT() {
        return getMtt();
    }

    public void setMainTT(UniversityTimeTable mtt) {
        this.setMtt(mtt);
    }

    public ArrayList<SchoolTimeTable> getStts() {
        return stts;
    }

    public void setStts(ArrayList<SchoolTimeTable> stts) {
        this.stts = stts;
    }

    public ArrayList<Day> getDtts() {
        return dtts;
    }

    public void setDtts(ArrayList<Day> dtts) {
        this.dtts = dtts;
    }

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }

    public GroupedDepartments getGDepartments() {
        return getgDepartments();
    }

    public void setGDepartments(GroupedDepartments gDepartments) {
        this.setgDepartments(gDepartments);
    }

    public GroupedRooms getGrooms() {
        return grooms;
    }

    public void setGrooms(GroupedRooms grooms) {
        this.grooms = grooms;
    }

    public GroupedCourses getGcourses() {
        return gcourses;
    }

    public void setGcourses(GroupedCourses gcourses) {
        this.gcourses = gcourses;
    }

    public GroupedStudents getGstudents() {
        return gstudents;
    }

    public void setGstudents(GroupedStudents gstudents) {
        this.gstudents = gstudents;
    }

    public GroupedUnits getGunits() {
        return gunits;
    }

    public void setGunits(GroupedUnits gunits) {
        this.gunits = gunits;
    }

    public TTDB getDb() {
        return db;
    }

    public ArrayList<TimeSlot> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(ArrayList<TimeSlot> timeslots) {
        this.timeslots = timeslots;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public void setStages(ArrayList<Stage> stages) {
        this.stages = stages;
    }


    /**
     * @param db the db to set
     */
    public void setDb(TTDB db) {
        this.db = db;
    }

    private ArrayList newObjects() {
        return new ArrayList();
    }
}

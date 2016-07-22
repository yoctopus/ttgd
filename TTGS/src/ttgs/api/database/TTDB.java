package ttgs.api.database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ttgs.ds.CommonUnit;
import ttgs.ds.Course;
import ttgs.ds.Faculty;
import ttgs.ds.Lecturer;
import ttgs.ds.Room;
import ttgs.ds.School;
import ttgs.ds.ActiveUnit;
import ttgs.ds.Stage;
import ttgs.ds.Group;
import ttgs.ds.Unit;
import ttgs.ds.tt.Lecture;
import ttgs.ds.tt.UniversityTimeTable;
import ttgs.ds.tt.SchoolTimeTable;
import ttgs.main.MainLogger;
import ttgs.api.database.models.Courses;
import ttgs.api.database.models.Departments;
import ttgs.api.database.models.Lecturers;
import ttgs.api.database.models.Rooms;
import ttgs.api.database.models.Schools;
import ttgs.api.database.models.Semesterunits;
import ttgs.api.database.models.Stages;
import ttgs.api.database.models.Students;
import ttgs.api.database.models.Ttevents;
import ttgs.api.database.models.Units;

/**
 *
 * @author Vin
 */
public class TTDB {

    private Lecturers lecTable;
    private Courses courseTable;
    private Rooms rmTable;
    private Semesterunits semTable;
    private Units unitTable;
    private Departments deptTable;
    private ArrayList<Room> rooms;
    private ArrayList<Stage> stages;
    private ArrayList<Course> courses;
    private ArrayList<Group> students;
    private ArrayList<Unit> units;
    private ArrayList<ActiveUnit> semUnits;
    private ArrayList<Lecturer> lecturers;
    private ArrayList<CommonUnit> comUnits;
    private ArrayList<School> schools;
    private ArrayList<Faculty> departments;
    private static Session session;

    /**
     *
     */
    protected static void connect() {
        setSession(InitDB.getSession());
    }

    /**
     * @param aSession the session to set
     */
    public static void setSession(Session aSession) {
        session = aSession;
    }

    public TTDB() {
        //initialiize the ttdb data
        Init();
        //connecting to the external database
        connect();
        //loading data from database
        loadSchools();
        loadRooms();
        loadDepartments();
        loadCourses();
        loadUnits();
        loadSemUnits();
        loadLecturers();
        loadStudents();
        loadStages();

        disconnect();

    }

    private void Init() {
        setComUnits((ArrayList<CommonUnit>) newObjects());
        setCourses((ArrayList<Course>) newObjects());
        setLecturers((ArrayList<Lecturer>) newObjects());
        setRooms((ArrayList<Room>) newObjects());
        setSchools((ArrayList<School>) newObjects());
        setSemUnits((ArrayList<ActiveUnit>) newObjects());
        setStudents((ArrayList<Group>) newObjects());
        setUnits((ArrayList<Unit>) newObjects());
        setStages((ArrayList<Stage>) newObjects());
    }

    private void loadRooms() {
        logit("\nLoading rooms");
        Transaction tx = getSession().beginTransaction();
        ArrayList<Rooms> local = newObjects();
        List rms = getSession().createQuery("from Rooms").list();
        for (Iterator iter = rms.iterator(); iter.hasNext();) {
            local.add((Rooms) iter.next());
        }
        ArrayList<Room> roomss = newObjects();
        local.stream().map((rm) -> new Room(rm.getName(), rm.getSize(), rm.getId(),
                rm.getType(), rm.getSchools().getSid())).map((r) -> {
                    roomss.add(r);
                    return r;
                }).forEach((r) -> {
                    logit(r.getName());
                });
        setRooms(roomss);
        tx.commit();
        logit("Found " + roomss.size() + " rooms");
    }

    private void loadStages() {
        logit("\nLoading stages");
        Transaction tx = getSession().beginTransaction();
        ArrayList<Stages> local = newObjects();
        List rms = getSession().createQuery("from Stages").list();
        for (Iterator iter = rms.iterator(); iter.hasNext();) {
            local.add((Stages) iter.next());
        }
        ArrayList<Stage> stagess = newObjects();
        local.stream().map((rm) -> new Stage(rm.getId(), rm.getName())).map((r) -> {
            stagess.add(r);
            return r;
        }).forEach((r) -> {
            logit(r.getName());
        });
        setStages(stagess);
        tx.commit();
        logit("Found " + stagess.size() + " stages");
    }

    private void loadCourses() {
        logit("\nLoading courses");
        Transaction tx = getSession().beginTransaction();
        ArrayList<Courses> local = newObjects();
        List rms = getSession().createQuery("from Courses").list();
        for (Iterator iter = rms.iterator(); iter.hasNext();) {
            local.add((Courses) iter.next());
        }
        ArrayList<Course> coursess = newObjects();
        for (Courses cs : local) {
            ArrayList<Unit> cunits = newObjects();

            for (Iterator it = cs.getUnitses().iterator(); it.hasNext();) {
                Units unitsss = (Units) it.next();
                cunits.add(new Unit(unitsss.getCode(), unitsss.getName(), unitsss.getCourses().getId()));
            }

            Course c = new Course(cs.getName(), cs.getId(), cs.getDepartments().getId(), cunits);            
            coursess.add(c);
            logit(c.getName());
        }
        setCourses(coursess);
        tx.commit();
        logit("Found " + coursess.size() + " courses");
    }

    private void loadLecturers() {
        logit("\nLoading lecturers");
        Transaction tx = getSession().beginTransaction();
        ArrayList<Lecturers> local = newObjects();
        List rms = getSession().createQuery("from Lecturers").list();
        for (Iterator iter = rms.iterator(); iter.hasNext();) {
            local.add((Lecturers) iter.next());
        }
        ArrayList<Lecturer> lecturerss = newObjects();
        for (Lecturers lec : local) {
            Lecturer l = new Lecturer(lec.getName(), lec.getId());
            
            lecturerss.add(l);
            logit(l.getInitials() + l.getName());
        }
        setLecturers(lecturerss);
        tx.commit();
        logit("\nFound " + lecturerss.size() + " lecturers");
    }

    private void loadSchools() {
        logit("\nLoading schools");
        Transaction tx = getSession().beginTransaction();
        ArrayList<Schools> local = newObjects();
        List rms = getSession().createQuery("from Schools").list();
        for (Iterator iter = rms.iterator(); iter.hasNext();) {
            local.add((Schools) iter.next());
        }
        ArrayList<School> schoolss = newObjects();
        for (Schools s : local) {
            School sc = new School(s.getName(), s.getSid());
            schoolss.add(sc);
            logit(sc.toString());
        }
        setSchools(schoolss);
        tx.commit();
        logit("Found " + schoolss.size() + " schools");
    }

    private void loadStudents() {
        logit("\nLoading student groups");
        Transaction tx = getSession().beginTransaction();
        ArrayList<Students> local = newObjects();
        List rms = getSession().createQuery("from Students").list();
        for (Iterator iter = rms.iterator(); iter.hasNext();) {
            local.add((Students) iter.next());
        }
        ArrayList<Group> studentss = newObjects();
        for (Students s : local) {
            ArrayList<Unit> cunits = newObjects();
            for (Iterator it = s.getCourses().getUnitses().iterator(); it.hasNext();) {
                Units unitsss = (Units) it.next();
                cunits.add(new Unit(unitsss.getCode(), unitsss.getName(), unitsss.getCourses().getId()));
            }
            Course c = new Course(s.getCourses().getName(), s.getCourses().getDepartments().getId(), s.getCourses().getId(), cunits);
            Stage stage = new Stage(s.getStages().getId(), s.getStages().getName());
            Group sgrp = new Group(c, stage, s.getNumber());
            sgrp.setId(s.getId());
            studentss.add(sgrp);
            logit(sgrp.getCourse().getName());
        }
        setStudents(studentss);
        tx.commit();
        logit("Found " + studentss.size() + " Student groups");
    }

    private void loadSemUnits() {
        logit("\nLoading active units");
        Transaction tx = getSession().beginTransaction();
        ArrayList<Semesterunits> local = newObjects();
        List rms = getSession().createQuery("from Semesterunits").list();
        for (Iterator iter = rms.iterator(); iter.hasNext();) {
            local.add((Semesterunits) iter.next());
        }
        ArrayList<ActiveUnit> semunits = newObjects();
        for (Semesterunits su : local) {
            Unit un = new Unit(su.getUnits().getCode(), su.getUnits().getName(), su.getUnits().getCourses().getId());
            Stage st = new Stage(su.getStages().getId(), su.getStages().getName());
            Lecturers lec = su.getLecturers();
            Lecturer lecc = new Lecturer(lec.getName(), lec.getId());
            ActiveUnit unit = new ActiveUnit(un, lecc, st, su.getLecturesInAWeek());
            unit.setId(su.getId());
            semunits.add(unit);
            logit(unit.getCode() + " " + unit.getName() + " by " + unit.getLecturer().getName()
                    + " in semester " + unit.getStage().getName() + " ");
        }
        setSemUnits(semunits);
        tx.commit();
        logit("Found " + semunits.size() + " active units");
    }

    private void loadUnits() {
        logit("\nLoading all units");
        Transaction tx = getSession().beginTransaction();
        ArrayList<Units> local = newObjects();
        List rms = getSession().createQuery("from Units").list();
        for (Iterator iter = rms.iterator(); iter.hasNext();) {
            local.add((Units) iter.next());
        }
        ArrayList<Unit> unitss = newObjects();
        for (Units u : local) {
            Unit unit = new Unit(u.getCode(), u.getName(), u.getCourses().getId());
            unit.setCourseId(u.getCourses().getId());
            unitss.add(unit);
            logit(unit.getName());
        }
        setUnits(unitss);
        tx.commit();
        logit("Found " + unitss.size() + " units");
    }

    private void loadDepartments() {
        logit("\nLoading departments");
        Transaction tx = getSession().beginTransaction();
        ArrayList<Departments> local = newObjects();
        List rms = getSession().createQuery("from Departments").list();
        for (Iterator iter = rms.iterator(); iter.hasNext();) {
            local.add((Departments) iter.next());
        }
        ArrayList<Faculty> departmentss = newObjects();
        for (Departments d : local) {
            ArrayList<Course> css = newObjects();
            for (Iterator iter = d.getCourseses().iterator(); iter.hasNext();) {
                Courses somec = (Courses) iter.next();
                ArrayList<Unit> cunits = newObjects();
                for (Iterator uiter = somec.getUnitses().iterator(); uiter.hasNext();) {
                    Units unts = (Units) uiter.next();
                    units.add(new Unit(unts.getCode(), unts.getName(), unts.getCourses().getId()));
                }
                Course somecc = new Course(somec.getName(), somec.getDepartments().getId(), somec.getId(), cunits);                
                css.add(somecc);
            }
            Faculty dept = new Faculty(d.getName(), d.getId(), css, d.getSchools().getSid());
            departmentss.add(dept);
            logit(dept.getName());
        }
        setDepartments(departmentss);
        tx.commit();
        logit("Found " + departmentss.size() + " departments");
    }

    /**
     * the function that saves the timetables to the vents table
     *
     * @param mtt the timetable to be saved
     */
    public void saveTTData(UniversityTimeTable mtt) {
        logit("\nSaving events ");
        connect();

        mtt.getStt().stream().forEach((stt) -> {
            saveSchoolTT(stt);
        });

        disconnect();
        logit("Save successful");
    }

    private void saveSchoolTT(SchoolTimeTable stt) {
        stt.getTts().stream().forEach((tt) -> {
            tt.getTimetable().stream().forEach((t) -> {
                t.getLectures().stream().forEach((l) -> {
                    saveLecture(l);
                });
            });
        });
    }

    private void saveLecture(Lecture lecture) {
        Transaction tx = getSession().beginTransaction();
        Ttevents event = new Ttevents();
        Lecturers lec = new Lecturers();
        lec.setId(lecture.getSemunit().getLecturer().getID());
        event.setLecturers(lec);
        Students studentss = new Students();
        studentss.setId(lecture.getStudents().getId());
        event.setStudents(studentss);
        Rooms rm = new Rooms();
        rm.setId(lecture.getRoom().getID());
        event.setRooms(rm);
        Semesterunits unit = new Semesterunits();
        unit.setId(lecture.getSemunit().getId());
        event.setSemesterunits(unit);
        event.setTimestart(lecture.getStartime());
        event.setTimeend(lecture.getEndtime());
        event.setDay(lecture.getDay());
        event.setDescription(lecture.getDescription());
        logit("Saving event for " + lecture.getSemunit().getCode() + "on " + lecture.getDay());
        //now saving the event;
        getSession().save(event);
        tx.commit();
        logit("\nlecture " + event.getDescription() + " has been saved");
    }

    private void disconnect() {
        getSession().disconnect();
    }

    public TTDB getTTDB() {
        return this;
    }

    private void logit(String s) {
        MainLogger.logger("TTDB >" + s);
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public void setStages(ArrayList<Stage> stages) {
        this.stages = stages;
    }

    /**
     *
     * @return
     */
    public Departments getDeptTable() {
        return deptTable;
    }

    public void setDeptTable(Departments deptTable) {
        this.deptTable = deptTable;
    }

    public Lecturers getLecTable() {
        return lecTable;
    }

    public void setLecTable(Lecturers lecTable) {
        this.lecTable = lecTable;
    }

    public Courses getCourseTable() {
        return courseTable;
    }

    public void setCourseTable(Courses courseTable) {
        this.courseTable = courseTable;
    }

    public Rooms getRmTable() {
        return rmTable;
    }

    public void setRmTable(Rooms rmTable) {
        this.rmTable = rmTable;
    }

    public Semesterunits getSemTable() {
        return semTable;
    }

    public void setSemTable(Semesterunits semTable) {
        this.semTable = semTable;
    }

    public Units getUnitTable() {
        return unitTable;
    }

    public void setUnitTable(Units unitTable) {
        this.unitTable = unitTable;
    }

    public Session getSession() {
        return session;
    }

    public ArrayList<Faculty> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Faculty> departments) {
        this.departments = departments;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
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

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public ArrayList<ActiveUnit> getSemUnits() {
        return semUnits;
    }

    public void setSemUnits(ArrayList<ActiveUnit> semUnits) {
        this.semUnits = semUnits;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public ArrayList<CommonUnit> getComUnits() {
        return comUnits;
    }

    public void setComUnits(ArrayList<CommonUnit> comUnits) {
        this.comUnits = comUnits;
    }

    public ArrayList<School> getSchools() {
        return schools;
    }

    public void setSchools(ArrayList<School> schools) {
        this.schools = schools;
    }
    
    public ArrayList newObjects() {
        return new ArrayList();
    }
}

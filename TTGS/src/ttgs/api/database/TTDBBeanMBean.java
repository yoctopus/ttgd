/*
 * TTDBBeanMBean.java
 *
 * Created on May 19, 2016, 10:41 PM
 */
package ttgs.api.database;

import java.util.ArrayList;
import org.hibernate.Session;
import ttgs.api.database.models.Courses;
import ttgs.api.database.models.Departments;
import ttgs.api.database.models.Lecturers;
import ttgs.api.database.models.Rooms;
import ttgs.api.database.models.Semesterunits;
import ttgs.api.database.models.Units;
import ttgs.ds.tt.UniversityTimeTable;

/**
 * Interface TTDBBeanMBean
 *
 * @author Vincent
 */
public interface TTDBBeanMBean {

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getComUnits();

    /**
     * Set Attribute exposed for management
     */
    public void setComUnits(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public Courses getCourseTable();

    /**
     * Set Attribute exposed for management
     */
    public void setCourseTable(Courses value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getCourses();

    /**
     * Set Attribute exposed for management
     */
    public void setCourses(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getDepartments();

    /**
     * Set Attribute exposed for management
     */
    public void setDepartments(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public Departments getDeptTable();

    /**
     * Set Attribute exposed for management
     */
    public void setDeptTable(Departments value);

    /**
     * Get Attribute exposed for management
     */
    public Lecturers getLecTable();

    /**
     * Set Attribute exposed for management
     */
    public void setLecTable(Lecturers value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getLecturers();

    /**
     * Set Attribute exposed for management
     */
    public void setLecturers(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public Rooms getRmTable();

    /**
     * Set Attribute exposed for management
     */
    public void setRmTable(Rooms value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getRooms();

    /**
     * Set Attribute exposed for management
     */
    public void setRooms(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getSchools();

    /**
     * Set Attribute exposed for management
     */
    public void setSchools(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public Semesterunits getSemTable();

    /**
     * Set Attribute exposed for management
     */
    public void setSemTable(Semesterunits value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getSemUnits();

    /**
     * Set Attribute exposed for management
     */
    public void setSemUnits(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public Session getSession();

    /**
     * Set Attribute exposed for management
     */
    public void setSession(Session value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getStages();

    /**
     * Set Attribute exposed for management
     */
    public void setStages(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getStudents();

    /**
     * Set Attribute exposed for management
     */
    public void setStudents(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public TTDB getTTDB();

    /**
     * Get Attribute exposed for management
     */
    public Units getUnitTable();

    /**
     * Set Attribute exposed for management
     */
    public void setUnitTable(Units value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getUnits();

    /**
     * Set Attribute exposed for management
     */
    public void setUnits(ArrayList value);

    /**
     * newOperation0 Description
     */
    public void init();

    /**
     * Operation exposed for management
     * @param param0
     */
    public void saveTTData(UniversityTimeTable param0);
    
}

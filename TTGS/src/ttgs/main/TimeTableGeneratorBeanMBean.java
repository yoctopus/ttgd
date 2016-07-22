/*
 * TimeTableGeneratorBeanMBean.java
 *
 * Created on May 19, 2016, 10:32 PM
 */
package ttgs.main;

import java.util.ArrayList;
import ttgs.ds.Course;
import ttgs.ds.Group;
import ttgs.ds.tt.GroupTimeTable;
import ttgs.ds.tt.SchoolTimeTable;

/**
 * Interface TimeTableGeneratorBeanMBean
 *
 * @author Vincent
 */
public interface TimeTableGeneratorBeanMBean {

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getActiveUnits();

    /**
     * Set Attribute exposed for management
     */
    public void setActiveUnits(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getClashes_of_the_day();

    /**
     * Set Attribute exposed for management
     */
    public void setClashes_of_the_day(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getClashes_of_the_week();

    /**
     * Set Attribute exposed for management
     */
    public void setClashes_of_the_week(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public Course getCourse();

    /**
     * Set Attribute exposed for management
     */
    public void setCourse(Course value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getDays();

    /**
     * Set Attribute exposed for management
     */
    public void setDays(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getDaytimes();

    /**
     * Set Attribute exposed for management
     */
    public void setDaytimes(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getDtts();

    /**
     * Set Attribute exposed for management
     */
    public void setDtts(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public String getFRIDAY();

    /**
     * Set Attribute exposed for management
     */
    public void setFRIDAY(String value);

    /**
     * Get Attribute exposed for management
     */
    public Group getGroup();

    /**
     * Set Attribute exposed for management
     */
    public void setGroup(Group value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getId_units();

    /**
     * Set Attribute exposed for management
     */
    public void setId_units(ArrayList value);

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
    public String getMONDAY();

    /**
     * Set Attribute exposed for management
     */
    public void setMONDAY(String value);

    /**
     * Get Attribute exposed for management
     */
    public int getNumber_of_units();

    /**
     * Set Attribute exposed for management
     */
    public void setNumber_of_units(int value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getPlacedUnits();

    /**
     * Set Attribute exposed for management
     */
    public void setPlacedUnits(ArrayList value);

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
    public SchoolTimeTable getSchoolTimeTable();

    /**
     * Set Attribute exposed for management
     */
    public void setSchoolTimeTable(SchoolTimeTable value);

    /**
     * Get Attribute exposed for management
     */
    public String getTHURSDAY();

    /**
     * Set Attribute exposed for management
     */
    public void setTHURSDAY(String value);

    /**
     * Get Attribute exposed for management
     */
    public String getTUESDAY();

    /**
     * Set Attribute exposed for management
     */
    public void setTUESDAY(String value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getTimeSlots();

    /**
     * Set Attribute exposed for management
     */
    public void setTimeSlots(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public GroupTimeTable getTimeTable();

    /**
     * Set Attribute exposed for management
     */
    public void setTimeTable(GroupTimeTable value);

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getUsedDayTimes();

    /**
     * Set Attribute exposed for management
     */
    public void setUsedDayTimes(ArrayList value);

    /**
     * Get Attribute exposed for management
     */
    public String getWEDNESDAY();

    /**
     * Set Attribute exposed for management
     */
    public void setWEDNESDAY(String value);

    /**
     * Operation exposed for management
     */
    public void generate();

    /**
     * Operation exposed for management
     * @return java.util.ArrayList
     */
    public ArrayList newObjects();
    
}

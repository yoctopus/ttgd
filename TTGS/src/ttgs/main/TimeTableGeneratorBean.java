/*
 * TimeTableGeneratorBean.java
 *
 * Created on May 19, 2016, 10:32 PM
 */
package ttgs.main;

import java.util.ArrayList;
import javax.management.*;
import java.util.Arrays;
import ttgs.ds.Course;
import ttgs.ds.Group;
import ttgs.ds.tt.GroupTimeTable;
import ttgs.ds.tt.SchoolTimeTable;

/**
 * Class TimeTableGeneratorBean
 *
 * @author Vincent
 */
public class TimeTableGeneratorBean extends StandardMBean implements TimeTableGeneratorBeanMBean {
    private TimeTableGenerator theRef;

    public TimeTableGeneratorBean(TimeTableGenerator theRef) throws NotCompliantMBeanException {
        //WARNING Uncomment the following call to super() to make this class compile (see BUG ID 122377)
        super(TimeTableGeneratorBeanMBean.class);
        this.theRef = theRef;
    }
    
    
    @Override
    public MBeanInfo getMBeanInfo() {
        MBeanInfo mbinfo = super.getMBeanInfo();
        return new MBeanInfo(mbinfo.getClassName(),
                mbinfo.getDescription(),
                mbinfo.getAttributes(),
                mbinfo.getConstructors(),
                mbinfo.getOperations(),
                getNotificationInfo());
    }
    
    public MBeanNotificationInfo[] getNotificationInfo() {
        return new MBeanNotificationInfo[]{};
    }

    /**
     * Override customization hook:
     * You can supply a customized description for MBeanInfo.getDescription()
     */
    @Override
    protected String getDescription(MBeanInfo info) {
        return "TimeTableGeneratorBean Description";
    }

    /**
     * Override customization hook:
     * You can supply a customized description for MBeanAttributeInfo.getDescription()
     */
    @Override
    protected String getDescription(MBeanAttributeInfo info) {
        String description = null;
        if (info.getName().equals("ActiveUnits")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Clashes_of_the_day")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Clashes_of_the_week")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Course")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Days")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Daytimes")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Dtts")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("FRIDAY")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Group")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Id_units")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Lecturers")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("MONDAY")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Number_of_units")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("PlacedUnits")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Rooms")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("SchoolTimeTable")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("THURSDAY")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("TUESDAY")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("TimeSlots")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("TimeTable")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("UsedDayTimes")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("WEDNESDAY")) {
            description = "Attribute exposed for management";
        }
        return description;
    }

    /**
     * Override customization hook:
     * You can supply a customized description for MBeanParameterInfo.getDescription()
     */
    @Override
    protected String getDescription(MBeanOperationInfo op, MBeanParameterInfo param, int sequence) {
        if (op.getName().equals("generate")) {
            switch (sequence) {
                default:
                    return null;
            }
        } else if (op.getName().equals("newObjects")) {
            switch (sequence) {
                default:
                    return null;
            }
        }
        return null;
    }

    /**
     * Override customization hook:
     * You can supply a customized description for MBeanParameterInfo.getName()
     */
    @Override
    protected String getParameterName(MBeanOperationInfo op, MBeanParameterInfo param, int sequence) {
        if (op.getName().equals("generate")) {
            switch (sequence) {
                default:
                    return null;
            }
        } else if (op.getName().equals("newObjects")) {
            switch (sequence) {
                default:
                    return null;
            }
        }
        return null;
    }

    /**
     * Override customization hook:
     * You can supply a customized description for MBeanOperationInfo.getDescription()
     */
    @Override
    protected String getDescription(MBeanOperationInfo info) {
        String description = null;
        MBeanParameterInfo[] params = info.getSignature();
        String[] signature = new String[params.length];
        for (int i = 0; i < params.length; i++) {
            signature[i] = params[i].getType();
        }
        String[] methodSignature;
        methodSignature = new String[]{};
        if (info.getName().equals("generate") && Arrays.equals(signature, methodSignature)) {
            description = "Operation exposed for management";
        }
        methodSignature = new String[]{};
        if (info.getName().equals("newObjects") && Arrays.equals(signature, methodSignature)) {
            description = "Operation exposed for management";
        }
        return description;
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getActiveUnits() {
        return getTheRef().getActiveUnits();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setActiveUnits(ArrayList value) {
        getTheRef().setActiveUnits(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getClashes_of_the_day() {
        return getTheRef().getClashes_of_the_day();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setClashes_of_the_day(ArrayList value) {
        getTheRef().setClashes_of_the_day(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getClashes_of_the_week() {
        return getTheRef().getClashes_of_the_week();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setClashes_of_the_week(ArrayList value) {
        getTheRef().setClashes_of_the_week(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public Course getCourse() {
        return getTheRef().getCourse();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setCourse(Course value) {
        getTheRef().setCourse(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getDays() {
        return getTheRef().getDays();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setDays(ArrayList value) {
        getTheRef().setDays(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getDaytimes() {
        return getTheRef().getDaytimes();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setDaytimes(ArrayList value) {
        getTheRef().setDaytimes(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getDtts() {
        return getTheRef().getDtts();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setDtts(ArrayList value) {
        getTheRef().setDtts(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public String getFRIDAY() {
        return getTheRef().getFRIDAY();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setFRIDAY(String value) {
        getTheRef().setFRIDAY(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public Group getGroup() {
        return getTheRef().getGroup();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setGroup(Group value) {
        getTheRef().setGroup(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getId_units() {
        return getTheRef().getCurrentUnits();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setId_units(ArrayList value) {
        getTheRef().setCurrentUnits(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getLecturers() {
        return getTheRef().getLecturers();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setLecturers(ArrayList value) {
        getTheRef().setLecturers(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public String getMONDAY() {
        return getTheRef().getMONDAY();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setMONDAY(String value) {
        getTheRef().setMONDAY(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public int getNumber_of_units() {
        return getTheRef().getNumber_of_units();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setNumber_of_units(int value) {
        getTheRef().setNumber_of_units(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getPlacedUnits() {
        return getTheRef().getPlaced_currentUnits();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setPlacedUnits(ArrayList value) {
        getTheRef().setPlaced_currentUnits(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getRooms() {
        return getTheRef().getRooms();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setRooms(ArrayList value) {
        getTheRef().setRooms(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public SchoolTimeTable getSchoolTimeTable() {
        return getTheRef().getSchoolTimeTable();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setSchoolTimeTable(SchoolTimeTable value) {
        getTheRef().setSchoolTimeTable(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public String getTHURSDAY() {
        return getTheRef().getTHURSDAY();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setTHURSDAY(String value) {
        getTheRef().setTHURSDAY(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public String getTUESDAY() {
        return getTheRef().getTUESDAY();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setTUESDAY(String value) {
        getTheRef().setTUESDAY(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getTimeSlots() {
        return getTheRef().getTimeSlots();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setTimeSlots(ArrayList value) {
        getTheRef().setTimeSlots(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public GroupTimeTable getTimeTable() {
        return getTheRef().getTimeTable();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setTimeTable(GroupTimeTable value) {
        getTheRef().setTimeTable(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getUsedDayTimes() {
        return getTheRef().getUsedDayTimes();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setUsedDayTimes(ArrayList value) {
        getTheRef().setUsedDayTimes(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public String getWEDNESDAY() {
        return getTheRef().getWEDNESDAY();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setWEDNESDAY(String value) {
        getTheRef().setWEDNESDAY(value);
    }

    /**
     * Operation exposed for management
     */
    public void generate() {
        getTheRef().generate();
    }

    /**
     * Operation exposed for management
     * @return java.util.ArrayList
     */
    public ArrayList newObjects() {
        return getTheRef().newObjects();
    }

    /**
     * @return the theRef
     */
    public TimeTableGenerator getTheRef() {
        return theRef;
    }

    /**
     * @param theRef the theRef to set
     */
    public void setTheRef(TimeTableGenerator theRef) {
        this.theRef = theRef;
    }
}

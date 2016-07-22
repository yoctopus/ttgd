/*
 * TTDBBean.java
 *
 * Created on May 19, 2016, 10:41 PM
 */
package ttgs.api.database;

import java.util.ArrayList;
import javax.management.*;
import java.util.Arrays;
import org.hibernate.Session;
import ttgs.api.database.models.Courses;
import ttgs.api.database.models.Departments;
import ttgs.api.database.models.Lecturers;
import ttgs.api.database.models.Rooms;
import ttgs.api.database.models.Semesterunits;
import ttgs.api.database.models.Units;
import ttgs.ds.tt.UniversityTimeTable;

/**
 * Class TTDBBean
 *
 * @author Vincent
 */
public class TTDBBean extends StandardMBean implements TTDBBeanMBean {
    private TTDB theRef;

    public TTDBBean(TTDB theRef) throws NotCompliantMBeanException {
        //WARNING Uncomment the following call to super() to make this class compile (see BUG ID 122377)
        super(TTDBBeanMBean.class);
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
        return "TTDBBean Description";
    }

    /**
     * Override customization hook:
     * You can supply a customized description for MBeanAttributeInfo.getDescription()
     */
    @Override
    protected String getDescription(MBeanAttributeInfo info) {
        String description = null;
        if (info.getName().equals("ComUnits")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("CourseTable")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Courses")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Departments")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("DeptTable")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("LecTable")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Lecturers")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("RmTable")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Rooms")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Schools")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("SemTable")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("SemUnits")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Session")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Stages")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Students")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("TTDB")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("UnitTable")) {
            description = "Attribute exposed for management";
        } else if (info.getName().equals("Units")) {
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
        if (op.getName().equals("init")) {
            switch (sequence) {
                default:
                    return null;
            }
        } else if (op.getName().equals("saveTTData")) {
            switch (sequence) {
                case 0:
                    return "";
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
        if (op.getName().equals("init")) {
            switch (sequence) {
                default:
                    return null;
            }
        } else if (op.getName().equals("saveTTData")) {
            switch (sequence) {
                case 0:
                    return "param0";
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
        if (info.getName().equals("init") && Arrays.equals(signature, methodSignature)) {
            description = "newOperation0 Description";
        }
        methodSignature = new String[]{ttgs.ds.tt.UniversityTimeTable.class.getName()};
        if (info.getName().equals("saveTTData") && Arrays.equals(signature, methodSignature)) {
            description = "Operation exposed for management";
        }
        return description;
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getComUnits() {
        return theRef.getComUnits();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setComUnits(ArrayList value) {
        theRef.setComUnits(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public Courses getCourseTable() {
        return theRef.getCourseTable();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setCourseTable(Courses value) {
        theRef.setCourseTable(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getCourses() {
        return theRef.getCourses();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setCourses(ArrayList value) {
        theRef.setCourses(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getDepartments() {
        return theRef.getDepartments();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setDepartments(ArrayList value) {
        theRef.setDepartments(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public Departments getDeptTable() {
        return theRef.getDeptTable();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setDeptTable(Departments value) {
        theRef.setDeptTable(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public Lecturers getLecTable() {
        return theRef.getLecTable();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setLecTable(Lecturers value) {
        theRef.setLecTable(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getLecturers() {
        return theRef.getLecturers();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setLecturers(ArrayList value) {
        theRef.setLecturers(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public Rooms getRmTable() {
        return theRef.getRmTable();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setRmTable(Rooms value) {
        theRef.setRmTable(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getRooms() {
        return theRef.getRooms();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setRooms(ArrayList value) {
        theRef.setRooms(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getSchools() {
        return theRef.getSchools();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setSchools(ArrayList value) {
        theRef.setSchools(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public Semesterunits getSemTable() {
        return theRef.getSemTable();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setSemTable(Semesterunits value) {
        theRef.setSemTable(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getSemUnits() {
        return theRef.getSemUnits();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setSemUnits(ArrayList value) {
        theRef.setSemUnits(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public Session getSession() {
        return theRef.getSession();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setSession(Session value) {
        theRef.setSession(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getStages() {
        return theRef.getStages();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setStages(ArrayList value) {
        theRef.setStages(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getStudents() {
        return theRef.getStudents();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setStudents(ArrayList value) {
        theRef.setStudents(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public TTDB getTTDB() {
        return theRef.getTTDB();
    }

    /**
     * Get Attribute exposed for management
     */
    public Units getUnitTable() {
        return theRef.getUnitTable();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setUnitTable(Units value) {
        theRef.setUnitTable(value);
    }

    /**
     * Get Attribute exposed for management
     */
    public ArrayList getUnits() {
        return theRef.getUnits();
    }

    /**
     * Set Attribute exposed for management
     */
    public void setUnits(ArrayList value) {
        theRef.setUnits(value);
    }

    /**
     * newOperation0 Description
     */
    public void init() {
    }

    /**
     * Operation exposed for management
     * @param param0
     */
    public void saveTTData(UniversityTimeTable param0) {
        theRef.saveTTData(param0);
    }
}

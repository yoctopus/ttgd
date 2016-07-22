/*
 * MainLoggerBean.java
 *
 * Created on May 19, 2016, 10:11 PM
 */
package ttgs.main;

import javax.management.*;
import java.util.Arrays;

/**
 * Class MainLoggerBean
 *
 * @author Vincent
 */
public class MainLoggerBean extends StandardMBean implements MainLoggerBeanMBean {

    /**
     * Attribute : NewAttribute0
     */
    private String newAttribute0;
    private MainLogger theRef;

    public MainLoggerBean(MainLogger theRef) throws NotCompliantMBeanException {
        //WARNING Uncomment the following call to super() to make this class compile (see BUG ID 122377)
        super(MainLoggerBeanMBean.class);
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
     * Override customization hook: You can supply a customized description for
     * MBeanInfo.getDescription()
     */
    @Override
    protected String getDescription(MBeanInfo info) {
        return "MainLoggerBean Description";
    }

    /**
     * Override customization hook: You can supply a customized description for
     * MBeanAttributeInfo.getDescription()
     */
    @Override
    protected String getDescription(MBeanAttributeInfo info) {
        String description = null;
        if (info.getName().equals("NewAttribute0")) {
            description = "NewAttribute0 Description";
        }
        return description;
    }

    /**
     * Override customization hook: You can supply a customized description for
     * MBeanParameterInfo.getDescription()
     */
    @Override
    protected String getDescription(MBeanOperationInfo op, MBeanParameterInfo param, int sequence) {
        if (op.getName().equals("logger")) {
            switch (sequence) {
                case 0:
                    return "";
                default:
                    return null;
            }
        } else if (op.getName().equals("printIt")) {
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
     * Override customization hook: You can supply a customized description for
     * MBeanParameterInfo.getName()
     */
    @Override
    protected String getParameterName(MBeanOperationInfo op, MBeanParameterInfo param, int sequence) {
        if (op.getName().equals("logger")) {
            switch (sequence) {
                case 0:
                    return "param0";
                default:
                    return null;
            }
        } else if (op.getName().equals("printIt")) {
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
     * Override customization hook: You can supply a customized description for
     * MBeanOperationInfo.getDescription()
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
        methodSignature = new String[]{java.lang.String.class.getName()};
        if (info.getName().equals("logger") && Arrays.equals(signature, methodSignature)) {
            description = "Operation exposed for management";
        }
        methodSignature = new String[]{java.lang.String.class.getName()};
        if (info.getName().equals("printIt") && Arrays.equals(signature, methodSignature)) {
            description = "Operation exposed for management";
        }
        return description;
    }

    /**
     * Get NewAttribute0 Description
     */
    public String getNewAttribute0() {
        return newAttribute0;
    }

    /**
     * Set NewAttribute0 Description
     */
    public void setNewAttribute0(String value) {
        newAttribute0 = value;
    }

    /**
     * Operation exposed for management
     *
     * @param param0
     */
    public void logger(String param0) {
        getTheRef().logger(param0);
    }

    /**
     * Operation exposed for management
     *
     * @param param0
     */
    public void printIt(String param0) {
        getTheRef().printIt(param0);
    }

    /**
     * @return the theRef
     */
    public MainLogger getTheRef() {
        return theRef;
    }

    /**
     * @param theRef the theRef to set
     */
    public void setTheRef(MainLogger theRef) {
        this.theRef = theRef;
    }
}

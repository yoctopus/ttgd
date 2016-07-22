/*
 * MainLoggerBeanMBean.java
 *
 * Created on May 19, 2016, 10:11 PM
 */
package ttgs.main;

/**
 * Interface MainLoggerBeanMBean
 *
 * @author Vincent
 */
public interface MainLoggerBeanMBean {

    /**
     * Get NewAttribute0 Description
     */
    public String getNewAttribute0();

    /**
     * Set NewAttribute0 Description
     */
    public void setNewAttribute0(String value);

    /**
     * Operation exposed for management
     * @param param0
     */
    public void logger(String param0);

    /**
     * Operation exposed for management
     * @param param0
     */
    public void printIt(String param0);
    
}

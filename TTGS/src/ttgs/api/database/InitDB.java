/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.api.database;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Vincent
 */
public class InitDB {

    private static SessionFactory session;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            setSession(new AnnotationConfiguration().configure("../../../hibernate.cfg.xml").buildSessionFactory());
        } 
        catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed\n\n "
                    + "Possible error Database server is down." );            
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return session;
    }
    public static Session getSession() {
        return getSessionFactory().openSession();
    }

    /**
     * @param aSession the session to set
     */
    public static void setSession(SessionFactory aSession) {
        session = aSession;
    }
}

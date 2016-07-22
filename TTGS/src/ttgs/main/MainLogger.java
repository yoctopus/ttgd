/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.main;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vincent
 */
public class MainLogger {

    private static final Logger LOG = Logger.getLogger(MainLogger.class.getName());
    
    public static void logger(String log) {
        System.err.println(" " + log);
        
    }
    
    private static void logDown(String log) {
        LOG.log(Level.OFF, log);
    }
    
    public static void printIt(String s) {
        System.out.println(" " + s);
    }
}

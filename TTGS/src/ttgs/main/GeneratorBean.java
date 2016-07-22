/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.main;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author Vincent
 */
public class GeneratorBean implements Serializable {
    
    private static String PROP_SAMPLE_PROPERTY = "sampleProperty";

    /**
     * @return the PROP_SAMPLE_PROPERTY
     */
    public static String getPROP_SAMPLE_PROPERTY() {
        return PROP_SAMPLE_PROPERTY;
    }

    /**
     * @param aPROP_SAMPLE_PROPERTY the PROP_SAMPLE_PROPERTY to set
     */
    public static void setPROP_SAMPLE_PROPERTY(String aPROP_SAMPLE_PROPERTY) {
        PROP_SAMPLE_PROPERTY = aPROP_SAMPLE_PROPERTY;
    }
    
    private String sampleProperty;
    
    private PropertyChangeSupport propertySupport;
    
    public GeneratorBean() {
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public String getSampleProperty() {
        return sampleProperty;
    }
    
    public void setSampleProperty(String value) {
        String oldValue = sampleProperty;
        sampleProperty = value;
        getPropertySupport().firePropertyChange(getPROP_SAMPLE_PROPERTY(), oldValue, sampleProperty);
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        getPropertySupport().addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        getPropertySupport().removePropertyChangeListener(listener);
    }

    /**
     * @return the propertySupport
     */
    public PropertyChangeSupport getPropertySupport() {
        return propertySupport;
    }

    /**
     * @param propertySupport the propertySupport to set
     */
    public void setPropertySupport(PropertyChangeSupport propertySupport) {
        this.propertySupport = propertySupport;
    }
    
}

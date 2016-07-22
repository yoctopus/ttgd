/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.algorithms.comparisons;

import java.util.Comparator;
import ttgs.ds.ActiveUnit;

/**
 *
 * @author Vincent
 */
public class UnitCompare implements Comparator<ActiveUnit> {

    @Override
    public int compare(ActiveUnit o1, ActiveUnit o2) {
        return o1.compareTo(o2);
        
    }
    
}

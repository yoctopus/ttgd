/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.ds.tt;

import java.util.Date;

/**
 *
 * @author Vincent
 */
public class TTGSDate extends Date {

    public TTGSDate() {
        this.setYear(super.getYear());
        this.setSeconds(0);
    }
}

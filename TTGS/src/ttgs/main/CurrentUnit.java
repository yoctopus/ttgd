/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttgs.main;

import java.util.Objects;
import ttgs.ds.ActiveUnit;

/**
 *
 * @author Vincent
 */
public class CurrentUnit {
    
    public CurrentUnit(int id, ActiveUnit id_units) {
        this.id = id;
        this.id_units = id_units;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ActiveUnit getId_units() {
        return id_units;
    }

    public void setId_units(ActiveUnit id_units) {
        this.id_units = id_units;
    }
    private int id;
    private ActiveUnit id_units;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.getId();
        hash = 61 * hash + Objects.hashCode(this.getId_units());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CurrentUnit other = (CurrentUnit) obj;
        if (this.getId() != other.getId()) {
            return false;
        }
        if (!Objects.equals(this.id_units, other.id_units)) {
            return false;
        }
        return true;
    }
    
    
}

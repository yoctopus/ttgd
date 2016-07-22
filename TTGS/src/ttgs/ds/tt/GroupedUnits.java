
package ttgs.ds.tt;

import java.util.ArrayList;
import ttgs.ds.ActiveUnit;
import ttgs.ds.Unit;

/**
 *
 * @author Vin
 */
public class GroupedUnits {
    private class Inner {
        public int getCid() { return cid; }
        public void setCid(int cid) { this.cid = cid; }
        public ArrayList<ActiveUnit> getUnits() { return u; }
        public void setUnits(ArrayList<ActiveUnit> units) { this.u = units; }
        private int cid;
        private ArrayList<ActiveUnit> u;
        public Inner(int cid, ArrayList<ActiveUnit> units) {
            this.cid = cid;
            this.u = units;
        }
    }

    public ArrayList<Inner> getUnits() {  return units; }
    private ArrayList<Inner> units;
    public GroupedUnits() {
        units = new ArrayList();
    }
    public void addGroup(int cid, ArrayList<ActiveUnit> unit) {
        Inner newIn = new Inner(cid, unit);
        units.add(newIn);
    }
    public void removeGroup(int index) {
        this.units.remove(index);
    }
    public void Clear() {
        this.units.clear();
    }
    public ArrayList<ActiveUnit> getUnits(int cid) {
        ArrayList<ActiveUnit> local = new ArrayList();
        for(Inner n : getUnits()) {
            if (n.getCid() == cid) { local =  n.getUnits(); }
            else { local =  null; }
        }
        return local;
    }
}

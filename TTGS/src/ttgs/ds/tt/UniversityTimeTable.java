package ttgs.ds.tt;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author Vin
 */
public class UniversityTimeTable {
    private ArrayList<SchoolTimeTable> stt;
    public ArrayList<SchoolTimeTable> getStt() {
        return stt;
    }
    private static final Logger LOG = Logger.getLogger(UniversityTimeTable.class.getName());
    public void setStt(ArrayList<SchoolTimeTable> stt) {
        this.stt = stt;
    }    
    public UniversityTimeTable(ArrayList<SchoolTimeTable> stt) {
        this.stt = (ArrayList)stt.clone();
    }
    public UniversityTimeTable() {
        this(null);
    }
    public static final long serialVersionUID = 0L;
    public void addSchoolTT(SchoolTimeTable stt) {
        this.stt.add(stt);
    }
    public void removeSchoolTT(int index) {
        this.stt.remove(index);
    }
    public void ClearTT() {
        this.stt.clear();
    }
    @Override
    public String toString() {
        return "MainTT{" + "stt=" + stt + '}';
    }
}

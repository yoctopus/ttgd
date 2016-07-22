
package ttgs.ds.tt;

import java.util.ArrayList;
import java.util.logging.Logger;
import ttgs.ds.Room;

/**
 *
 * @author Vin
 */
public class GroupedRooms {
    private class Inner {
        private ArrayList<Room> rooms;
        private int sid;
        public ArrayList<Room> getRooms() { return rooms;}
        public void setRooms(ArrayList<Room> rooms) { this.rooms = rooms; }
        public int getSid() { return sid; }
        public void setSid(int sid) { this.sid = sid; }
        private Inner(int sid, ArrayList<Room> rooms) {
            this.rooms = rooms;
            this.sid = sid;            
        }
    }
    public ArrayList<Inner> getGrooms() { return grooms; }    
    private ArrayList<Inner> grooms;
    public GroupedRooms() {
        grooms = new ArrayList();
    }
    public void addGroup( int sid, ArrayList<Room> room) {
        Inner newInner = new Inner(sid, room);
        grooms.add(newInner);
    }
    public void removeGroup(int index) {
        grooms.remove(index);
    }
    public void clear() {
        grooms.clear();
    }
    public ArrayList<Room> getRomms(int sid) {
         ArrayList<Room> local = new ArrayList();
        for(Inner n : getGrooms()) {
            if (n.getSid() == sid) { local =  n.getRooms(); }
            else { local =  null; }
        }
        return local;
    }
}

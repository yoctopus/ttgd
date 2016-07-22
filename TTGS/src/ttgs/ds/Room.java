package ttgs.ds;
import ttgs.rules.Defaults.RoomDefaults;
/**
 *
 * @author Vin
 */
public class Room {
    public static final int BIG = RoomDefaults.BIG;    
    public static final int NORMAL = RoomDefaults.NORMAL, LAB = RoomDefaults.LAB;
    public static final int HALL = RoomDefaults.HALL;
    private int size;
    public void setSize(int size) {this.size = size;}
    public int getSize(){ return this.size; }
    private String name;
    public void setName(String name) { this.name = name; }
    public String getName() {return this.name; }
    private int ID;
    public void setID(int id) {this.ID = id;}
    public int getID(){ return this.ID; }
    public int getSid() { return sid;}
    public void setSid(int sid) { this.sid = sid; }
    private int sid;
    private int type;
    public void setType(int type) {this.type = type;}
    public int getType(){return this.type;}
    private boolean rmBusy = false;

    /**
     *
     * @param name of the room
     * @param size of the room
     * @param id of the room
     * @param type of the room
     * @param sid school id of the room
     */
    public Room(String name, int size, int id, int type, int sid) {
        this.setName(name);
        this.setSize(size);
        this.setID(id);
        this.setType(type);
        this.setSid(sid);
    }    
    public Room(String name, int id, int type, int sid) {
        this(name, NORMAL, id, type, sid);
    }
    
    
    @Override
    public String toString() {
        return this.getName();
    }
    public Room getRoom() {
        return this;
    }
    
    public void setBusy() {
        rmBusy = true;
    }
    
    public boolean isBusy() {
        return rmBusy;
    }
}

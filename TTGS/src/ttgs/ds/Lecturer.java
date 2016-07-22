package ttgs.ds;
/**
 *
 * @author Vin
 */
public final class Lecturer {
    public static final int Lec_isAvalilable = 0;
    public static final int Lec_isBusy = 1;
    private String name;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Unit getTheirUnit() {
        return theirUnit;
    }

    public void setTheirUnit(Unit theirUnit) {
        this.theirUnit = theirUnit;
    }
    private Unit theirUnit;
    
    public String getName() {return name;}	

    public Lecturer(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public int getID() {return this.id;}
    public void setName(String s) {this.name = s;}	
    public void setID(int s) {this.id = s;}
   
    
    public String getInitials() {
        String name = this.name, regex = " ";         
        StringBuilder initials = new StringBuilder();         
        String[] str = name.split(regex);
        for(String s : str) {
            initials.append(s.charAt(0));
            initials.append(regex);
        }
        return initials.toString().toUpperCase();
    }
    @Override
    public String toString() {
        return this.getName(); 
    }
    
}



package ttgs.ds;

/**
 *
 * @author Vin
 */
public class Stage {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private String name;
    private int id;
    
    
    public Stage(int id, String name) {        
        this.id = id;
        this.name = name;
    } 
    
}

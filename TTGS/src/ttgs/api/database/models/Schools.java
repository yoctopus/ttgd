package ttgs.api.database.models;
// Generated May 30, 2016 7:45:52 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Schools generated by hbm2java
 */
@Entity
@Table(name="schools"
    ,catalog="ttdata"
)
public class Schools  implements java.io.Serializable {


     private Integer sid;
     private String name;
     private int userId;
     private String description;
     private Set<Rooms> roomses = new HashSet<Rooms>(0);
     private Set<Departments> departmentses = new HashSet<Departments>(0);

    public Schools() {
    }

	
    public Schools(int userId, String description) {
        this.userId = userId;
        this.description = description;
    }
    public Schools(String name, int userId, String description, Set<Rooms> roomses, Set<Departments> departmentses) {
       this.name = name;
       this.userId = userId;
       this.description = description;
       this.roomses = roomses;
       this.departmentses = departmentses;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="sid", unique=true, nullable=false)
    public Integer getSid() {
        return this.sid;
    }
    
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    
    @Column(name="name", length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="user_id", nullable=false)
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    @Column(name="description", nullable=false, length=65535)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="schools")
    public Set<Rooms> getRoomses() {
        return this.roomses;
    }
    
    public void setRoomses(Set<Rooms> roomses) {
        this.roomses = roomses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="schools")
    public Set<Departments> getDepartmentses() {
        return this.departmentses;
    }
    
    public void setDepartmentses(Set<Departments> departmentses) {
        this.departmentses = departmentses;
    }




}



package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "Subject")
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String sb_name;

    public Subject(String sb_name){
        this.sb_name=sb_name;
    }
    public Subject(){}

    public int getId() {
        return Id;
    }

    public String getSb_name() {
        return sb_name;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setSb_name(String sb_name) {
        this.sb_name = sb_name;
    }
}
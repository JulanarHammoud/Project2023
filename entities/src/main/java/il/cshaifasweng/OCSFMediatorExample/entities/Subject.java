package il.cshaifasweng.OCSFMediatorExample.entities;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@MappedSuperclass
public abstract class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String sb_name;


    public int getId() {
        return Id;
    }



    public String getSb_name() {
        return sb_name;
    }

    public void setSb_name(String sb_name) {
        this.sb_name = sb_name;
    }

    public void setId(int id) {
        Id = id;
    }

    public Subject(String sb_name) {
        this.sb_name=sb_name;
    }
    public Subject(){}
}
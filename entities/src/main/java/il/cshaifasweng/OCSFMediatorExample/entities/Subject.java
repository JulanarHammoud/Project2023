package il.cshaifasweng.OCSFMediatorExample.entities;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name= "Subject")
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String sb_name;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Questions_Table",joinColumns = @JoinColumn(name = "Subject_ID" ),inverseJoinColumns = {@JoinColumn(name = "Question_Column")})
    private List<Question> Questions ;

    public int getId() {
        return Id;
    }

    public List<Question> getQuestions() {
        return Questions;
    }

    public void setQuestions(List<Question> questions) {
        this.Questions = questions;
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

    public Subject(String sb_name,List<Question> questions) {
        this.sb_name=sb_name;
        Questions=questions;
    }
    public Subject(){}
}
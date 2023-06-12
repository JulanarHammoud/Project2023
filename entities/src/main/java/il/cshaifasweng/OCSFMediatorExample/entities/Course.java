package il.cshaifasweng.OCSFMediatorExample.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.io.Serializable;

@MappedSuperclass
public abstract class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String name;

   /* @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "course_subject",joinColumns = @JoinColumn(name = "course_ID" ),inverseJoinColumns = {@JoinColumn(name = "subject_id")})
    private List<SubjectTeacher> subjectTeacher;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "course_subject",joinColumns = @JoinColumn(name = "course_ID" ),inverseJoinColumns = {@JoinColumn(name = "subject_id")})
    private List<SubjectStudent> subjectStudent;*/

    public Course( String name) {
        this.name = name;
    }
    public Course(){

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}

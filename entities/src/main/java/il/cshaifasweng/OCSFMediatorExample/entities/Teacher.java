package il.cshaifasweng.OCSFMediatorExample.entities;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="Teacher",uniqueConstraints= @UniqueConstraint(columnNames={"PassWord"}))
public class Teacher extends Person{

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Teacher_Subject",joinColumns = @JoinColumn(name = "Teacher_ID"),inverseJoinColumns = {@JoinColumn(name = "Subject_ID")})
    private List<SubjectTeacher> Subjects ;

    public Teacher(String firstName, String lastName, String userName, String passWord, List<SubjectTeacher> subjects) {
        super(firstName, lastName, userName, passWord);
        Subjects = subjects;
    }

    public void setSubjects(List<SubjectTeacher> subjects) {
        Subjects = subjects;
    }

    public Teacher() {
    }

    public List<SubjectTeacher> getSubjects() {
        return Subjects;
    }

    public Teacher(List<SubjectTeacher> subjects) {
        Subjects = subjects;
    }

}

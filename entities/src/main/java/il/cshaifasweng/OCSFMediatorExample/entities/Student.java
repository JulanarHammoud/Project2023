package il.cshaifasweng.OCSFMediatorExample.entities;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name ="Student",uniqueConstraints= @UniqueConstraint(columnNames={"PassWord"}))
public class Student extends Person{

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Student_Subject",joinColumns = @JoinColumn(name = "Student_ID"),inverseJoinColumns = {@JoinColumn(name = "Subject_ID")})
    private List<SubjectStudent> Subjects ;

    public Student(String firstName, String lastName, String userName, String passWord, List<SubjectStudent> subjects) {
        super(firstName, lastName, userName, passWord);
        Subjects = subjects;
    }

    public List<SubjectStudent> getSubjects() {
        return Subjects;
    }
   public Student(){}
}

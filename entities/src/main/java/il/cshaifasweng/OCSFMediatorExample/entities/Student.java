package il.cshaifasweng.OCSFMediatorExample.entities;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="Student",uniqueConstraints= @UniqueConstraint(columnNames={"PassWord"}))
public class Student extends Person{

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Student_course",joinColumns = @JoinColumn(name = "Student_ID"),inverseJoinColumns = {@JoinColumn(name = "course_ID")})
    private List<CourseStudent> courses ;

    public Student(String firstName, String lastName, String userName, String passWord, List<CourseStudent> courses) {
        super(firstName, lastName, userName, passWord);
        this.courses = courses;
    }

    public List<CourseStudent> getCourses() {
        return courses;
    }
    public Student(){

    }
}

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
    @JoinTable(name = "Teacher_Course",joinColumns = @JoinColumn(name = "Teacher_ID"),inverseJoinColumns = {@JoinColumn(name = "Course_ID")})
    private List<CourseTeacher> courses ;

    public Teacher(String firstName, String lastName, String userName, String passWord, List<CourseTeacher> courses) {
        super(firstName, lastName, userName, passWord);
        this.courses = courses;
    }

    public List<CourseTeacher> getCourses() {
        return courses;
    }
    public Teacher(){
    }
}

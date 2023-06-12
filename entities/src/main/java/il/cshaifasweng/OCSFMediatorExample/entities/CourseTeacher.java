package il.cshaifasweng.OCSFMediatorExample.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name= "CourseTeacher")
public class CourseTeacher extends Course {

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "course_teachersubject",joinColumns = @JoinColumn(name = "course_ID" ),inverseJoinColumns = {@JoinColumn(name = "subject_id")})
    private List<SubjectTeacher> subjectTeacher;

    public CourseTeacher(String name, List<SubjectTeacher> subjectTeacher) {
        super(name);
        this.subjectTeacher = subjectTeacher;
    }

    public List<SubjectTeacher> getSubjectTeacher() {
        return subjectTeacher;
    }

    public CourseTeacher() {
    }
}

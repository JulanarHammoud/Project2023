package il.cshaifasweng.OCSFMediatorExample.entities;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name= "CourseStudent")
public class CourseStudent extends Course {
    @OneToOne
    @JoinColumn(name = "last_exam_id")
    ExamStudent lastExam;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "course_studentsubject",joinColumns = @JoinColumn(name = "course_ID" ),inverseJoinColumns = {@JoinColumn(name = "subject_id")})
    private List<SubjectStudent> subjectstudent;

    public CourseStudent(String name, List<SubjectStudent> subjectstudent) {
        super(name);
        this.subjectstudent = subjectstudent;
    }

    public List<SubjectStudent> getSubjectstudent() {
        return subjectstudent;
    }

    public CourseStudent() {
    }

    public ExamStudent getLastExam() {
        return lastExam;
    }

    public void setLastExam(ExamStudent lastExam) {
        this.lastExam = lastExam;
    }
}


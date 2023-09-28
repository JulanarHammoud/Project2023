package il.cshaifasweng.OCSFMediatorExample.entities;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "SubjectTeacher")
public class SubjectTeacher extends Subject {

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Questions_Table",joinColumns = @JoinColumn(name = "Subject_ID" ),inverseJoinColumns = {@JoinColumn(name = "Question_Column")})
    private List<Question> Questions ;

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "exams_sub",joinColumns = @JoinColumn(name = "Subject_ID" ),inverseJoinColumns = {@JoinColumn(name = "exam_id")})
    private List<Exam> exams ;

    public List<Question> getQuestions() {
        return Questions;
    }

    public void setQuestions(List<Question> questions) {
        this.Questions = questions;
    }

    public SubjectTeacher(String sb_name, List<Question> questions) {
        super(sb_name);
        Questions = questions;
    }
    public SubjectTeacher(){}
}

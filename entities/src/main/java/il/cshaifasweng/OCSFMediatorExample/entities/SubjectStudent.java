package il.cshaifasweng.OCSFMediatorExample.entities;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="SubjectStudent")
public class SubjectStudent extends Subject {

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "sub_examSt",joinColumns = @JoinColumn(name = "subid" ),inverseJoinColumns = {@JoinColumn(name = "examid")})
    private List<ExamStudent> exams ;

    public SubjectStudent(String sb_name) {
        super(sb_name);
    }

    public SubjectStudent(){}

    public List<ExamStudent> getExams() {
        return exams;
    }

    public void setExams(List<ExamStudent> exams) {
        this.exams = exams;
    }
}




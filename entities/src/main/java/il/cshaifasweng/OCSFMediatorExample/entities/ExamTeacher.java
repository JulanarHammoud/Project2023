package il.cshaifasweng.OCSFMediatorExample.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name= "ExamTeacher")
public class ExamTeacher extends Exam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int Id;
    String Date;
    boolean computed;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Exams_of_students",joinColumns = @JoinColumn(name = "teacher_id" ),inverseJoinColumns = {@JoinColumn(name = "ExamStd_id")})

    protected LinkedList<ExamStudent> ExamsOfStudents;

    @Override
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public boolean isComputed() {
        return computed;
    }

    public void setComputed(boolean computed) {
        this.computed = computed;
    }

    public LinkedList<ExamStudent> getExamsOfStudents() {
        return ExamsOfStudents;
    }

    public void setExamsOfStudents(LinkedList<ExamStudent> examsOfStudents) {
        ExamsOfStudents = examsOfStudents;
    }
}
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
public class ExamTeacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int Id;
    String Date;
    String Time;
    String Code;
    boolean computed;
    @OneToOne
    @JoinColumn(name = "exam_id")
    Exam exam;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Exams_of_students",joinColumns = @JoinColumn(name = "teacher_id" ),inverseJoinColumns = {@JoinColumn(name = "ExamStd_id")})
    private List<ExamStudent> ExamsOfStudents;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "examTQuestion",joinColumns = @JoinColumn(name = "teacher_id" ),inverseJoinColumns = {@JoinColumn(name = "DetailedQes_id")})
    private List<DetailedQuestion> questions;

    public ExamTeacher() {
    }

    public String getSubject() {
        return exam.getSubject();
    }


    public String getTeacher() {
        return exam.getTeacher();
    }

    public ExamTeacher(Exam exam, String Date, String Time, boolean isComputed, String Code){
        this.exam = exam;
        this.Date = Date;
        this.Time = Time;
        this.computed = isComputed;
        this.Code = Code;
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

    public List<ExamStudent> getExamsOfStudents() {
        return this.ExamsOfStudents;
    }

    public void setExamsOfStudents(List<ExamStudent> examsOfStudents) {
        ExamsOfStudents = examsOfStudents;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<DetailedQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<DetailedQuestion> questions) {
        this.questions = questions;
    }

    public int getId() {
        return Id;
    }
}
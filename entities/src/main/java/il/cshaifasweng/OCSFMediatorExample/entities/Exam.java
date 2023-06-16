package il.cshaifasweng.OCSFMediatorExample.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;
import java.util.Timer;

@Entity
@Table(name= "Exams")
public class Exam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String IdCode;

    private int timerr;
    private int NumOfQuestions;
    private String subject;
    private String teacher;
   // private String course;
    private String TeacherNotes;
    private String StudentNotes;
    private String code;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Questions_Exam",joinColumns = @JoinColumn(name = "Exam_ID" ),inverseJoinColumns = {@JoinColumn(name = "Question_id")})
    private List<Question> Questions ;

    public Exam(int numOfQuestions, String teacherNotes,String timerr, String studentNotes, String subject, String teacher) {
       timerr = timerr;
        NumOfQuestions = numOfQuestions;
        this.subject = subject;
        this.teacher = teacher;
        //this.course = course;
        TeacherNotes = teacherNotes;
        StudentNotes = studentNotes;
    }

    public Exam() {

    }

    public int getTimerr() {
        return timerr;
    }

    public void setTimerr(int timerr) {
        this.timerr = timerr;
    }

    public String getIdCode() {
        return IdCode;
    }

    public void setIdCode(String idCode) {
        IdCode = idCode;
    }

    public int getNumOfQuestions() {
        return NumOfQuestions;
    }

    public void setNumOfQuestions(int numOfQuestions) {
        NumOfQuestions = numOfQuestions;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacherNotes() {
        return TeacherNotes;
    }

    public void setTeacherNotes(String teacherNotes) {
        TeacherNotes = teacherNotes;
    }

    public String getStudentNotes() {
        return StudentNotes;
    }

    public void setStudentNotes(String studentNotes) {
        StudentNotes = studentNotes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Question> getQuestions() {
        return Questions;
    }

    public void setQuestions(List<Question> questions) {
        Questions = questions;
    }

    public int getId() {
        return Id;
    }
}

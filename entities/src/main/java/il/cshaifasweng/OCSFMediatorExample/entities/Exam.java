package il.cshaifasweng.OCSFMediatorExample.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private String course;
    private String TeacherNotes;
    private String StudentNotes;
    private static String code;
    private String CodeGivenByTeacher;
    @Transient
    private Boolean exist = false;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Questions_Exam",joinColumns = @JoinColumn(name = "Exam_ID" ),inverseJoinColumns = {@JoinColumn(name = "Question_id")})
    private List<Question> Questions=new ArrayList<>() ;

    public Exam(int numOfQuestions, String teacherNotes,String timerr, String studentNotes,String course, String subject, String teacher) {
        this.timerr = Integer.parseInt(timerr);
        NumOfQuestions = numOfQuestions;
        this.subject = subject;
        this.teacher = teacher;
        this.course = course;
        TeacherNotes = teacherNotes;
        StudentNotes = studentNotes;
    }

    public Exam(int id, String idCode, int timerr, int numOfQuestions, String subject, String teacher, String course, String teacherNotes, String studentNotes, String codeGivenByTeacher, Boolean exist, List<Question> questions) {
        Id = id;
        IdCode = idCode;
        this.timerr = timerr;
        NumOfQuestions = numOfQuestions;
        this.subject = subject;
        this.teacher = teacher;
        this.course = course;
        TeacherNotes = teacherNotes;
        StudentNotes = studentNotes;
        CodeGivenByTeacher = codeGivenByTeacher;
        this.exist = exist;
        Questions = questions;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Exam() {

    }

    public String getCodeGivenByTeacher() {
        return CodeGivenByTeacher;
    }

    public void setCodeGivenByTeacher(String codeGivenByTeacher) {
        CodeGivenByTeacher = codeGivenByTeacher;
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

    public static String getCode() {
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
    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }
}

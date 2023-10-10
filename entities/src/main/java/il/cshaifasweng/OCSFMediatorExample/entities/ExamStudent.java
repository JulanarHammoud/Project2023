package il.cshaifasweng.OCSFMediatorExample.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name= "ExamStudent")
public class ExamStudent  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    int grade;
    String Date;
    String Time;
    boolean computed;
    int examTId;
    boolean executed;
    boolean onTime;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "examSQuestion",joinColumns = @JoinColumn(name = "teacher_id" ),inverseJoinColumns = {@JoinColumn(name = "DetailedQes_id")})
    protected List<DetailedQuestion> questions;

    @OneToOne
    @JoinColumn(name = "exam_std_id")
    Exam exam;
    String code;


    public ExamStudent() {
    }

    public ExamStudent(String Time, String date, boolean computed, Exam exam, String Code) {
        this.grade = grade;
        Date = date;
        this.computed = computed;
        this.exam = exam;
        this.code = Code;
    }

    public String getStudentNotes(){
        return exam.getStudentNotes();
    }
//    public void setQuestions(List<Question> questions) {
//        exam.setQuestions(questions);
//    }

    public String getTeacherNotes(){
        return exam.getTeacherNotes();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public String getCodeGivenByTeacher(){
        return exam.getCodeGivenByTeacher();
    }

//    public List<Question> getQuestions(){
//        return exam.getQuestions();
//    }
    public String getTeacher(){
        return exam.getTeacher();
    }
    public String getSubject(){
        return exam.getSubject();
    }
    public String getDate(){ return this.Date; }
    public int getTimerr(){
        return exam.getTimerr();
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public boolean isComputed() {
        return computed;
    }

    public void setComputed(boolean computed) {
        this.computed = computed;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setQuestions(List<DetailedQuestion> questions) {
        this.questions = questions;
    }

    public List<DetailedQuestion> getQuestions() {
        return questions;
    }

    public int getExamTId() {
        return examTId;
    }

    public void setExamTId(int examTId) {
        this.examTId = examTId;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public boolean isOnTime() {
        return onTime;
    }

    public void setOnTime(boolean onTime) {
        this.onTime = onTime;
    }
}

package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class StudentWillDoEx implements Serializable {
    private Student student;
//    private Exam ExamWillBeDone;
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

//    public Exam getExamWillBeDone() {
//        return ExamWillBeDone;
//    }
//
//    public void setExamWillBeDone(Exam examWillBeDone) {
//        ExamWillBeDone = examWillBeDone;
//    }

    public StudentWillDoEx(Student student) {
        this.student = student;
//        ExamWillBeDone = examWillBeDone;
    }
}

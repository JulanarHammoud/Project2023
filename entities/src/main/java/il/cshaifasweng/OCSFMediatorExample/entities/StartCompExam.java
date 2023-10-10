package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class StartCompExam implements Serializable {
    ExamStudent exam;
    Student student;

    public StartCompExam(ExamStudent exam, Student student) {
        this.exam = exam;
        this.student = student;
    }

    public ExamStudent getExam() {
        return exam;
    }

    public void setExam(ExamStudent exam) {
        this.exam = exam;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

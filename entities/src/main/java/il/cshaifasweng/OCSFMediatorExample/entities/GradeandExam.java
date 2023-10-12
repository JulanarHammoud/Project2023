package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class GradeandExam implements Serializable {
    ExamStudent exam;
        GradeSt grade;


    public GradeSt getGrade() {
        return grade;
    }

    public void setGrade(GradeSt grade) {
        this.grade = grade;
    }

    public void setExam(ExamStudent exam) {
        this.exam = exam;
    }

    public ExamStudent getExam() {
        return exam;
    }
        public GradeandExam(ExamStudent exam, GradeSt grade) {
       this.exam=exam;
       this.grade=grade;
        }

}

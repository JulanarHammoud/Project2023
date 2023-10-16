package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class ExamStudentsandGrade implements Serializable {
    ExamStudent examStudent;
    GradeSt gradeSt;

    public ExamStudentsandGrade(ExamStudent examStudent, GradeSt gradeSt) {
        this.examStudent = examStudent;
        this.gradeSt = gradeSt;
    }

    public ExamStudent getExamStudent() {
        return examStudent;
    }

    public void setExamStudent(ExamStudent examStudent) {
        this.examStudent = examStudent;
    }

    public GradeSt getGradeSt() {
        return gradeSt;
    }

    public void setGradeSt(GradeSt gradeSt) {
        this.gradeSt = gradeSt;
    }
}

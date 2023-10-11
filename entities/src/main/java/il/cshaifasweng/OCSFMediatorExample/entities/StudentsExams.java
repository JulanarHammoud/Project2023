package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class StudentsExams implements Serializable {
    Teacher teacher;
    ExamTeacher exam;

    public StudentsExams(Teacher teacher, ExamTeacher exam) {
        this.teacher = teacher;
        this.exam = exam;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ExamTeacher getExam() {
        return exam;
    }

    public void setExam(ExamTeacher exam) {
        this.exam = exam;
    }
}

package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class ToDuration implements Serializable {
    Teacher teacher;
    ExamTeacher examTeacher;

    public ToDuration(Teacher teacher, ExamTeacher examTeacher) {
        this.teacher = teacher;
        this.examTeacher = examTeacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ExamTeacher getExamTeacher() {
        return examTeacher;
    }

    public void setExamTeacher(ExamTeacher examTeacher) {
        this.examTeacher = examTeacher;
    }
}

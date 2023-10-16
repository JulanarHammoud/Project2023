package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class ToDuration implements Serializable {
    Teacher teacher;
    ExamTeacher examTeacher;
    boolean update;

    public ToDuration(Teacher teacher, ExamTeacher examTeacher, boolean update) {
        this.teacher = teacher;
        this.examTeacher = examTeacher;
        this.update = update;
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

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
}

package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class ExamSubjectTeacherEdit implements Serializable {
    Teacher teacher;
    SubjectTeacher subjectTeacher;
    Exam exam;
    int flag;
    boolean pressed=false;

    public ExamSubjectTeacherEdit(Teacher teacher, SubjectTeacher subjectTeacher, Exam exam) {
        this.teacher = teacher;
        this.subjectTeacher = subjectTeacher;
        this.exam = exam;
        this.flag=0;
    }

    public ExamSubjectTeacherEdit(Teacher teacher, SubjectTeacher subjectTeacher, Exam exam, int flag) {
        this.teacher = teacher;
        this.subjectTeacher = subjectTeacher;
        this.exam = exam;
        this.flag = flag;
    }

    public boolean getPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public SubjectTeacher getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(SubjectTeacher subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}

package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class ExamSubjectTeacherEdit implements Serializable {
    Teacher teacher;
    SubjectTeacher subjectTeacher;
    Exam exam;
    CourseTeacher courseTeacher;
    int flag;
    boolean pressed=false;


    public ExamSubjectTeacherEdit(Teacher teacher, SubjectTeacher subjectTeacher, Exam exam) {
        this.teacher = teacher;
        this.subjectTeacher = subjectTeacher;
        this.exam = exam;
        this.flag=0;
        this.courseTeacher=null;
    }

    public ExamSubjectTeacherEdit(Teacher teacher, SubjectTeacher subjectTeacher, Exam exam, int flag) {
        this.teacher = teacher;
        this.subjectTeacher = subjectTeacher;
        this.exam = exam;
        this.flag = flag;
        this.courseTeacher=null;
    }

    public ExamSubjectTeacherEdit(Teacher teacher, SubjectTeacher subjectTeacher, Exam exam, int flag, CourseTeacher courseTeacher) {
        this.teacher = teacher;
        this.subjectTeacher = subjectTeacher;
        this.exam = exam;
        this.courseTeacher = courseTeacher;
        this.flag = flag;
    }

    public CourseTeacher getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(CourseTeacher courseTeacher) {
        this.courseTeacher = courseTeacher;
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

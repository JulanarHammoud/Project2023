package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class ExamSubjectTeacher implements Serializable {
    Teacher teacher;
    SubjectTeacher subjectTeacher;
    Exam exam;
    CourseTeacher courseTeacher;
    boolean flag=false;

    public ExamSubjectTeacher(Teacher teacher, SubjectTeacher subjectTeacher, Exam exam) {
        this.teacher = teacher;
        this.subjectTeacher = subjectTeacher;
        this.exam = exam;
        this.courseTeacher=null;
    }

    public ExamSubjectTeacher(Teacher teacher, SubjectTeacher subjectTeacher, Exam exam, CourseTeacher courseTeacher) {
        this.teacher = teacher;
        this.subjectTeacher = subjectTeacher;
        this.exam = exam;
        this.courseTeacher = courseTeacher;
    }

    public ExamSubjectTeacher(Teacher teacher, SubjectTeacher subjectTeacher, Exam exam, CourseTeacher courseTeacher , boolean flag) {
        this.teacher = teacher;
        this.subjectTeacher = subjectTeacher;
        this.exam = exam;
        this.courseTeacher = courseTeacher;
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public CourseTeacher getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(CourseTeacher courseTeacher) {
        this.courseTeacher = courseTeacher;
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

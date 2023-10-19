package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.List;

public class GradeTeacher implements Serializable {
    Teacher teacher;
    CourseTeacher courseTeacher;
    SubjectTeacher subjectTeacher;
    List<ExamTeacher> examTeachers;

    public GradeTeacher(Teacher teacher, CourseTeacher courseTeacher, SubjectTeacher subjectTeacher, List<ExamTeacher> examTeachers) {
        this.teacher = teacher;
        this.courseTeacher = courseTeacher;
        this.subjectTeacher = subjectTeacher;
        this.examTeachers = examTeachers;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public CourseTeacher getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(CourseTeacher courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public SubjectTeacher getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(SubjectTeacher subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public List<ExamTeacher> getExamTeachers() {
        return examTeachers;
    }

    public void setExamTeachers(List<ExamTeacher> examTeachers) {
        this.examTeachers = examTeachers;
    }
}

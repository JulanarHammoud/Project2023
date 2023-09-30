package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class GetSubject implements Serializable {
    SubjectTeacher subjectTeacher;
    Teacher teacher;
    CourseTeacher courseTeacher;
    public GetSubject(SubjectTeacher subjectTeacher, Teacher teacher) {
        this.subjectTeacher = subjectTeacher;
        this.teacher = teacher;
        this.courseTeacher = null;
    }

    public CourseTeacher getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(CourseTeacher courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public GetSubject(SubjectTeacher subjectTeacher, Teacher teacher, CourseTeacher courseTeacher) {
        this.subjectTeacher = subjectTeacher;
        this.teacher = teacher;
        this.courseTeacher = courseTeacher;
    }

    public SubjectTeacher getSubjectTeacher() {
        return subjectTeacher;
    }
    public void setSubjectTeacher(SubjectTeacher subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
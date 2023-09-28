package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class GetSubject implements Serializable {
    SubjectTeacher subjectTeacher;
    Teacher teacher;
   // Course course;
 //   Subject subject;


    public GetSubject(SubjectTeacher subjectTeacher, Teacher teacher) {
        this.subjectTeacher = subjectTeacher;
        this.teacher = teacher;
    }
//    public GetSubject(SubjectTeacher subjectTeacher, Teacher teacher, Course course, Subject subject) {
//        this.subjectTeacher = subjectTeacher;
//        this.teacher = teacher;
//        this.course = course;
//        this.subject = subject;
//    }
//    public Subject getSubject() {
//        return subject;
//    }
//
//    public void setSubject(Subject subject) {
//        this.subject = subject;
//    }
//
//    public Course getCourse() {
//        return course;
//    }
//
//    public void setCourse(Course course) {
//        this.course = course;
//    }

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
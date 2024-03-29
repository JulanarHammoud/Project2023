package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.LinkedList;

public class SubjectAndId implements Serializable {
    SubjectTeacher subject ;
    int id;
    Teacher teacher;
    LinkedList<Question> questions;
    CourseTeacher courseTeacher;

    public SubjectAndId(SubjectTeacher subject, int id, Teacher teacher) {
        this.subject = subject;
        this.id = id;
        this.teacher = teacher;
        this.questions=null;
        this.courseTeacher=null;
    }

    public SubjectAndId(SubjectTeacher subject, int id, Teacher teacher, LinkedList<Question> questions, CourseTeacher courseTeacher) {
        this.subject = subject;
        this.id = id;
        this.teacher = teacher;
        this.questions = questions;
        this.courseTeacher=null;
    }

    public SubjectAndId(SubjectTeacher subject, int id, Teacher teacher, CourseTeacher courseTeacher) {
        this.subject = subject;
        this.id = id;
        this.teacher = teacher;
        this.questions=null;
        this.courseTeacher = courseTeacher;
    }

    public CourseTeacher getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(CourseTeacher courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public SubjectAndId(SubjectTeacher subject, int id, Teacher teacher, LinkedList<Question> questions) {
        this.subject = subject;
        this.id = id;
        this.teacher = teacher;
        this.questions = questions;
//        this.examSubjectTeacherEdit=null;
    }

//    public SubjectAndId(SubjectTeacher subject, int id, Teacher teacher, ExamSubjectTeacherEdit examSubjectTeacherEdit) {
//        this.subject = subject;
//        this.id = id;
//        this.teacher = teacher;
//        this.questions=null;
//        this.examSubjectTeacherEdit = examSubjectTeacherEdit;
//    }

//    public ExamSubjectTeacherEdit getExamSubjectTeacherEdit() {
//        return examSubjectTeacherEdit;
//    }
//
//    public void setExamSubjectTeacherEdit(ExamSubjectTeacherEdit examSubjectTeacherEdit) {
//        this.examSubjectTeacherEdit = examSubjectTeacherEdit;
//    }

    public LinkedList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(LinkedList<Question> questions) {
        this.questions = questions;
    }

    public SubjectTeacher getSubject() {
        return subject;
    }

    public int getId() {
        return id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}

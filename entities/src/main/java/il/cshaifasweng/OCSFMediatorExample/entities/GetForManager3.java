package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.List;

public class GetForManager3 implements Serializable {
    List<Teacher> teachers;
    List<Student> students;
    List<CourseTeacher> courses;

    public GetForManager3(List<Teacher> teachers, List<Student> students, List<CourseTeacher> courses) {
        this.teachers = teachers;
        this.students = students;
        this.courses = courses;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<CourseTeacher> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseTeacher> courses) {
        this.courses = courses;
    }
}
